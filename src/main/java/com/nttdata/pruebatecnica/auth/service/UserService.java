package com.nttdata.pruebatecnica.auth.service;

import com.nttdata.pruebatecnica.auth.dto.RegisterRequestDTO;
import com.nttdata.pruebatecnica.auth.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<UserDTO> crearUsuario(RegisterRequestDTO request);
    List<UserDTO> listarUsuarios();
    Optional<UserDTO> obtenerUsuarioPorId(Long id);
    void eliminarUsuario(Long id);
}
