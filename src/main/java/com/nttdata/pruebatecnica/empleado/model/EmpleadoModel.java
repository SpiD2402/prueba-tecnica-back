package com.nttdata.pruebatecnica.empleado.model;

import com.nttdata.pruebatecnica.oficina.model.OficinaModel;
import jakarta.persistence.*;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table(name = "empleados")
@Entity
public class EmpleadoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String telefono;
    private String dni;
    private String direccion;
    private LocalDate fechaNacimiento;

    @ManyToMany
    @JoinTable(
            name = "empleado_oficina",
            joinColumns = @JoinColumn(name = "empleado_id"),
            inverseJoinColumns = @JoinColumn(name = "oficina_id")
    )
    private Set<OficinaModel> oficinas = new HashSet<>();

    public EmpleadoModel() {
    }

    public EmpleadoModel(Long id, String nombre, String telefono, String dni, String direccion, LocalDate fechaNacimiento, Set<OficinaModel> oficinas) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.dni = dni;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.oficinas = oficinas;
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

    public Set<OficinaModel> getOficinas() {
        return oficinas;
    }

    public void setOficinas(Set<OficinaModel> oficinas) {
        this.oficinas = oficinas;
    }
}
