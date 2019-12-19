package com.loopwiki.loginregisterwithsqlite;

public class Utilidades {

    //Constantes campos tabla diario
    public static final String TABLA_DIARIO = "diario";
    public static final String CAMPO_ID = "id";
    public static final String CAMPO_TITULO = "titulo";
    public static final String CAMPO_DESCRIPCION = "descripcion";
    public static final String CAMPO_FECHA = "fecha";

    public static final String CREAR_TABLA_DIARIO = "CREATE TABLE " +
            ""+TABLA_DIARIO+ "(" +
            CAMPO_ID+" " +"INTEGER PRIMARY KEY AUTOINCREMENT, "+
            CAMPO_TITULO+" TEXT,"+
            CAMPO_DESCRIPCION+" TEXT,"+
            CAMPO_FECHA+" TEXT)";
}
