package com.loopwiki.loginregisterwithsqlite.peso_madre.Utilidades;

public class Utilidades {

    public static final String TABLA_PESO="pesos";
    public static final String CAMPO_PESOINICIAL="pesoInicial";
    public static final String CAMPO_FECHAINICIAL="fechaInicial";
    public static final String CAMPO_PESOACTUAL="pesoActual";
    public static final String CAMPO_FECHAACTUAL="fechaActual";

    public static final String CREAR_TABLA_PESOS = "CREATE TABLE "+TABLA_PESO+" ( "+
            CAMPO_PESOINICIAL+" FLOAT, "+
            CAMPO_FECHAINICIAL+" TEXT, "+
            CAMPO_PESOACTUAL+" FLOAT, "+
            CAMPO_FECHAACTUAL+" TEXT)";
}
