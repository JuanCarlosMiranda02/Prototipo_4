package com.example.prototipo_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Opcion_venta extends AppCompatActivity {
    private Button btn_puntoVentas, btn_lista_ventas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opcion_venta);
        btn_puntoVentas=(Button)findViewById(R.id.btn_puntoVentas);
        btn_lista_ventas=(Button)findViewById(R.id.btn_lista_ventas);

    }
    public void Detalles_venta(View view){
        Intent DetallesVentas = new Intent(this,Lista_Ventas.class);
        startActivity(DetallesVentas);
    }
    public void IrVentas(View view){
        Intent irVentas = new Intent(this, Ventas.class);
        startActivity(irVentas);
    }
}