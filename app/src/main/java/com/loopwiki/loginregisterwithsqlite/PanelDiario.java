package com.loopwiki.loginregisterwithsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

public class PanelDiario extends AppCompatActivity {

    private HeaderAdapter headerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //agregar el boton de atras
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_diario);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        //Disposicion de los Items, como se organizan los Items
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        //Datos que vienen de la BD
        ArrayList<Header> myDataset = new ArrayList<>();
        myDataset.add(new Header("188998","Segundo Ejemplo","9 de Diciembre de 2019",".d vV>rsbv.bubvr"));
        myDataset.add(new Header("234556","Tercer Ejemplo","10 de Diciembre de 2019",",knvkBVUKBEVV"));
        myDataset.add(new Header("345566","Cuarto Ejemplo","11 de Diciembre de 2019","JKVDSNLKESBKUU"));
        myDataset.add(new Header("488998","Segundo Ejemplo","9 de Diciembre de 2019",".linli.ubuv"));
        myDataset.add(new Header("534556","Tercer Ejemplo","10 de Diciembre de 2019",",jbb,kuyggukg"));
        myDataset.add(new Header("645566","Cuarto Ejemplo","11 de Diciembre de 2019","....uubytctc,vhvhvh"));
        myDataset.add(new Header("788998","Segundo Ejemplo","9 de Diciembre de 2019",".kkjbbyvyv"));
        myDataset.add(new Header("834556","Tercer Ejemplo","10 de Diciembre de 2019","123456789dfghhjk"));
        myDataset.add(new Header("945566","Cuarto Ejemplo","11 de Diciembre de 2019","nbvcfghjkoiuytrfg"));
        myDataset.add(new Header("108998","Segundo Ejemplo","9 de Diciembre de 2019",",ijmnuyhbgtrfcde"));
        myDataset.add(new Header("114556","Tercer Ejemplo","10 de Diciembre de 2019",",bvctvythyrdfghj"));
        myDataset.add(new Header("125566","Cuarto Ejemplo","11 de Diciembre de 2019","vacio"));



        //Asociar con un Adapter convertir datos en los items del recyclerView
        Collections.reverse(myDataset);
        headerAdapter = new HeaderAdapter(myDataset);
        recyclerView.setAdapter(headerAdapter);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.accionespaneldiario, menu);
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //funciones para el boton agregar y regresar
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        /*if(id == android.R.id.home){
            //termina el activity
            this.finish();*/
        /*}else*/ if(id == R.id.b_agregar){
            Intent i = new Intent(this,EscribirNota.class);
            startActivity(i);
        }
        else if(id == R.id.diario){
            Toast.makeText( this, "Diario",Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.info) {
            Intent intent=new Intent(this, Info_diaria.class);
            startActivity(intent);
        }
        else if(id == R.id.contracciones) {
            Intent intent=new Intent(this, Contracciones.class);
            startActivity(intent);
        }
        else if(id == R.id.semana_semana) {
            Intent intent =  new Intent(this, FetoSemana.class );
            startActivity(intent);
        }
        else if(id == R.id.salir){
            Intent intent=new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
