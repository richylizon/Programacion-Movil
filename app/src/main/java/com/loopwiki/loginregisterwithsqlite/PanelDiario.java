package com.loopwiki.loginregisterwithsqlite;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

public class PanelDiario extends AppCompatActivity {

    ArrayList<NotasVo> listaNotas;
    RecyclerView recyclerNotas;

    ConexionSQLiteHelper conn;

    String titulo = "Diario";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_diario);

        this.setTitle(titulo);

        //agregar el boton de atras
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        actualizar();

    }

    private void actualizar(){
        listaNotas = new ArrayList<>();
        conn = new ConexionSQLiteHelper(getApplicationContext(),"bd_diario",null,1);

        recyclerNotas = (RecyclerView)findViewById(R.id.RecyclerId);
        recyclerNotas.setLayoutManager(new LinearLayoutManager(this));

        consultarDiario();

        //llenarPersonajes();
        Collections.reverse(listaNotas);
        AdapterDatos adapter = new AdapterDatos(listaNotas);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),
                        //"Seleccion " + listaNotas.get(recyclerNotas.getChildAdapterPosition(view)).getTitulo(),
                        //Toast.LENGTH_SHORT).show();
                String p_id = listaNotas.get(recyclerNotas.getChildAdapterPosition(view)).getCodigo();
                String p_fecha = listaNotas.get(recyclerNotas.getChildAdapterPosition(view)).getFecha();
                String p_titulo = listaNotas.get(recyclerNotas.getChildAdapterPosition(view)).getTitulo();
                String p_descripcion = listaNotas.get(recyclerNotas.getChildAdapterPosition(view)).getDescripcion();
                pasarDatos(p_id, p_fecha, p_titulo, p_descripcion);

            }
        });

        recyclerNotas.setAdapter(adapter);
    }

    private void pasarDatos(String id, String fecha, String titulo, String descripcion) {
        SQLiteDatabase db = conn.getReadableDatabase();

        Intent i = new Intent(this,EditarEliminarNota.class);
        i.putExtra("valor_id",id);
        i.putExtra("valor_fecha",fecha);
        i.putExtra("valor_titulo",titulo);
        i.putExtra("valor_descripcion",descripcion);
        startActivity(i);
    }

    private void consultarDiario() {
        SQLiteDatabase db = conn.getReadableDatabase();
        NotasVo nota = null;

        //obtencion de los datos
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_DIARIO,null);

        //recorrido de los datos
        while(cursor.moveToNext()){
            nota = new NotasVo();
            nota.setCodigo(cursor.getString(0));
            nota.setTitulo(cursor.getString(1));
            nota.setDescripcion(cursor.getString(2));
            nota.setFecha(cursor.getString(3));

            listaNotas.add(nota);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.accionespaneldiario, menu);
        return true;
    }

    //funciones para el boton agregar y regresar
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == android.R.id.home){
            //termina el activity
            this.finish();
        }else if(id == R.id.b_agregar){
            Intent i = new Intent(this,EscribirNota.class);
            startActivity(i);
        }else if(id==R.id.b_refrescar){
            actualizar();
            Toast.makeText(this,"Diario actualizado", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
