package com.loopwiki.loginregisterwithsqlite.peso_madre;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.loopwiki.loginregisterwithsqlite.R;

public class Peso_Madre_Activity extends AppCompatActivity {

    private EditText et_pesoInicial,et_pesoActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peso_);

        et_pesoInicial = (EditText)findViewById(R.id.et_pesoInicial);
        et_pesoActual = (EditText)findViewById(R.id.et_pesoActual);
    }

    //Método para registrar los pesos
    public void registrarpeso (View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String pesoIncial = et_pesoInicial.getText().toString();
        String pesoActual = et_pesoActual.getText().toString();

        if (!pesoIncial.isEmpty() && !pesoActual.isEmpty()){
            ContentValues registro = new ContentValues();

            registro.put("pesoInicial",pesoIncial);
            registro.put("pesoAcual",pesoActual);

            BaseDeDatos.insert("pesos", null, registro);

            BaseDeDatos.close();
            Toast.makeText(this,"Registrado",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Debe ingresar los pesos",Toast.LENGTH_SHORT).show();
        }
    }
}