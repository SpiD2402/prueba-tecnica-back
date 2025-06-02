package com.nttdata.pruebatecnica.empleado.service;

import com.nttdata.pruebatecnica.empleado.dto.EmpleadoDTO;

import java.util.List;
import java.util.Optional;

public interface EmpleadoService {


    EmpleadoDTO crearEmpleado(EmpleadoDTO empleadoDTO);

    List<EmpleadoDTO> listarEmpleados();

    Optional<EmpleadoDTO> obtenerEmpleadoPorId(Long id);

    EmpleadoDTO actualizarEmpleado(Long id, EmpleadoDTO empleadoDTO);

    void eliminarEmpleado(Long id);

    EmpleadoDTO asignarOficinas(Long empleadoId, List<Long> oficinasIds);


}
