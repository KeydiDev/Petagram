package com.keydi.petagram.pojo;

import java.util.Date;

public class Mascota {

    private int id;
    private String nombre;
    private int raiting;
    private int foto;

    public Mascota(String nombre, int raiting, int foto) {
        this.nombre = nombre;
        this.raiting = raiting;
        this.foto = foto;
    }

    public Mascota() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public int getRaiting() {
        return raiting;
    }

    public void setRaiting(int raiting) {
        this.raiting = raiting;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
