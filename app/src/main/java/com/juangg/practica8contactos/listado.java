package com.juangg.practica8contactos;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class listado extends AppCompatActivity {

    ListView listbase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        listbase = (ListView) findViewById(R.id.listBase);


        // Declaro mi objeto helper que me ayuda en la
        // operaciones de base de datos (logica)
        BDDHelper Helper = new BDDHelper(this);

        //base de datos modo lectura
        SQLiteDatabase db = Helper.getReadableDatabase();


        Cursor c = db.rawQuery("SELECT * FROM "+ BDDestructura.TABLE_NAME,null);



        ArrayList<cContacto> listaContactos = new ArrayList<cContacto>();
        while(c.moveToNext()) {

            listaContactos.add(new cContacto(c.getString(0),c.getString(1),
                    c.getString(2),c.getString(3)));

        }
        c.close();

        //Preparar el adaptador para mi array de obetos
        ContactosAdapter contactoAdapter = new ContactosAdapter(this, R.layout.itemlist, listaContactos);



        listbase.setAdapter(contactoAdapter);



    }
}