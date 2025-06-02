package com.nttdata.pruebatecnica.auth.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDTO {
    private Long id;
    private String username;

    @JsonIgnore
    private String password;
    private RoleDTO rol;

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

    public RoleDTO getRol() {
        return rol;
    }

    public void setRol(RoleDTO rol) {
        this.rol = rol;
    }
}
