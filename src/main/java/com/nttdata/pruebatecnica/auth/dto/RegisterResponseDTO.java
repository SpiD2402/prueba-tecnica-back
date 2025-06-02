package com.nttdata.pruebatecnica.auth.dto;

public class RegisterResponseDTO {

    private String username;
    private String rol;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
