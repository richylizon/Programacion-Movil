package com.loopwiki.loginregisterwithsqlite.peso_madre;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.loopwiki.loginregisterwithsqlite.R;
import com.loopwiki.loginregisterwithsqlite.peso_madre.Utilidades.Utilidades;

import java.text.DecimalFormat;
import java.util.Calendar;

public class Peso_Madre_Activity extends AppCompatActivity {

    private Button bFechaInical,bFechaActual;
    private int dia,mes,ano;
    private EditText et_pesoIncial,et_fechaInicial,et_pesoActual,et_fechaActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peso_);

        bFechaInical = (Button)findViewById(R.id.bFecha);
        bFechaActual = (Button)findViewById(R.id.bFecha2);

        et_pesoIncial = (EditText)findViewById(R.id.etPeso);
        et_fechaInicial = (EditText)findViewById(R.id.etFecha);
        et_pesoActual = (EditText)findViewById(R.id.etPeso2);
        et_fechaActual = (EditText)findViewById(R.id.etFecha2);
    }

    /*
    metodo para el boton fecha inicial
     */
    public void onClickInicial(View v) {
        if(v==bFechaInical){
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            ano = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

                    et_fechaInicial.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);

                }
            },dia,mes,ano);
            datePickerDialog.show();

        }
    }
    /*
    metodo para el boton fecha actual
     */
    public void onClickActual(View v) {
        if(v==bFechaActual){
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            ano = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

                    et_fechaActual.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);

                }
            },dia,mes,ano);
            datePickerDialog.show();

        }
    }

    /*
    Metodo para el boton guardar
     */
    public void guardar(View view){
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"db_pesos",null,1);

        SQLiteDatabase db = conn.getWritableDatabase();
        String pesoInicial = et_pesoIncial.getText().toString();
        String fechaInicial = et_fechaInicial.getText().toString();
        String pesoActual = et_pesoActual.getText().toString();
        String fechaActual = et_fechaActual.getText().toString();
        if (!pesoInicial.isEmpty() && !pesoActual.isEmpty()) {
            ContentValues values = new ContentValues();

            values.put(Utilidades.CAMPO_PESOINICIAL, pesoInicial);
            values.put(Utilidades.CAMPO_FECHAINICIAL, fechaInicial);
            values.put(Utilidades.CAMPO_PESOACTUAL, pesoActual);
            values.put(Utilidades.CAMPO_FECHAACTUAL, fechaActual);

            db.insert(Utilidades.TABLA_PESO, null, values);
            db.close();
            et_pesoIncial.setText("");
            et_fechaInicial.setText("");
            et_pesoActual.setText("");
            et_fechaActual.setText("");

            Toast.makeText(this,"Registrado", Toast.LENGTH_SHORT).show();
            irActividadReporte(view,fechaActual,pesoInicial,pesoActual);
        }else{
            Toast.makeText(this,"Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    private void irActividadReporte(View view, String fechaActual, String pesoInicial, String pesoActual) {
        Intent siguiente = new Intent(this,SegundoActivity.class);
        siguiente.putExtra("fecha",fechaActual);
        siguiente.putExtra("pesoI",pesoInicial);
        siguiente.putExtra("pesoA",pesoActual);

        double pesoA = Double.parseDouble(pesoActual);
        double pesoI = Double.parseDouble(pesoInicial);

        double resta = (pesoA - pesoI);
        DecimalFormat cambio = new DecimalFormat("#.0");
        double resta1 = Double.parseDouble(cambio.format(resta));
        String res = Double.toString(resta1);

        siguiente.putExtra("aumento",res);

        startActivity(siguiente);
    }
}
