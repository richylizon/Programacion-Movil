package com.loopwiki.loginregisterwithsqlite;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
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

public class EditarEliminarNota extends AppCompatActivity implements View.OnClickListener{

    String titulo = "Editar Nota";
    private String codigo;
    private TextView tv_Fecha,tv_titulo,tv_descripcion;
    private Button b_Modificar;
    private int dia, mes, anio;
    final Calendar c = Calendar.getInstance();
    ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd_diario",null,1);
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
        //ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd_diario",null,1);

        tv_titulo = (TextView)findViewById(R.id.et_titulo1);
        tv_descripcion = (TextView)findViewById(R.id.et_descripcion1);
        tv_Fecha = (TextView)findViewById(R.id.et_fecha1);
        b_Modificar = (Button)findViewById(R.id.b_modificar1);

        mostrarDatos();
    }

    private void mostrarDatos() {
        //Recupero id del codigo, para obtener los demas datos de la BD
        //String idCodigo = getIntent().getStringExtra("valor_id");
        String fecha = getIntent().getStringExtra("valor_fecha");
        String titulo = getIntent().getStringExtra("valor_titulo");
        String descripcion = getIntent().getStringExtra("valor_descripcion");

        tv_titulo.setText(titulo);
        tv_Fecha.setText(fecha);
        tv_descripcion.setText(descripcion);
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
        getMenuInflater().inflate(R.menu.accioneseditareliminarnota, menu);
        return true;
    }

    //funciones para el boton agregar y regresar
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == android.R.id.home){
            this.finish();
        }else if(id == R.id.b_guardar){
            actualizarNota();
            Toast.makeText(this,"Guardando...", Toast.LENGTH_SHORT).show();
            this.finish();
        }else if(id == R.id.b_eliminar){
            mostrarDialogo();
            //eliminarNota();
            //Toast.makeText(this,"Eliminando...",Toast.LENGTH_SHORT).show();
            //this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void mostrarDialogo() {
        new AlertDialog.Builder(this).setTitle("")
                .setMessage("¿Eliminar nota?").setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                eliminarNota();
                finish();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        }).setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        }).show();

    }

    private void actualizarNota() {
        String codigo = getIntent().getStringExtra("valor_id");
        String[] parametros= {codigo};
        //ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd_diario",null,1);
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_FECHA, tv_Fecha.getText().toString());
        values.put(Utilidades.CAMPO_TITULO, tv_titulo.getText().toString());
        values.put(Utilidades.CAMPO_DESCRIPCION, tv_descripcion.getText().toString());

        db.update(Utilidades.TABLA_DIARIO,values,Utilidades.CAMPO_ID + "=?",parametros);
        db.close();

    }

    private void eliminarNota() {
        String codigo = getIntent().getStringExtra("valor_id");
        //ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd_diario",null,1);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.execSQL("DELETE FROM " + Utilidades.TABLA_DIARIO + " WHERE " + Utilidades.CAMPO_ID + " = " + codigo);
        db.close();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
