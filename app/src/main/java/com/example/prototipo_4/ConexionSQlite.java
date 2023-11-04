package com.example.prototipo_4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.prototipo_4.Utilidades.Utilidades;

public class ConexionSQlite extends SQLiteOpenHelper {


    public ConexionSQlite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase Database){
        Database.execSQL(Utilidades.CREAR_TABLA_ARTICULOS);
        Database.execSQL(Utilidades.CREAR_TABLA_USUARIOS);
        Database.execSQL(Utilidades.CREAR_TABLA_VENTAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase Db, int oldVersion, int newVersion) {
        Db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_ARTICULOS);
        onCreate(Db);
        Db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_USUARIOS);
        onCreate(Db);
        Db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_VENTAS);
        onCreate(Db);
    }
}
