package com.loopwiki.loginregisterwithsqlite;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.loopwiki.loginregisterwithsqlite.citas_medicas.CreateNote;
import com.loopwiki.loginregisterwithsqlite.citas_medicas.Medic;
import com.loopwiki.loginregisterwithsqlite.ecografias.Eco_Activity;
import com.loopwiki.loginregisterwithsqlite.login2.database.DatabaseManagerUser;
import com.loopwiki.loginregisterwithsqlite.login2.entity.User;
import com.loopwiki.loginregisterwithsqlite.peso_madre.Peso_Madre_Activity;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DatabaseManagerUser databaseManagerUser;
    private User itemUsuario;
    private String ident;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main2);
        //se agrego codigo del 39 al 68
        Bundle b = getIntent().getExtras();

        ident = b.getString("IDENT");

        databaseManagerUser= new DatabaseManagerUser(getApplicationContext());
        itemUsuario = databaseManagerUser.getUsuario(ident); // encuentra al usuario registrado en la bbdd

        View header = ((NavigationView)findViewById(R.id.nav_view)).getHeaderView(0);

        ((TextView) header.findViewById(R.id.tv_nombre_usuario_menu)).setText(itemUsuario.getNombre());
        ((TextView) header.findViewById(R.id.tv_correo_menu)).setText(itemUsuario.getCorreo());

        Bitmap bitmapsinfoto = BitmapFactory.decodeResource(getResources(),R.drawable.imagen);
        RoundedBitmapDrawable roundedBitmapDrawablesinfoto = RoundedBitmapDrawableFactory.create(getResources(), bitmapsinfoto);
        roundedBitmapDrawablesinfoto.setCircular(true);

       ((ImageView) header.findViewById(R.id.imageView)).setImageDrawable(roundedBitmapDrawablesinfoto);

      /*  if(itemUsuario.getBytes()!=null){
            byte[] foodImage = itemUsuario.getBytes();
            Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);

            ((ImageView) header.findViewById(R.id.imageView)).setImageBitmap(bitmap);

            Bitmap bitmap2 = ((BitmapDrawable)((ImageView) header.findViewById(R.id.imageView)).getDrawable()).getBitmap();
            RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap2);
            roundedBitmapDrawable.setCircular(true);

            ((ImageView) header.findViewById(R.id.imageView)).setImageDrawable(roundedBitmapDrawable);
        }*/


      /*  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

    /*    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();*/

      // NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
       //navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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

    public void Citas(View view)
    {
        Intent citas= new Intent(this, CreateNote.class);
        startActivity(citas);
    }
    public void Salir(View view)
    {
        finish();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }
}
