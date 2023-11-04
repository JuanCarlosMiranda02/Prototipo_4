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

import com.example.prototipo_4.Entidades.Usuarios;
import com.example.prototipo_4.Utilidades.Utilidades;

public class Detalles_usuarios extends AppCompatActivity {
    private EditText  Et_campoContrasenia, Et_campoTipo, Et_campoNombre;
    private TextView Txt_idusuario, Txt_nombres, Txt_contrasenia, Txt_tipo,Et_campoidUsuario;
    private Button btn_modificarUsuario, btn_eliminarUsuario;
    ConexionSQlite conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_usuarios);
        Et_campoidUsuario=(TextView) findViewById(R.id.Et_campoidUsuario);
        Et_campoNombre=(EditText)findViewById(R.id.Et_campoNombre);
        Et_campoContrasenia=(EditText)findViewById(R.id.Et_campoContrasenia);
        Et_campoTipo=(EditText)findViewById(R.id.Et_campoTipo);
        Txt_idusuario=(TextView)findViewById(R.id.Txt_idusuario);
        Txt_nombres=(TextView)findViewById(R.id.Txt_nombres);
        Txt_contrasenia=(TextView)findViewById(R.id.Txt_contrasenia);
        Txt_tipo=(TextView)findViewById(R.id.Txt_tipo);
        btn_modificarUsuario=(Button)findViewById(R.id.btn_modificarUsuario);
        btn_eliminarUsuario=(Button)findViewById(R.id.btn_eliminarUsuario);
        //Iniciar la base de datos
        conn = new ConexionSQlite(this,"ElectronicInventory.db",null,1);

        //Recibir los datos detallados de la lista de articulos
        Bundle objetoEnviado = getIntent().getExtras();
        Usuarios usuario = null;
        if(objetoEnviado!=null){
            usuario = (Usuarios) objetoEnviado.getSerializable("usuario");
            //Convertir int y floats a strings para imprimirlos
            int Idusuario = usuario.getId_usuario();

            String idusuario= String.valueOf(Idusuario);
            //Imprimirlos como strings
            Et_campoidUsuario.setText(idusuario);//Convertido
            Et_campoNombre.setText((usuario.getNombre().toString()));
            Et_campoContrasenia.setText(usuario.getContrasenia().toString());
            Et_campoTipo.setText(usuario.getTipo().toString());
        }
    }
    //Métodos para verificar el click del usuario en los botones
    public void OnClickUser (View view){
        switch (view.getId()){
            case R.id.btn_modificarUsuario:
                ActualizarUsuario();
                break;
            case R.id.btn_eliminarUsuario:
                EliminarUsuario();
                break;
        }
    }
    //Método para eliminar un usuario
    private void EliminarUsuario() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametos = {Et_campoidUsuario.getText().toString()};
        db.delete(Utilidades.TABLA_USUARIOS,Utilidades.CAMPO_ID_USER+"=?", parametos);
        Toast.makeText(getApplicationContext(), "Se eliminó al usuario", Toast.LENGTH_SHORT).show();
        Et_campoidUsuario.setText("");
        Et_campoNombre.setText("");
        Et_campoContrasenia.setText("");
        Et_campoTipo.setText("");
        db.close();
    }

    //Método para modificar datos en la tabla Usuarios
    private void ActualizarUsuario(){
        //Abrir la base de datos para su escritura
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametos = {Et_campoidUsuario.getText().toString()};
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRE, Et_campoNombre.getText().toString());
        values.put(Utilidades.CAMPO_CONTRASENIA, Et_campoContrasenia.getText().toString());
        values.put(Utilidades.CAMPO_TIPO, Et_campoTipo.getText().toString());
        db.update(Utilidades.TABLA_USUARIOS, values,Utilidades.CAMPO_ID_USER+"=?", parametos);
        Toast.makeText(getApplicationContext(), "Se actualizo al usuario", Toast.LENGTH_SHORT).show();
        db.close();

    }

}
