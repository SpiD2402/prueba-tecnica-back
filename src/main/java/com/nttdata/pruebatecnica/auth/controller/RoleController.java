package com.nttdata.pruebatecnica.auth.controller;

import com.nttdata.pruebatecnica.auth.dto.RoleDTO;
import com.nttdata.pruebatecnica.auth.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/role")
public class RoleController {


    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<RoleDTO>> obtenerTodosLosRoles() {
        List<RoleDTO> roles = roleService.obtenerTodasLosRoles();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> obtenerRolePorId(@PathVariable Long id) {
        Optional<RoleDTO> role = roleService.obtenerRolePorId(id);
        return ResponseEntity.ok(role.get());
    }

    @PostMapping
    public ResponseEntity<RoleDTO> crearRole(@RequestBody RoleDTO roleDTO) {
        Optional<RoleDTO> nuevoRole = roleService.crearRole(roleDTO);
        return new ResponseEntity<>(nuevoRole.get(), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDTO> actualizarRole(@PathVariable Long id, @RequestBody RoleDTO roleDTO) {
        Optional<RoleDTO> roleActualizado = roleService.actualizarRole(id, roleDTO);
        return ResponseEntity.ok(roleActualizado.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRole(@PathVariable Long id) {
        roleService.eliminarRole(id);
        return ResponseEntity.noContent().build();
    }



}
