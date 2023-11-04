package com.example.prototipo_4.Entidades;

import java.io.Serializable;

public class Usuarios implements Serializable {
    private int Id_usuario;
    private String Nombre;
    private String Contrasenia;
    private String Tipo;

    public Usuarios(int id_usuario, String nombre, String contrasenia, String tipo) {
        Id_usuario = id_usuario;
        Nombre = nombre;
        Contrasenia = contrasenia;
        Tipo = tipo;
    }
    public Usuarios(){

    }
    public int getId_usuario() {
        return Id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        Id_usuario = id_usuario;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getContrasenia() {
        return Contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        Contrasenia = contrasenia;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }


}
