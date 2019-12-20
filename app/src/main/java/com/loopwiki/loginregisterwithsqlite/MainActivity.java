package com.loopwiki.loginregisterwithsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.loopwiki.loginregisterwithsqlite.ecografias.Eco_Activity;
import com.loopwiki.loginregisterwithsqlite.peso_madre.Peso_Madre_Activity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void Diarioper(View view)
    {
        Intent diario= new Intent(this, PanelDiario.class);
        startActivity(diario);
    }

    public void Info(View view)
    {
        Intent info= new Intent(this, Info_diaria.class);
        startActivity(info);
    }

    public void Contracciones(View view)
    {
        Intent contracciones= new Intent(this, Contracciones.class);
        startActivity(contracciones);
    }

    public void Info_semanal_feto(View view)
    {
        Intent info_se= new Intent(this, FetoSemana.class);
        startActivity(info_se);
    }

    public void Eco(View view)
    {
        Intent eco= new Intent(this, Eco_Activity.class);
        startActivity(eco);
    }
    public void Peso(View view)
    {
        Intent peso= new Intent(this, Peso_Madre_Activity.class);
        startActivity(peso);
    }
    public void Salir(View view)
    {
        finish();
    }

}
