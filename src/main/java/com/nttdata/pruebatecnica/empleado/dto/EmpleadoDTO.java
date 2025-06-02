package com.nttdata.pruebatecnica.empleado.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public class EmpleadoDTO {


    private  Long id;
    @NotBlank(message = "es obligatorio")
    @Size(min = 2, max = 100, message = "nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "es obligatorio")
    @Pattern(regexp = "\\d{9}", message = "teléfono debe tener 9 dígitos")
    private String telefono;

    @NotBlank(message = "es obligatorio")
    @Pattern(regexp = "\\d{8}", message = "DNI debe tener 8 dígitos")
    private String dni;

    @NotBlank(message = "es obligatoria")
    private String direccion;

    @NotNull(message = "es obligatoria")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;
    private List<Long> oficinasIds;

    public EmpleadoDTO() {
    }


    public EmpleadoDTO(Long id, String nombre, String telefono, String dni, String direccion, LocalDate fechaNacimiento, List<Long> oficinasIds) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.dni = dni;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.oficinasIds = oficinasIds;
    }

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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public List<Long> getOficinasIds() {
        return oficinasIds;
    }

    public void setOficinasIds(List<Long> oficinasIds) {
        this.oficinasIds = oficinasIds;
    }
}
