package com.loopwiki.loginregisterwithsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Diario extends AppCompatActivity {

   // private EditText et1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diario);

        Button b_diario = (Button)findViewById(R.id.boton_diario);
        b_diario.setOnClickListener((View.OnClickListener) this);
    }

    public void onClick(View view){
        switch (view.getId()) {
            case R.id.boton_diario:
                Intent intent = new Intent(this, PanelDiario.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }



}
