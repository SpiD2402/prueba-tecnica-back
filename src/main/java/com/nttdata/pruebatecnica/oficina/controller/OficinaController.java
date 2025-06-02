package com.nttdata.pruebatecnica.oficina.controller;

import com.nttdata.pruebatecnica.oficina.dto.OficinaDTO;
import com.nttdata.pruebatecnica.oficina.service.OficinaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/oficina")
public class OficinaController {
    private final OficinaService oficinaService;

    public OficinaController(OficinaService oficinaService) {
        this.oficinaService = oficinaService;
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearOficina(@Valid @RequestBody OficinaDTO oficinaDTO, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(validar(result),HttpStatus.BAD_REQUEST);
        }

        OficinaDTO creada = oficinaService.crearOficina(oficinaDTO);
        return new ResponseEntity<>(creada, HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<OficinaDTO>> listarOficinas() {
        List<OficinaDTO> oficinas = oficinaService.listarOficinas();
        return ResponseEntity.ok(oficinas);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<OficinaDTO> obtenerOficinaPorId(@PathVariable Long id) {
        return oficinaService.obtenerOficinaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarOficina(@PathVariable Long id,
                                                        @Valid @RequestBody OficinaDTO oficinaDTO,
                                                        BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(validar(result),HttpStatus.BAD_REQUEST);
        }
        OficinaDTO actualizada = oficinaService.actualizarOficina(id, oficinaDTO);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarOficina(@PathVariable Long id) {
        oficinaService.eliminarOficina(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }

}
