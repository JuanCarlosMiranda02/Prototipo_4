package com.example.prototipo_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText Et_usuario;
    private EditText Et_contrasenia;
    private Button btn_inicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Et_contrasenia=(EditText)findViewById(R.id.Et_contrasenia);
        Et_usuario=(EditText)findViewById(R.id.Et_usuario);
        btn_inicio=(Button)findViewById(R.id.btn_inicio);
        //btn_inicio.setOnClickListener(ValidarInicio);
        ConexionSQlite conn = new ConexionSQlite(this, "InventarioElectronico", null, 1);
    }
    /*public View.OnClickListener ValidarInicio = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
    public void ValidarUsuario(View view){

    }*/
    public void IniciarSesion(View view){
        Intent IniciarPrograma = new Intent(this, Menu.class);
        startActivity(IniciarPrograma);
    }

}