package com.loopwiki.loginregisterwithsqlite.peso_madre;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.loopwiki.loginregisterwithsqlite.R;

public class SegundoActivity extends AppCompatActivity {

    private TextView tv_fecha,tv_pesoIni,tv_pesoA,tv_aumento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segundo);

        tv_fecha = (TextView)findViewById(R.id.tvfecha2);
        tv_pesoIni = (TextView)findViewById(R.id.tvpesoInicial2);
        tv_pesoA = (TextView)findViewById(R.id.tvpesoActual2);
        tv_aumento = (TextView)findViewById(R.id.tvAumento2);

        String fecha = getIntent().getStringExtra("fecha");
        String pesoI = getIntent().getStringExtra("pesoI");
        String pesoA = getIntent().getStringExtra("pesoA");
        String aumento = getIntent().getStringExtra("aumento");

        tv_fecha.setText(fecha);
        tv_pesoIni.setText(pesoI+" klg");
        tv_pesoA.setText(pesoA+" klg");
        tv_aumento.setText(aumento+" klg");
    }
}
