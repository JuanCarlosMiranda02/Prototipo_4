package com.example.prototipo_4.Utilidades;

public class Utilidades {
    //Constante campos tabla Articulos
    public static final String TABLA_ARTICULOS = "Articulos";
    public static final String CAMPO_ID = "Id_producto";
    public static final String CAMPO_PRODUCTO = "Producto";
    public static final String CAMPO_CATEGORIA = "Categoria";
    public static final String CAMPO_CANTIDAD = "Cantidad";
    public static final String CAMPO_PRECIO = "Precio";
    //Constantes campos tabla Usuarios
    public static final String TABLA_USUARIOS = "Usuarios";
    public static final String CAMPO_ID_USER = "Id_usuario";
    public static final String CAMPO_NOMBRE = "Nombre";
    public static final String CAMPO_CONTRASENIA = "Contrasenia";
    public static final String CAMPO_TIPO = "Tipo";
    //Constantes campos tabla Ventas
    public static final String TABLA_VENTAS = "Ventas";
    public static final String CAMPO_ID_VENTA = "Id_venta";
    public static final String CAMPO_TOTAL = "Total";
    public static final String CAMPO_REDUCIRCSTOCK = "ReducirStock";
    public static final String CAMPO_PRODUCTO_ID = "Producto_id";
    //Crear tabla usuarios
    public static final String CREAR_TABLA_USUARIOS = "CREATE TABLE "+TABLA_USUARIOS+"("+CAMPO_ID_USER+" INTEGER PRIMARY KEY, " +CAMPO_NOMBRE+" TEXT, "+CAMPO_CONTRASENIA+" TEXT, "+CAMPO_TIPO+" TEXT)";

    //Crear tabla ventas
    public static final String CREAR_TABLA_VENTAS = "CREATE TABLE "+TABLA_VENTAS+"("+CAMPO_ID_VENTA+" INTEGER AUTOINCREMENT PRIMARY KEY , " +CAMPO_TOTAL+" REAL, "+CAMPO_REDUCIRCSTOCK+" INTEGER, "+CAMPO_PRODUCTO_ID+" INTEGER)";
    //Crear tabla artículos
    public static final String CREAR_TABLA_ARTICULOS = "CREATE TABLE "+TABLA_ARTICULOS+"("+CAMPO_ID+" INTEGER PRIMARY KEY, " +CAMPO_PRODUCTO+" TEXT, "+CAMPO_CATEGORIA+" TEXT, "+CAMPO_CANTIDAD+" INTEGER, "+CAMPO_PRECIO+" REAL)";
}
