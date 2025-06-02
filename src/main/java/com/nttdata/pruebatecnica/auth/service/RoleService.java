package com.nttdata.pruebatecnica.auth.service;

import com.nttdata.pruebatecnica.auth.dto.RoleDTO;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<RoleDTO> obtenerTodasLosRoles();
    Optional<RoleDTO>  obtenerRolePorId(Long id);
    Optional<RoleDTO>  crearRole(RoleDTO roleDTO);
    Optional<RoleDTO>  actualizarRole(Long id, RoleDTO roleDTO);
    void eliminarRole(Long id);

}
