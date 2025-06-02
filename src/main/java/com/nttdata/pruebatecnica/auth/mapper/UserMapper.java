package com.nttdata.pruebatecnica.auth.mapper;

import com.nttdata.pruebatecnica.auth.dto.UserDTO;
import com.nttdata.pruebatecnica.auth.model.UserModel;

public class UserMapper {
    public static UserDTO toDTO(UserModel userModel) {
        if (userModel == null) return null;

        UserDTO dto = new UserDTO();
        dto.setId(userModel.getId());
        dto.setUsername(userModel.getUsername());
        dto.setPassword(userModel.getPassword());
        dto.setRol(RoleMapper.toDTO(userModel.getRol()));
        return dto;
    }

    public static UserModel toEntity(UserDTO dto) {
        if (dto == null) return null;

        UserModel usuario = new UserModel();
        usuario.setId(dto.getId());
        usuario.setUsername(dto.getUsername());
        usuario.setPassword(dto.getPassword());
        usuario.setRol(RoleMapper.toEntity(dto.getRol()));
        return usuario;
    }
}
