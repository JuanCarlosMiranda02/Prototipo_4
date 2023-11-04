package com.example.prototipo_4;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import com.example.prototipo_4.Entidades.Articulos;
import com.example.prototipo_4.Utilidades.Utilidades;

public class Ventas extends AppCompatActivity {
    private EditText Et_buscarIdproducto, Et_cantidadComprar;
    private Button btn_agregar, btn_buscarArticulo, btn_confirmarVenta;
    private TextView  Tv_Campoprecio, Tv_total, Tv_cantidadDisponible, Tv_nombreArticulo;
    private String DisminuirStock, Unidades;

    ConexionSQlite conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         conn = new ConexionSQlite(getApplicationContext(), "ElectronicInventory.db", null, 1);
        setContentView(R.layout.activity_ventas);
        Et_buscarIdproducto = (EditText) findViewById(R.id.Et_buscarIdproducto);
        Et_cantidadComprar=(EditText)findViewById((R.id.Et_cantidadComprar));
        Tv_Campoprecio = (TextView) findViewById(R.id.Tv_Campoprecio);
        Tv_total = (TextView) findViewById(R.id.Tv_total);
        btn_agregar = (Button) findViewById(R.id.btn_agregar);
        btn_buscarArticulo = (Button) findViewById(R.id.btn_buscarArticulo);
        Tv_cantidadDisponible = (TextView) findViewById(R.id.Tv_cantidadDisponible);
        btn_confirmarVenta = (Button) findViewById(R.id.btn_confirmarVenta);
        Tv_nombreArticulo=(TextView)findViewById(R.id.Tv_nombreArticulo);
        btn_buscarArticulo.setOnClickListener(accionBuscarArticulo);
        btn_confirmarVenta.setOnClickListener(accionVenta);
        btn_agregar.setOnClickListener(accionCalcular);

    }
    public  View.OnClickListener accionCalcular= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CalcularTotal();
        }
    };
    public  View.OnClickListener accionVenta=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            VentaNueva();
            ReducirStock();
        }
    };
    //Métodos para verificar el click del usuario en los botones
    public View.OnClickListener accionBuscarArticulo = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ConsultaVenta2();
        }
    };
    //Método para mostrar el total de la venta actualiado
    public void CalcularTotal(){
        String ssubTotal = Tv_Campoprecio.getText().toString();
        String sCantidad_Vendida = Et_cantidadComprar.getText().toString();
        //Convertirlo para calcular los valores
        int Subtotal = Integer.parseInt(ssubTotal);
        int cantidadVender = Integer.parseInt(sCantidad_Vendida);
        int Total = Subtotal * cantidadVender;
        String sTotal= String.valueOf(Total);
        Tv_total.setText(sTotal);
    }
    //FUNCIONA
    //Método para registrar una venta
    public void VentaNueva(){
        SQLiteDatabase db2 = conn.getWritableDatabase();
        String IdProducto=Et_buscarIdproducto.getText().toString();
        String ReducirStock=Et_cantidadComprar.getText().toString();
        if(!IdProducto.isEmpty() && !ReducirStock.isEmpty()) {
            ContentValues values2 = new ContentValues();
            values2.put(Utilidades.CAMPO_PRODUCTO_ID, Et_buscarIdproducto.getText().toString());
            values2.put(Utilidades.CAMPO_TOTAL, Tv_total.getText().toString());
            values2.put(Utilidades.CAMPO_REDUCIRCSTOCK, Et_cantidadComprar.getText().toString());
            //Guardar la variable para proximamente reducir el stock
            DisminuirStock = Et_cantidadComprar.getText().toString();
            int MenosStock = Integer.parseInt(DisminuirStock);
            int StockActual = Integer.parseInt(Unidades);
            if ( MenosStock > StockActual ) {
                Toast.makeText(this, "No hay stock suficiente para la venta", Toast.LENGTH_SHORT).show();
            }
            else{
                //Guardar la cantidad para calcular el total
                db2.insert(Utilidades.TABLA_VENTAS, null, values2);
                db2.close();
                Toast.makeText(this, "Venta realizada con éxito", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "Se deben llenar los campos requeridos", Toast.LENGTH_SHORT).show();
        }
    }

    //Método para reducir stock del producto a la hora de confirmar la venta
   public void ReducirStock(){
       SQLiteDatabase db2 = conn.getWritableDatabase();
       String[] parametros2 = {Et_buscarIdproducto.getText().toString()};
       ContentValues values2 = new ContentValues();
       //Convertirlo para disminuir el stock de articulos
       int Disminuir = Integer.parseInt(DisminuirStock);
       int StockDisponible = Integer.parseInt(Unidades);
       //Validar que no venda mas articulos de los que se tienen
       if (Disminuir > StockDisponible) {
           Toast.makeText(this, "No hay stock suficiente para la venta", Toast.LENGTH_SHORT).show();
       }
       else{
           int Resultado = StockDisponible - Disminuir;
           String ResultadoStock=String.valueOf(Resultado);
           //Insertar los nuevos valores de cantidad en la tabla productos
           values2.put(Utilidades.CAMPO_CANTIDAD, ResultadoStock);
           db2.update(Utilidades.TABLA_ARTICULOS, values2,Utilidades.CAMPO_ID+"=?", parametros2);
           Toast.makeText(getApplicationContext(), "Se actualizo el stock", Toast.LENGTH_SHORT).show();
           //Limpiar datos
           Tv_total.setText("");
           Et_buscarIdproducto.setText("");
           Tv_nombreArticulo.setText("");
           Tv_cantidadDisponible.setText("");
           Tv_Campoprecio.setText("");
           Et_cantidadComprar.setText("");
       }
   }
    //FUNCIONA
    //Método para buscar los datos esenciales del producto a buscar
    public void ConsultaVenta2() {
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] parametros = {Et_buscarIdproducto.getText().toString()};

        try {
            Cursor cursor2 = db.rawQuery("SELECT " + Utilidades.CAMPO_PRODUCTO + " , "+ Utilidades.CAMPO_CANTIDAD + " , " + Utilidades.CAMPO_PRECIO + " FROM " + Utilidades.TABLA_ARTICULOS + " WHERE " + Utilidades.CAMPO_ID + "=? ", parametros);
            cursor2.moveToFirst();
            Tv_nombreArticulo.setText(cursor2.getString(0));
            Tv_cantidadDisponible.setText(cursor2.getString(1));
            //Guardar las unidades disponibles para usaralr en un futuro
            Unidades=Tv_cantidadDisponible.getText().toString();
            //---------------------------------------------------
            Tv_Campoprecio.setText(cursor2.getString(2));
            //Obtener el valor del precio original para luego calcularlo según la cantidad deseada
            db.close();

        } catch (Exception e2) {
            Toast.makeText(getApplicationContext(), "El artículo no existe", Toast.LENGTH_SHORT).show();
            Et_buscarIdproducto.setText("");
            Tv_nombreArticulo.setText("");
            Tv_cantidadDisponible.setText("");
            Tv_Campoprecio.setText("");

        }
    }
}