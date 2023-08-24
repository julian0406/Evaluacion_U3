package com.example.evaluacion_3.model;

public class user {
    private int id;
    private String name;
    private String rol;

    public user(int id, String name, String rol) {
        this.id = id;
        this.name = name;
        this.rol = rol;
    }


    public user() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}


