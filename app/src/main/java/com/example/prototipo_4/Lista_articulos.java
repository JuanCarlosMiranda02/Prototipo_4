package com.example.prototipo_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.prototipo_4.Entidades.Articulos;
import com.example.prototipo_4.Utilidades.Utilidades;

import java.util.ArrayList;

public class Lista_articulos extends AppCompatActivity {
    ArrayList<String> listaInformacion;
    ArrayList<Articulos> listaArticulos;
    ListView listviewArticulos;
    ConexionSQlite conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_articulos);

        listviewArticulos=(ListView)findViewById(R.id.Lv_ListaArticulos);
        conn = new ConexionSQlite(getApplicationContext(),"ElectronicInventory.db", null,1);
        consultarlistaArticulos();
        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInformacion);
        listviewArticulos.setAdapter(adaptador);
        //Mostrar datos completos en un mensaje
        listviewArticulos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                String informacion = "Id_producto "+listaArticulos.get(pos).getId_producto()+ "\n";
                informacion+="Producto: "+listaArticulos.get(pos).getProducto()+ "\n";
                informacion+="Categoria: "+listaArticulos.get(pos).getCategoria()+ "\n";
                informacion+="Cantidad: "+listaArticulos.get(pos).getCantidad()+ "\n";
                informacion+="Precio: "+listaArticulos.get(pos).getPrecio()+ "\n";
                //Guardar datos para enviarlos a otra activdad más detallada
                Articulos articulo = listaArticulos.get(pos);
                Intent IrDetalles = new Intent(Lista_articulos.this,DetallesArticulo.class);
                Bundle bundle= new Bundle();
                bundle.putSerializable("articulo",articulo);
                IrDetalles.putExtras(bundle);
                startActivity(IrDetalles);
            }
        });
    }
    // Consulta a la base de datos
    private void consultarlistaArticulos() {
        SQLiteDatabase db = conn.getReadableDatabase();
        Articulos articulo = null;
        listaArticulos= new ArrayList<Articulos>();
        //Select * from Articulos
        Cursor cursor = db.rawQuery("SELECT * FROM "+Utilidades.TABLA_ARTICULOS, null);
        while (cursor.moveToNext() ){
            articulo= new Articulos();
            articulo.setId_producto(cursor.getInt(0));
            articulo.setProducto(cursor.getString(1));
            articulo.setCategoria(cursor.getString(2));
            articulo.setCantidad(cursor.getInt(3));
            articulo.setPrecio(cursor.getFloat(4));
            listaArticulos.add(articulo);

        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacion = new ArrayList<String>();
        for (int i=0; i<listaArticulos.size(); i++){
            listaInformacion.add(listaArticulos.get(i).getId_producto()+" - "+listaArticulos.get(i).getProducto());
        }
    }
}