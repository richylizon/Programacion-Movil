package com.loopwiki.loginregisterwithsqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class EscribirNota extends AppCompatActivity implements View.OnClickListener {

    private String titulo = "Escribir Nota";
    private String fecha;
    private TextView tv_titulo,tv_descripcion;
    private int dia, mes, anio;
    final Calendar c = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escribir_nota);

        this.setTitle(titulo);

        //agregar el boton de atras
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //conexion con la base de datos
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd_diario",null,1);

        tv_titulo = (TextView)findViewById(R.id.et_titulo_e);
        tv_descripcion = (TextView)findViewById(R.id.et_descripcion_e);
        fecha = obtenerFecha();
    }

    private String obtenerFecha() {
        dia = c.get(Calendar.DAY_OF_MONTH);
        mes = c.get(Calendar.MONTH) + 1;
        anio = c.get(Calendar.YEAR);

        return dia + "/" + mes + "/" + anio;
    }

    @Override
    public void onClick(View view){ }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.accionesescribirnota, menu);
        return true;
    }

    //funciones para el boton agregar y regresar
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == android.R.id.home){
            this.finish();
        }else if(id == R.id.b_guardar){
            registrarNota();
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void registrarNota() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd_diario",null,1);
        SQLiteDatabase db = conn.getWritableDatabase();
        String aux_titulo = tv_titulo.getText().toString();
        String aux_descripcion = tv_descripcion.getText().toString();

        if(tv_descripcion.getText().toString()==null){
            Toast.makeText(getApplicationContext(),"Debes ingresar una descripcion",Toast.LENGTH_SHORT).show();
        }else{
            ContentValues valores = new ContentValues();

            valores.put(Utilidades.CAMPO_TITULO,aux_titulo);
            valores.put(Utilidades.CAMPO_DESCRIPCION,aux_descripcion);
            valores.put(Utilidades.CAMPO_FECHA,obtenerFecha());

            long idResultante = db.insert(Utilidades.TABLA_DIARIO,Utilidades.CAMPO_ID,valores);
        }
        db.close();
        Toast.makeText(this,"Guardando...",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) { }
}