package com.nttdata.pruebatecnica.oficina.model;

import com.nttdata.pruebatecnica.empleado.model.EmpleadoModel;
import jakarta.persistence.*;

import java.util.List;

@Table(name = "oficinas")
@Entity
public class OficinaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String direccion;

    @ManyToMany(mappedBy = "oficinas")
    private List<EmpleadoModel> empleados;


    public OficinaModel() {
    }

    public OficinaModel(Long id, String nombre, String direccion, List<EmpleadoModel> empleados) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.empleados = empleados;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<EmpleadoModel> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<EmpleadoModel> empleados) {
        this.empleados = empleados;
    }
}
