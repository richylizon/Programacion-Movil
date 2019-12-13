package com.loopwiki.loginregisterwithsqlite;

import android.app.DatePickerDialog;
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

    private TextView et_Fecha;
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

        et_Fecha = (TextView)findViewById(R.id.et_fecha);
        b_Modificar = (Button)findViewById(R.id.b_modificar);

        b_Modificar.setOnClickListener(this);
        et_Fecha.setText(c.get(Calendar.DAY_OF_MONTH)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.YEAR));
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
                    et_Fecha.setText(d+"/"+(m+1)+"/"+y);
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
            //termina el activity
            this.finish();
        }else if(id == R.id.b_guardar){
            //implementar guardar en base de datos
            Toast.makeText(this,"Guardando...", Toast.LENGTH_SHORT).show();
            this.finish();
        }else if(id == R.id.b_eliminar){
            //implementar eliminar en base de datos
            Toast.makeText(this,"Eliminando...", Toast.LENGTH_SHORT).show();
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
