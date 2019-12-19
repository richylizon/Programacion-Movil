package com.loopwiki.loginregisterwithsqlite;


import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class EscribirNota extends AppCompatActivity implements View.OnClickListener {

    String titulo = "Escribir Nota";

    private TextView tv_Fecha,tv_titulo,tv_descripcion;
    private Button b_Modificar;
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
        //

        //conexion con la base de datos
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd_diario",null,1);

        tv_titulo = (TextView)findViewById(R.id.et_titulo1);
        tv_descripcion = (TextView)findViewById(R.id.et_descripcion1);
        tv_Fecha = (TextView)findViewById(R.id.et_fecha1);
        b_Modificar = (Button)findViewById(R.id.b_modificar1);

        b_Modificar.setOnClickListener(this);
        tv_Fecha.setText(c.get(Calendar.DAY_OF_MONTH)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.YEAR));

    }

    @Override
    public void onClick(View view){
        if(view == b_Modificar){
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            anio = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int y, int m, int d) {
                    tv_Fecha.setText(d+"/"+(m+1)+"/"+y);
                }
            }
                    ,anio,mes,dia);
            datePickerDialog.show();
        }
    }

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
            //Toast.makeText(this,"Guardando...",Toast.LENGTH_SHORT).show();
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
            Toast.makeText(getApplicationContext(),"Debes ingresar una descripcion", Toast.LENGTH_SHORT).show();
        }else{
            ContentValues valores = new ContentValues();

            valores.put(Utilidades.CAMPO_TITULO,aux_titulo);
            valores.put(Utilidades.CAMPO_DESCRIPCION,aux_descripcion);
            valores.put(Utilidades.CAMPO_FECHA,tv_Fecha.getText().toString());

            long idResultante = db.insert(Utilidades.TABLA_DIARIO,Utilidades.CAMPO_ID,valores);
        }
        db.close();
        Toast.makeText(this,"Guardando...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}