package com.nttdata.pruebatecnica.auth.controller;

import com.nttdata.pruebatecnica.auth.dto.RegisterRequestDTO;
import com.nttdata.pruebatecnica.auth.dto.UserDTO;
import com.nttdata.pruebatecnica.auth.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<Optional<UserDTO>> crearUsuario(@RequestBody RegisterRequestDTO request) {
        Optional<UserDTO> nuevoUsuario = userService.crearUsuario(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }


    @GetMapping
    public ResponseEntity<List<UserDTO>> listarUsuarios() {
        List<UserDTO> usuarios = userService.listarUsuarios();
        return ResponseEntity.ok(usuarios);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserDTO>> obtenerPorId(@PathVariable Long id) {
        Optional<UserDTO> usuario = userService.obtenerUsuarioPorId(id);
        return ResponseEntity.ok(usuario);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        userService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
