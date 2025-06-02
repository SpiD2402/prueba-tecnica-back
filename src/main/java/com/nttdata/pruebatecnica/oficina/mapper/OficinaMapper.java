package com.nttdata.pruebatecnica.oficina.mapper;

import com.nttdata.pruebatecnica.oficina.dto.OficinaDTO;
import com.nttdata.pruebatecnica.oficina.model.OficinaModel;

public class OficinaMapper {

    public static OficinaDTO toDTO(OficinaModel oficinaModel) {
        if (oficinaModel == null) {
            return null;
        }
        OficinaDTO dto = new OficinaDTO();
        dto.setId(oficinaModel.getId());
        dto.setNombre(oficinaModel.getNombre());
        dto.setDireccion(oficinaModel.getDireccion());
        return dto;
    }

    public static OficinaModel toEntity(OficinaDTO oficinaDTO) {
        if (oficinaDTO == null) {
            return null;
        }
        OficinaModel model = new OficinaModel();
        model.setId(oficinaDTO.getId());
        model.setNombre(oficinaDTO.getNombre());
        model.setDireccion(oficinaDTO.getDireccion());
        return model;
    }
}
