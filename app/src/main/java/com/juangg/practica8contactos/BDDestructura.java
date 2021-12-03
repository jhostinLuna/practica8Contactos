package com.juangg.practica8contactos;

public class BDDestructura {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private BDDestructura() {}

    /* Inner class that defines the table contents */
   // public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "tbcontactos";
        public static final String COLUMN_CODIGO = "codigo";
        public static final String COLUMN_NOMBRE = "nombre";
        public static final String COLUMN_APELLIDO = "apellido";
        public static final String COLUMN_TELEFONO = "telefono";
    //}

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + BDDestructura.TABLE_NAME + " (" +
                    BDDestructura.COLUMN_CODIGO + " INTEGER PRIMARY KEY," +
                    BDDestructura.COLUMN_NOMBRE + " TEXT," +
                    BDDestructura.COLUMN_APELLIDO + " TEXT," +
                    BDDestructura.COLUMN_TELEFONO + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + BDDestructura.TABLE_NAME;

}
