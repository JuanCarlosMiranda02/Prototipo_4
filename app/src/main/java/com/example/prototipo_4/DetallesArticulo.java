package com.example.prototipo_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prototipo_4.Entidades.Articulos;
import com.example.prototipo_4.Utilidades.Utilidades;

public class DetallesArticulo extends AppCompatActivity {
    private TextView Tv_id, Tv_producto, Tv_categoria, Tv_cantidad, Tv_precio, Et_campoId;
    private EditText Et_campoProducto, Et_campoCategoria, Et_campoCantidad, Et_campoPrecio;
    private Button btn_modificar, btn_eliminar;
    ConexionSQlite conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_articulo);
        Tv_id=(TextView)findViewById(R.id.Tv_id);
        Tv_producto=(TextView)findViewById(R.id.Tv_producto);
        Tv_categoria=(TextView)findViewById(R.id.Tv_categoria);
        Tv_cantidad=(TextView)findViewById(R.id.Tv_cantidad);
        Tv_precio=(TextView)findViewById(R.id.Tv_precio);
        Et_campoId=(TextView) findViewById(R.id.Et_campoId);
        Et_campoProducto=(EditText)findViewById(R.id.Et_campoProducto);
        Et_campoCategoria=(EditText) findViewById(R.id.Et_campoCategoria);
        Et_campoCantidad=(EditText) findViewById(R.id.Et_campoCantidad);
        Et_campoPrecio=(EditText) findViewById(R.id.Et_campoPrecio);
        btn_modificar=(Button)findViewById(R.id.btn_modificar);
        btn_eliminar=(Button)findViewById(R.id.btn_eliminar);
        //Iniciar la base de datos
        conn = new ConexionSQlite(this,"ElectronicInventory.db",null,1);

        //Recibir los datos detallados de la lista de articulos
        Bundle objetoEnviado = getIntent().getExtras();
        Articulos producto = null;
        if(objetoEnviado!=null){
            producto = (Articulos) objetoEnviado.getSerializable("articulo");
            //Convertir int y floats a strings para imprimirlos
            int Idarticulo = producto.getId_producto();
            String idarticulo= String.valueOf(Idarticulo);
            int cantidad = producto.getCantidad();
            // cantidad UPDATE ARTICULOS SET CANTIDAD
            String Cantidad= String.valueOf(cantidad);
            float precio = producto.getPrecio();
            String Precio= String.valueOf(precio);
            //Imprimirlos como strings
            Et_campoId.setText(idarticulo);//Convertido
            //Et_campoId.setText(producto.getId_producto());//Sin convertir
            Et_campoProducto.setText((producto.getProducto().toString()));
            Et_campoCategoria.setText(producto.getCategoria().toString());
            Et_campoCantidad.setText(Cantidad);
            Et_campoPrecio.setText(Precio);
            //
           // Et_campoCantidad.setText((int)producto.getCantidad());//Sin convertir
            //Et_campoPrecio.setText((int) producto.getPrecio());//Sin convertir
        }
    }
    //Métodos para verificar el click del usuario en los botones
    public void OnClickInventario (View view){
        switch (view.getId()){
            case R.id.btn_modificar:
                ActuializarArticulo();
                break;
            case R.id.btn_eliminar:
                EliminarArticulo();
                break;
        }
    }
    //Método para eliminar un artículo
    private void EliminarArticulo() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametos = {Et_campoId.getText().toString()};
        db.delete(Utilidades.TABLA_ARTICULOS,Utilidades.CAMPO_ID+"=?", parametos);
        Toast.makeText(getApplicationContext(), "Se eliminó el artículo", Toast.LENGTH_SHORT).show();
        Et_campoId.setText("");
        Et_campoProducto.setText("");
        Et_campoCategoria.setText("");
        Et_campoCantidad.setText("");
        Et_campoPrecio.setText("");
        db.close();
    }

    //Método para modificar datos en la tabla Articulos
    private void ActuializarArticulo(){
        //Abrir la base de datos para su escritura
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametos = {Et_campoId.getText().toString()};
            ContentValues values = new ContentValues();
            values.put(Utilidades.CAMPO_PRODUCTO, Et_campoProducto.getText().toString());
            values.put(Utilidades.CAMPO_CATEGORIA, Et_campoCategoria.getText().toString());
            values.put(Utilidades.CAMPO_CANTIDAD, Et_campoCantidad.getText().toString());
            values.put(Utilidades.CAMPO_PRECIO, Et_campoPrecio.getText().toString());
            db.update(Utilidades.TABLA_ARTICULOS, values,Utilidades.CAMPO_ID+"=?", parametos);
            Toast.makeText(getApplicationContext(), "Se actualizo el artículo", Toast.LENGTH_SHORT).show();
            db.close();
    }

}