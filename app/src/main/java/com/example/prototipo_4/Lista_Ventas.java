package com.example.prototipo_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.prototipo_4.Entidades.Usuarios;
import com.example.prototipo_4.Entidades.Ventas;
import com.example.prototipo_4.Utilidades.Utilidades;

import java.util.ArrayList;

public class Lista_Ventas extends AppCompatActivity {
    ArrayList<String> listainformacionVentas;
    ArrayList<Ventas> listaVentas;
    ListView listviewVentas;
    ConexionSQlite conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ventas);
        listviewVentas=(ListView)findViewById(R.id.Lv_ventas);
        conn = new ConexionSQlite(getApplicationContext(),"ElectronicInventory.db", null,1);
        consultarlistaVentas();
        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listainformacionVentas);
        listviewVentas.setAdapter(adaptador);
        //Mostrar datos completos en un mensaje
        listviewVentas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                String informacionVenta = "Id_venta "+listaVentas.get(pos).getId_ventas()+ "\n";
                informacionVenta+="Total: "+listaVentas.get(pos).getTotal()+ "\n";
                informacionVenta+="Cantidad vendida: "+listaVentas.get(pos).getReducirStock()+ "\n";
                informacionVenta+="Id_producto: "+listaVentas.get(pos).getProducto_id()+ "\n";

                //Guardar datos para enviarlos a otra activdad más detallada
                Ventas venta = listaVentas.get(pos);
                Intent IrDetallesVentas = new Intent(Lista_Ventas.this,Detalles_venta.class);
                Bundle bundle= new Bundle();
                bundle.putSerializable("venta", venta);
                IrDetallesVentas.putExtras(bundle);
                startActivity(IrDetallesVentas);
            }
        });
    }
    // Consulta a la base de datos
    private void consultarlistaVentas() {
        SQLiteDatabase db = conn.getReadableDatabase();
        Ventas venta = null;
        listaVentas= new ArrayList<Ventas>();
        //Select * from VEntas
        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_VENTAS, null);
        while (cursor.moveToNext() ){
            venta= new Ventas();
            venta.setId_ventas(cursor.getInt(0));
            venta.setTotal(cursor.getFloat(1));
            venta.setReducirStock(cursor.getInt(2));
            venta.setProducto_id(cursor.getInt(3));
            listaVentas.add(venta);

        }
        obtenerListaventas();
    }

    private void obtenerListaventas() {
        listainformacionVentas = new ArrayList<String>();
        for (int i=0; i<listaVentas.size(); i++){
            listainformacionVentas.add(listaVentas.get(i).getId_ventas()+" - "+listaVentas.get(i).getTotal());
        }
    }

}