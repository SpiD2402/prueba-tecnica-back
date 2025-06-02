package com.nttdata.pruebatecnica.auth.model;

import jakarta.persistence.*;
@Entity
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private RoleModel rol;

    public UserModel(Long id, String username, String password, RoleModel rol) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.rol = rol;
    }

    public UserModel() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleModel getRol() {
        return rol;
    }

    public void setRol(RoleModel rol) {
        this.rol = rol;
    }
}
