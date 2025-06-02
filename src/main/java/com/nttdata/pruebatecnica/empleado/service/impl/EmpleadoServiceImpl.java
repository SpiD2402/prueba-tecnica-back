package com.nttdata.pruebatecnica.empleado.service.impl;

import com.nttdata.pruebatecnica.empleado.dto.EmpleadoDTO;
import com.nttdata.pruebatecnica.empleado.mapper.EmpleadoMapper;
import com.nttdata.pruebatecnica.empleado.model.EmpleadoModel;
import com.nttdata.pruebatecnica.empleado.repository.EmpleadoRepository;
import com.nttdata.pruebatecnica.empleado.service.EmpleadoService;
import com.nttdata.pruebatecnica.oficina.model.OficinaModel;
import com.nttdata.pruebatecnica.oficina.repository.OficinaRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private final OficinaRepository oficinaRepository;

    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository,
                               OficinaRepository oficinaRepository) {
        this.empleadoRepository = empleadoRepository;
        this.oficinaRepository = oficinaRepository;
    }

    @PostConstruct
    public void initEmpleados() {
        if (empleadoRepository.count() == 0) {


            EmpleadoModel emp1 = new EmpleadoModel();
            emp1.setNombre("Juan Pérez");
            emp1.setTelefono("987654321");
            emp1.setDni("12345678");
            emp1.setDireccion("Calle Falsa 123");
            emp1.setFechaNacimiento(LocalDate.of(1990, 5, 20));

            EmpleadoModel emp2 = new EmpleadoModel();
            emp2.setNombre("María García");
            emp2.setTelefono("912345678");
            emp2.setDni("87654321");
            emp2.setDireccion("Av. Real 456");
            emp2.setFechaNacimiento(LocalDate.of(1985, 8, 15));


            empleadoRepository.saveAll(List.of(emp1, emp2));

            System.out.println("Empleados por defecto creados.");
        }
    }


    @Override
    @Transactional
    public EmpleadoDTO crearEmpleado(EmpleadoDTO empleadoDTO) {
        List<OficinaModel> oficinas = new ArrayList<>();

        if (empleadoDTO.getOficinasIds() != null && !empleadoDTO.getOficinasIds().isEmpty()) {
            oficinas = oficinaRepository.findAllById(empleadoDTO.getOficinasIds());
        }

        EmpleadoModel empleado = EmpleadoMapper.toModel(empleadoDTO, oficinas);
        EmpleadoModel saved = empleadoRepository.save(empleado);

        return EmpleadoMapper.toDTO(saved);
    }
    @Transactional(readOnly = true)
    @Override
    public List<EmpleadoDTO> listarEmpleados() {
        return empleadoRepository.findAll().stream()
                .map(EmpleadoMapper::toDTO)
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    @Override
    public Optional<EmpleadoDTO> obtenerEmpleadoPorId(Long id) {
        return empleadoRepository.findById(id)
                .map(EmpleadoMapper::toDTO);
    }
    @Transactional
    @Override
    public EmpleadoDTO actualizarEmpleado(Long id, EmpleadoDTO empleadoDTO) {
        EmpleadoModel empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        empleado.setNombre(empleadoDTO.getNombre());
        empleado.setDni(empleadoDTO.getDni());
        empleado.setTelefono(empleadoDTO.getTelefono());
        empleado.setDireccion(empleadoDTO.getDireccion());
        empleado.setFechaNacimiento(empleadoDTO.getFechaNacimiento());

        EmpleadoModel actualizado = empleadoRepository.save(empleado);
        return EmpleadoMapper.toDTO(actualizado);
    }
    @Transactional
    @Override
    public void eliminarEmpleado(Long id) {
        if (!empleadoRepository.existsById(id)) {
            throw new RuntimeException("Empleado no encontrado");
        }
        empleadoRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public EmpleadoDTO asignarOficinas(Long empleadoId, List<Long> oficinasIds) {
        EmpleadoModel empleado = empleadoRepository.findById(empleadoId)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        List<OficinaModel> oficinas = oficinaRepository.findAllById(oficinasIds);

        empleado.setOficinas(new HashSet<>(oficinas));
        EmpleadoModel actualizado = empleadoRepository.save(empleado);

        return EmpleadoMapper.toDTO(actualizado);
    }

}
