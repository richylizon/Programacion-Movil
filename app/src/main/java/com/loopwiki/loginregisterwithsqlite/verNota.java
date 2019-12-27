package com.loopwiki.loginregisterwithsqlite;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class verNota extends AppCompatActivity implements View.OnClickListener{

    String titulo = "Ver Nota";
    private TextView tv_titulo,tv_descripcion;
    ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd_diario",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_nota);

        this.setTitle(titulo);

        //agregar el boton de atras
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tv_titulo = (TextView)findViewById(R.id.tv_titulo_v);
        tv_descripcion = (TextView)findViewById(R.id.tv_descripcion_v);

        mostrarDatos();
    }

    private void mostrarDatos() {
        String titulo = getIntent().getStringExtra("valor_tituloj");
        String descripcion = getIntent().getStringExtra("valor_descripcionj");
        tv_titulo.setText(titulo);
        tv_descripcion.setText(descripcion);
    }

    @Override
    public void onClick(View view){ }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.accionesvernota, menu);
        return true;
    }

    //funciones para el boton agregar y regresar
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == android.R.id.home){
            this.finish();
        }else if(id == R.id.b_eliminar){
            mostrarDialogo();
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

    private void eliminarNota() {
        String codigo = getIntent().getStringExtra("valor_idj");
        SQLiteDatabase db = conn.getWritableDatabase();

        db.execSQL("DELETE FROM " + Utilidades.TABLA_DIARIO + " WHERE " + Utilidades.CAMPO_ID + " = " + codigo);
        db.close();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) { }
}
