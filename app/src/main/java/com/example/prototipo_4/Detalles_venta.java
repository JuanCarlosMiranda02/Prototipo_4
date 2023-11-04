package com.example.prototipo_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.prototipo_4.Entidades.Usuarios;
import com.example.prototipo_4.Entidades.Ventas;

public class Detalles_venta extends AppCompatActivity {
    private Button btn_regresar;
    private TextView Tv_CampoTotal, Tv_CampoCantidadVendida, Tv_CampoId_Venta, Tv_CampoId_producto;
    ConexionSQlite conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_venta);
        Tv_CampoId_Venta = (TextView) findViewById(R.id.Tv_CampoId_venta);
        Tv_CampoId_producto = (TextView) findViewById(R.id.Tv_CampoId_producto);
        Tv_CampoCantidadVendida = (TextView) findViewById(R.id.Tv_CampoCantidadVendida);
        Tv_CampoTotal = (TextView) findViewById(R.id.Tv_CampoTotal);
        //Iniciar la base de datos
        conn = new ConexionSQlite(this, "ElectronicInventory.db", null, 1);
        //Recibir los datos detallados de la lista de articulos
        Bundle objetoEnviado = getIntent().getExtras();
        com.example.prototipo_4.Entidades.Ventas venta = null;
        if (objetoEnviado != null) {
            venta = (Ventas) objetoEnviado.getSerializable("venta");
            //Convertir int y floats a strings para imprimirlos
            int Id_venta = venta.getId_ventas();
            int Id_producto = venta.getProducto_id();
            int CantidadVendida = venta.getReducirStock();
            float TotalVenta = venta.getTotal();

            String id_venta = String.valueOf(Id_venta);
            String id_producto = String.valueOf(Id_producto);
            String cantidadvendida = String.valueOf(CantidadVendida);
            String totalventa = String.valueOf(TotalVenta);

            //Imprimirlos como strings
            Tv_CampoId_Venta.setText(id_venta);//Convertido
            Tv_CampoId_producto.setText(id_producto);
            Tv_CampoCantidadVendida.setText(cantidadvendida);
            Tv_CampoTotal.setText(totalventa);
        }
    }
    public void VolverVentas(View view){
        Intent RegresarVentas = new Intent(this, Lista_Ventas.class);
        startActivity(RegresarVentas);
    }
}