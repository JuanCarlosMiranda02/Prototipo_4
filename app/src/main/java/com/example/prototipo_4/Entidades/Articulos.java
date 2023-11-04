package com.example.prototipo_4.Entidades;

import java.io.Serializable;

public class Articulos implements Serializable {
    private int Id_producto;
    private String Producto;
    private String Categoria;
    private int Cantidad;
    private float Precio;

    public Articulos (int id_producto, String producto, String categoria, int cantidad, float precio) {
        Id_producto = id_producto;
        Producto = producto;
        Categoria = categoria;
        Cantidad = cantidad;
        Precio = precio;
    }
    public Articulos(){

    }

    public int getId_producto() {
        return Id_producto;
    }

    public void setId_producto(int id_producto) {
        Id_producto = id_producto;
    }

    public String getProducto() {
        return Producto;
    }

    public void setProducto(String producto) {
        Producto = producto;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    public float getPrecio() {
        return Precio;
    }

    public void setPrecio(float precio) {
        Precio = precio;
    }
}
