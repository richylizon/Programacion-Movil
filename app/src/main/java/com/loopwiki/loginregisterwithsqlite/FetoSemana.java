package com.loopwiki.loginregisterwithsqlite;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

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
       // FloatingActionButton fab = findViewById(R.id.fab);

        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        //agregar el boton de atras
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    //funciones para el boton agregar y regresar


}