package com.example.prototipo_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {
    private Button btn_ventas, btn_inventario, btn_usuarios, btn_cerrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btn_ventas=(Button)findViewById(R.id.btn_ventas);
        btn_inventario=(Button)findViewById(R.id.btn_inventario);
        btn_usuarios=(Button)findViewById(R.id.btn_usuarios);
        btn_cerrar=(Button)findViewById(R.id.btn_cerrar);
        //btn_ventas.setOnClickListener((accionVentas));
    }
   /* public View.OnClickListener accionVentas= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_buscarArticulo :
                        IrVentas();
                        break;
                    case R.id.btn_inventario:
                        Inventario();
                        break;
                    case R.id.btn_usuarios:
                        IrUsuarios();
                        break;
                    case R.id.btn_cerrar :
                        IrVentas();
                        break;
                }
            }
    };
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_buscarArticulo :
                IrVentas();
                break;
            case R.id.btn_inventario:
                Inventario();
                break;
            case R.id.btn_usuarios:
                IrUsuarios();
                break;
                    case R.id.btn_cerrar :
                        IrVentas();
                        break;
        }
    }*/
    public void Inventario(View view){
        Intent irInventario = new Intent(this, Inventario.class);
        startActivity(irInventario);
    }
    public void IrUsuarios(View view){
        Intent irUsuarios = new Intent(this, Usuarios.class);
        startActivity(irUsuarios);
    }
    public void IrOpcionVentas(View view){
        Intent irOpcionVentas = new Intent(this, Opcion_venta.class);
        startActivity(irOpcionVentas);
    }
    public void CerrarSeison(View view){
        Intent Cerrar = new Intent(this, MainActivity.class);
        startActivity(Cerrar);
    }
}