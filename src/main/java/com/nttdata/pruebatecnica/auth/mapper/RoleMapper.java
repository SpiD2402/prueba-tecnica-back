package com.nttdata.pruebatecnica.auth.mapper;

import com.nttdata.pruebatecnica.auth.dto.RoleDTO;
import com.nttdata.pruebatecnica.auth.model.RoleModel;



public class RoleMapper {
    public static RoleDTO toDTO(RoleModel role) {
        RoleDTO dto = new RoleDTO();
        dto.setId(role.getId());
        dto.setNombre(role.getNombre());
        return dto;
    }

    public static RoleModel toEntity(RoleDTO dto) {
        RoleModel role = new RoleModel();
        role.setId(dto.getId());
        role.setNombre(dto.getNombre());
        return role;
    }
}
