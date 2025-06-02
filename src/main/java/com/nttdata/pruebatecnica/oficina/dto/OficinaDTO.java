package com.nttdata.pruebatecnica.oficina.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class OficinaDTO {

    private Long id;

    @NotBlank(message = "es obligatorio")
    @Size(min = 5, max = 100, message = "debe tener entre 2 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "es obligatoria")
    @Size(min = 5, max = 200, message = "debe tener entre 5 y 200 caracteres")
    private String direccion;

    public OficinaDTO() {}

    public OficinaDTO(Long id, String nombre, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
