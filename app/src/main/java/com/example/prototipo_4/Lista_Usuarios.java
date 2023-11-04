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
import com.example.prototipo_4.Utilidades.Utilidades;

import java.util.ArrayList;

public class Lista_Usuarios extends AppCompatActivity {
    ArrayList<String> listaInformacionUsuarios;
    ArrayList<Usuarios> listaUsuarios;
    ListView listviewUsuarios;
    ConexionSQlite conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuarios);

        listviewUsuarios=(ListView)findViewById(R.id.Lv_ListaUsuarios);
        conn = new ConexionSQlite(getApplicationContext(),"ElectronicInventory.db", null,1);
        consultarlistaUsuarios();
        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInformacionUsuarios);
        listviewUsuarios.setAdapter(adaptador);
        //Mostrar datos completos en un mensaje
        listviewUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                String informacionUsuario = "Id_usuario "+listaUsuarios.get(pos).getId_usuario()+ "\n";
                informacionUsuario+="Nombre: "+listaUsuarios.get(pos).getNombre()+ "\n";
                informacionUsuario+="Contrasenia: "+listaUsuarios.get(pos).getContrasenia()+ "\n";
                informacionUsuario+="Tipo: "+listaUsuarios.get(pos).getTipo()+ "\n";
                //Guardar datos para enviarlos a otra activdad más detallada
                Usuarios usuario = listaUsuarios.get(pos);
                Intent IrDetallesUsuarios = new Intent(Lista_Usuarios.this,Detalles_usuarios.class);
                Bundle bundle= new Bundle();
                bundle.putSerializable("usuario", usuario);
                IrDetallesUsuarios.putExtras(bundle);
                startActivity(IrDetallesUsuarios);
            }
        });
    }
    // Consulta a la base de datos
    private void consultarlistaUsuarios() {
        SQLiteDatabase db = conn.getReadableDatabase();
        Usuarios usuario = null;
        listaUsuarios= new ArrayList<Usuarios>();
        //Select * from Usuarios
        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_USUARIOS, null);
        while (cursor.moveToNext() ){
            usuario= new Usuarios();
            usuario.setId_usuario(cursor.getInt(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setContrasenia(cursor.getString(2));
            usuario.setTipo(cursor.getString(3));
            listaUsuarios.add(usuario);

        }
        obtenerListausuarios();
    }

    private void obtenerListausuarios() {
        listaInformacionUsuarios = new ArrayList<String>();
        for (int i=0; i<listaUsuarios.size(); i++){
            listaInformacionUsuarios.add(listaUsuarios.get(i).getId_usuario()+" - "+listaUsuarios.get(i).getNombre());
        }
    }

}