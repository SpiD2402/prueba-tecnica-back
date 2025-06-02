package com.nttdata.pruebatecnica.oficina.service.impl;

import com.nttdata.pruebatecnica.oficina.dto.OficinaDTO;
import com.nttdata.pruebatecnica.oficina.mapper.OficinaMapper;
import com.nttdata.pruebatecnica.oficina.model.OficinaModel;
import com.nttdata.pruebatecnica.oficina.repository.OficinaRepository;
import com.nttdata.pruebatecnica.oficina.service.OficinaService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OficinaServiceImpl implements OficinaService {
    private final OficinaRepository oficinaRepository;

    public OficinaServiceImpl(OficinaRepository oficinaRepository) {
        this.oficinaRepository = oficinaRepository;
    }




    @PostConstruct
    public void initOficinas() {
        if (oficinaRepository.count() == 0) {
            OficinaModel oficina1 = new OficinaModel();
            oficina1.setNombre("Oficina Central");
            oficina1.setDireccion("Av. Principal 123");

            OficinaModel oficina2 = new OficinaModel();
            oficina2.setNombre("Sucursal Norte");
            oficina2.setDireccion("Calle Norte 456");

            OficinaModel oficina3 = new OficinaModel();
            oficina3.setNombre("Sucursal Sur");
            oficina3.setDireccion("Avenida Sur 789");

            oficinaRepository.save(oficina1);
            oficinaRepository.save(oficina2);
            oficinaRepository.save(oficina3);

            System.out.println("Oficinas por defecto creadas.");
        }
    }


    @Transactional
    @Override
    public OficinaDTO crearOficina(OficinaDTO oficinaDTO) {
        OficinaModel oficina = OficinaMapper.toEntity(oficinaDTO);
        OficinaModel saved = oficinaRepository.save(oficina);
        return OficinaMapper.toDTO(saved);
    }
    @Transactional(readOnly = true)
    @Override
    public List<OficinaDTO> listarOficinas() {
        return oficinaRepository.findAll().stream()
                .map(OficinaMapper::toDTO)
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    @Override
    public Optional<OficinaDTO> obtenerOficinaPorId(Long id) {
        return oficinaRepository.findById(id)
                .map(OficinaMapper::toDTO);
    }
    @Transactional
    @Override
    public OficinaDTO actualizarOficina(Long id, OficinaDTO oficinaDTO) {
        OficinaModel oficina = oficinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Oficina no encontrada"));

        oficina.setNombre(oficinaDTO.getNombre());
        oficina.setDireccion(oficinaDTO.getDireccion());

        OficinaModel updated = oficinaRepository.save(oficina);
        return OficinaMapper.toDTO(updated);
    }
    @Transactional
    @Override
    public void eliminarOficina(Long id) {
        if (!oficinaRepository.existsById(id)) {
            throw new RuntimeException("Oficina no encontrada");
        }
        oficinaRepository.deleteById(id);
    }
}
