package com.example.prototipo_4;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prototipo_4.Utilidades.Utilidades;

public class Usuarios extends AppCompatActivity {
    private EditText Et_idUsuario, Et_nombre, Et_tipo, Et_Contresenia;
    private Button btn_consultaUser, btn_altaUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);
        Et_idUsuario=(EditText)findViewById(R.id.Et_idUsuario);
        Et_nombre=(EditText)findViewById(R.id.Et_nombre);
        Et_tipo=(EditText)findViewById(R.id.Et_tipo);
        Et_Contresenia=(EditText)findViewById(R.id.Et_Contrsenia);
        btn_altaUser=(Button)findViewById(R.id.btn_altaUser);
        btn_consultaUser=(Button)findViewById(R.id.btn_consultaUser);
        ConexionSQlite conn = new ConexionSQlite(this, "InventarioElectronico", null, 1);
    }
    //Método para registrar usuarios
    public void RegistrarUsuarios(View view){

        ConexionSQlite conn = new ConexionSQlite(this, "ElectronicInventory.db",null,1);
        SQLiteDatabase DataBase= conn.getWritableDatabase();
        String Id_user = Et_idUsuario.getText().toString();
        String nombre = Et_nombre.getText().toString();
        String contrasenia = Et_Contresenia.getText().toString();
        String tipo = Et_tipo.getText().toString();

        if(!Id_user.isEmpty() && !nombre.isEmpty() && !contrasenia.isEmpty() && !tipo.isEmpty()){
            ContentValues values = new ContentValues();
            values.put(Utilidades.CAMPO_ID_USER, Et_idUsuario.getText().toString());
            values.put(Utilidades.CAMPO_NOMBRE, Et_nombre.getText().toString());
            values.put(Utilidades.CAMPO_CONTRASENIA, Et_Contresenia.getText().toString());
            values.put(Utilidades.CAMPO_TIPO, Et_tipo.getText().toString());

            DataBase.insert(Utilidades.TABLA_USUARIOS, null, values);
            DataBase.close();
            Et_idUsuario.setText("");
            Et_nombre.setText("");
            Et_Contresenia.setText("");
            Et_tipo.setText("");
            Toast.makeText(this, "Operación realizada con éxito", Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(this, "Se deben llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
    //Método para ir a la lista de usuarios
    public void IrListaUsuarios(View view){
        Intent ConsultarListaUsuarios = new Intent(this, Lista_Usuarios.class);
        startActivity(ConsultarListaUsuarios);
    }
}