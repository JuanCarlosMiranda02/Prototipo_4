package com.example.prototipo_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prototipo_4.Utilidades.Utilidades;

public class Inventario extends AppCompatActivity {
    private EditText Et_producto;
    private EditText Et_categoria;
    private EditText Et_id;
    private EditText Et_cantidad;
    private EditText Et_precio;
    private Button btn_alta;
    private Button btn_editar;
    private Button btn_baja;
    private Button btn_buscar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario);
        Et_id = (EditText) findViewById((R.id.Et_id));
        Et_producto = (EditText) findViewById((R.id.Et_producto));
        Et_categoria = (EditText) findViewById((R.id.Et_categoria));
        Et_cantidad = (EditText) findViewById((R.id.Et_cantidad));
        Et_precio = (EditText) findViewById((R.id.Et_precio));
        btn_alta = (Button) findViewById((R.id.btn_alta));
        btn_buscar = (Button) findViewById((R.id.btn_buscar));
        ConexionSQlite conn = new ConexionSQlite(this, "InventarioElectronico", null, 1);
    }
    //FUNCIONAAAAAAAAAAAAAAAAAAAAAAAA
    //Método para registrar articulos
   public void Registrar(View view){
        ConexionSQlite conn = new ConexionSQlite(this, "ElectronicInventory.db",null,1);
        SQLiteDatabase DataBase= conn.getWritableDatabase();
        String Id_producto = Et_id.getText().toString();
        String Producto = Et_producto.getText().toString();
        String Categoria = Et_categoria.getText().toString();
        String Cantidad = Et_cantidad.getText().toString();
        String Precio = Et_precio.getText().toString();

        if(!Cantidad.isEmpty() && !Categoria.isEmpty() && !Precio.isEmpty() && !Producto.isEmpty() && !Id_producto.isEmpty()){
            ContentValues values = new ContentValues();
            values.put(Utilidades.CAMPO_ID, Et_id.getText().toString());

            values.put(Utilidades.CAMPO_PRODUCTO, Et_producto.getText().toString());
            values.put(Utilidades.CAMPO_CATEGORIA, Et_categoria.getText().toString());
            values.put(Utilidades.CAMPO_CANTIDAD, Et_cantidad.getText().toString());

            values.put(Utilidades.CAMPO_PRECIO, Et_precio.getText().toString());

            DataBase.insert(Utilidades.TABLA_ARTICULOS, null, values);
            DataBase.close();
            Et_id.setText("");
            Et_producto.setText("");
            Et_precio.setText("");
            Et_categoria.setText("");
            Et_cantidad.setText("");
            Et_precio.setText("");
            Toast.makeText(this, "Operación realizada con éxito", Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(this, "Se deben llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
    public void IrLista(View view){
        Intent ConsultarLista = new Intent(this, Lista_articulos.class);
        startActivity(ConsultarLista);
    }
}
