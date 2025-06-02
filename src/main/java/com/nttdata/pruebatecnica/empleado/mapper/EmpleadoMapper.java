package com.nttdata.pruebatecnica.empleado.mapper;

import com.nttdata.pruebatecnica.empleado.dto.EmpleadoDTO;
import com.nttdata.pruebatecnica.empleado.model.EmpleadoModel;
import com.nttdata.pruebatecnica.oficina.model.OficinaModel;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class EmpleadoMapper {

    public static EmpleadoDTO toDTO(EmpleadoModel model) {
        EmpleadoDTO dto = new EmpleadoDTO();
        dto.setId(model.getId());
        dto.setNombre(model.getNombre());
        dto.setTelefono(model.getTelefono());
        dto.setDni(model.getDni());
        dto.setDireccion(model.getDireccion());
        dto.setFechaNacimiento(model.getFechaNacimiento());

        if (model.getOficinas() != null) {
            List<Long> oficinasIds = model.getOficinas()
                    .stream()
                    .map(OficinaModel::getId)
                    .collect(Collectors.toList());
            dto.setOficinasIds(oficinasIds);
        }

        return dto;
    }

    public static EmpleadoModel toModel(EmpleadoDTO dto, List<OficinaModel> oficinas) {
        EmpleadoModel model = new EmpleadoModel();
        model.setId(dto.getId());
        model.setNombre(dto.getNombre());
        model.setTelefono(dto.getTelefono());
        model.setDni(dto.getDni());
        model.setDireccion(dto.getDireccion());
        model.setFechaNacimiento(dto.getFechaNacimiento());

        if (oficinas != null && !oficinas.isEmpty()) {
            model.setOficinas(new HashSet<>(oficinas));
        }

        return model;
    }

}
