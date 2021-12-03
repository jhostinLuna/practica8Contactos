package com.juangg.practica8contactos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnalta, btnbuscar, btnmodificar, btneliminar, btnbase, btncontacto;

    EditText etxtcodigo, etxtnombre, etxtapellido, etxttelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnalta = (Button) findViewById(R.id.btnalta);
        btnbuscar = (Button) findViewById(R.id.btnbuscar);
        btnmodificar = (Button) findViewById(R.id.btnmodificar);
        btneliminar = (Button) findViewById(R.id.btneliminar);
        btnbase = (Button) findViewById(R.id.btnbase);
        btncontacto = (Button) findViewById(R.id.btncontacto);

        etxtcodigo = (EditText) findViewById(R.id.etxtcodigo);
        etxtnombre = (EditText) findViewById(R.id.etxtnombre);
        etxtapellido = (EditText) findViewById(R.id.etxtapellido);
        etxttelefono = (EditText) findViewById(R.id.etxttelefono);


        // Declaro mi objeto helper que me ayuda en la
        // operaciones de base de datos (logica)
        BDDHelper Helper = new BDDHelper(this);


        btnalta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = Helper.getWritableDatabase();

                // Create a new map of values, where column names are the keys
                ContentValues values = new ContentValues();
                values.put(BDDestructura.COLUMN_CODIGO, etxtcodigo.getText().toString());
                values.put(BDDestructura.COLUMN_NOMBRE, etxtnombre.getText().toString());
                values.put(BDDestructura.COLUMN_APELLIDO, etxtapellido.getText().toString());
                values.put(BDDestructura.COLUMN_TELEFONO, etxttelefono.getText().toString());

                // Insert the new row, returning the primary key value of the new row
                long newRowId = db.insert(BDDestructura.TABLE_NAME, null, values);

                Toast.makeText(getApplicationContext(),"Contacto guardado"+newRowId,
                        Toast.LENGTH_LONG).show();

                reset(); //borrar campos del layout
            }
        });


        btnbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = Helper.getReadableDatabase();

                // Define a projection that specifies which columns from the database
                // you will actually use after this query.
                String[] projection = {
                        //BaseColumns._ID,
                        BDDestructura.COLUMN_NOMBRE,
                        BDDestructura.COLUMN_APELLIDO,
                        BDDestructura.COLUMN_TELEFONO
                };

                // Filter results WHERE "title" = 'My Title'
                String selection = BDDestructura.COLUMN_CODIGO + " = ?";
                String[] selectionArgs = { etxtcodigo.getText().toString() };

                // How you want the results sorted in the resulting Cursor
                //String sortOrder =
                 //       FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";

                try {

                    Cursor c = db.query(
                            BDDestructura.TABLE_NAME,   // The table to query
                            projection,             // The array of columns to return (pass null to get all)
                            selection,              // The columns for the WHERE clause
                            selectionArgs,          // The values for the WHERE clause
                            null,                   // don't group the rows
                            null,                   // don't filter by row groups
                            null               // The sort order
                    );


                    c.moveToFirst(); //empieza desde el primero

                    etxtnombre.setText(c.getString(0));
                    etxtapellido.setText(c.getString(1));
                    etxttelefono.setText(c.getString(2));

                    c.close();  //cerrar cursor

                } catch (Exception e){

                    Toast.makeText(getApplicationContext(),"Contacto no encontrado",
                            Toast.LENGTH_LONG).show();

                }




            }
        });


        btnmodificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = Helper.getWritableDatabase();

                // New value for one column

                ContentValues values = new ContentValues();
                values.put(BDDestructura.COLUMN_NOMBRE, etxtnombre.getText().toString());
                values.put(BDDestructura.COLUMN_APELLIDO, etxtapellido.getText().toString());
                values.put(BDDestructura.COLUMN_TELEFONO, etxttelefono.getText().toString());

                try {
                    // Which row to update, based on the title
                    String selection = BDDestructura.COLUMN_CODIGO + " LIKE ?";
                    String[] selectionArgs = { etxtcodigo.getText().toString() };

                    int count = db.update(
                            BDDestructura.TABLE_NAME,
                            values,
                            selection,
                            selectionArgs);

                    Toast.makeText(getApplicationContext(),"Contacto actualizado", Toast.LENGTH_LONG).show();

                    reset();

                } catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Contacto no actualizado", Toast.LENGTH_LONG).show();

                }


            }



        });


        btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gets the data repository in write mode
                SQLiteDatabase db = Helper.getWritableDatabase();

                // Define 'where' part of query.
                String selection = BDDestructura.COLUMN_CODIGO + " LIKE ?";
                // Specify arguments in placeholder order.
                String[] selectionArgs = { etxtcodigo.getText().toString() };
                // Issue SQL statement.

                try {
                    db.delete(BDDestructura.TABLE_NAME, selection, selectionArgs);

                    Toast.makeText(getApplicationContext(),"Contacto" + etxtcodigo.getText() +" borrado",
                            Toast.LENGTH_LONG).show();

                    reset();
                } catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Contacto NO borrado, no encontrado.",
                            Toast.LENGTH_LONG).show();
                }

            }
        });

        btnbase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), listado.class);
                startActivity(intent);


            }
        });

        btncontacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Creates a new Intent to insert a contact
                Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                // Sets the MIME type to match the Contacts Provider
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);



                intent.putExtra(ContactsContract.Intents.Insert.NAME, etxtnombre.getText() +" "+ etxtapellido.getText())

                        .putExtra(ContactsContract.Intents.Insert.PHONE, etxttelefono.getText())

                        .putExtra(ContactsContract.Intents.Insert.PHONE_TYPE,
                                ContactsContract.CommonDataKinds.Phone.TYPE_WORK);

                startActivity(intent);

            }
        });


    }



    public void reset(){
        etxtcodigo.setText("");
        etxtnombre.setText("");
        etxtapellido.setText("");
        etxttelefono.setText("");

    }

}