package com.loopwiki.loginregisterwithsqlite;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.loopwiki.loginregisterwithsqlite.fragments.Etapa1;
import com.loopwiki.loginregisterwithsqlite.fragments.Etapa2;
import com.loopwiki.loginregisterwithsqlite.fragments.Ultima_etapa;
import com.loopwiki.loginregisterwithsqlite.ui.main.SectionsPagerAdapter;

public class FetoSemana extends AppCompatActivity implements Etapa1.OnFragmentInteractionListener,
        Etapa2.OnFragmentInteractionListener, Ultima_etapa.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feto_semana);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.diario){
            Intent intent =  new Intent(this, PanelDiario.class );
            startActivity(intent);
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
            Toast.makeText( this, "Informacion Semana a Semana y Tamaño del Feto",Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.salir){
            Intent intent=new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


}