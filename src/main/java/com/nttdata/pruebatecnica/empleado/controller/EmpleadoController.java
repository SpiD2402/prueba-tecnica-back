package com.nttdata.pruebatecnica.empleado.controller;

import com.nttdata.pruebatecnica.empleado.dto.EmpleadoDTO;
import com.nttdata.pruebatecnica.empleado.service.EmpleadoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {
    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearEmpleado(@Valid @RequestBody EmpleadoDTO empleadoDTO,BindingResult result) {

        if (result.hasErrors()) {
            return new ResponseEntity<>(validar(result),HttpStatus.BAD_REQUEST);
        }

        EmpleadoDTO creado = empleadoService.crearEmpleado(empleadoDTO);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<EmpleadoDTO>> listarEmpleados() {
        List<EmpleadoDTO> empleados = empleadoService.listarEmpleados();
        return ResponseEntity.ok(empleados);
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<EmpleadoDTO> obtenerEmpleadoPorId(@PathVariable Long id) {
        return empleadoService.obtenerEmpleadoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarEmpleado(@PathVariable Long id, @Valid @RequestBody EmpleadoDTO empleadoDTO,BindingResult result) {

        if (result.hasErrors()) {
            return new ResponseEntity<>(validar(result),HttpStatus.BAD_REQUEST);
        }

        EmpleadoDTO actualizado = empleadoService.actualizarEmpleado(id, empleadoDTO);
        return ResponseEntity.ok(actualizado);
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable Long id) {
        empleadoService.eliminarEmpleado(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{empleadoId}/oficinas")
    public ResponseEntity<EmpleadoDTO> asignarOficinas(@PathVariable Long empleadoId, @RequestBody List<Long> oficinasIds) {
        EmpleadoDTO actualizado = empleadoService.asignarOficinas(empleadoId, oficinasIds);
        return ResponseEntity.ok(actualizado);
    }

    private ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }

}
