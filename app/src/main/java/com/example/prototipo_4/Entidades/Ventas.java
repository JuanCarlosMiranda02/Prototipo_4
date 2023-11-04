package com.example.prototipo_4.Entidades;

import java.io.Serializable;

public class Ventas implements Serializable {
    private int Id_ventas;
    private float Total;
    private int ReducirStock;
    private int Producto_id;
    private int User_id;

    public Ventas(int id_ventas, float total, int reducirStock, int producto_id, int user_id) {
        Id_ventas = id_ventas;
        Total = total;
        ReducirStock = reducirStock;
        Producto_id = producto_id;
        User_id = user_id;
    }
    public Ventas(){

    }
    public int getId_ventas() {
        return Id_ventas;
    }

    public void setId_ventas(int id_ventas) {
        Id_ventas = id_ventas;
    }

    public float getTotal() {
        return Total;
    }

    public void setTotal(float total) {
        Total = total;
    }

    public int getReducirStock() {
        return ReducirStock;
    }

    public void setReducirStock(int reducirStock) {
        ReducirStock = reducirStock;
    }

    public int getProducto_id() {
        return Producto_id;
    }

    public void setProducto_id(int producto_id) {
        Producto_id = producto_id;
    }

    public int getUser_id() {
        return User_id;
    }

    public void setUser_id(int user_id) {
        User_id = user_id;
    }

}
