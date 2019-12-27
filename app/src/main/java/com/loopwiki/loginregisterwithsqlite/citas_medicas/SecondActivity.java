package com.loopwiki.loginregisterwithsqlite.citas_medicas;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.loopwiki.loginregisterwithsqlite.R;

public class SecondActivity extends AppCompatActivity {
    private EditText edit_doctor;
    private EditText edit_clinica;
    private Spinner spinner;
    private Button agregar;

    SQLiteDatabase mDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Add");
        actionBar.setDisplayHomeAsUpEnabled(true);

        edit_doctor=findViewById(R.id.editText);
        edit_clinica=findViewById(R.id.editText2);
        agregar = findViewById(R.id.button_add);

        CitaMedicaDbHelp dbHelp = new CitaMedicaDbHelp(this);
        mDb = dbHelp.getWritableDatabase();

        final Intent intent = getIntent();
        if (intent !=null && intent.hasExtra("id")){
            long id = intent.getLongExtra("id",1);
            getCita(id);
            actionBar.setTitle("Actualizar");
            agregar.setText("Actualizar");

        }

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edit_doctor.getText().length()==0){
                    return;
                }
                if(edit_clinica.getText().length()== 0){
                    return;
                }
                if(actionBar.getTitle().equals("Add")){
                    String doctor=edit_doctor.getText().toString();
                    String clinica=edit_clinica.getText().toString();
                    addNewCita(doctor,clinica);
                }else{
                    long id = intent.getLongExtra("id",1);
                    updateCita(id);
                    Toast.makeText(SecondActivity.this,"Cita Actualizada",Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }

    private void getCita(long id) {
        Cursor cursor = mDb.query(CitaMedicaContract.CitaMedicaEntry.TABLE_NAME,
                null,
                CitaMedicaContract.CitaMedicaEntry._ID+"=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        cursor.moveToFirst();

        String doctor = cursor.getString(cursor.getColumnIndex(CitaMedicaContract.CitaMedicaEntry.COLUMN_DOCTOR));
        String clinica = cursor.getString(cursor.getColumnIndex(CitaMedicaContract.CitaMedicaEntry.COLUMN_CLINICA));

        edit_doctor.setText(doctor);
        edit_clinica.setText(clinica);
    }

    private void addNewCita(String doctor,String clinica){
        ContentValues cv = new ContentValues();
        cv.put(CitaMedicaContract.CitaMedicaEntry.COLUMN_DOCTOR,doctor);
        cv.put(CitaMedicaContract.CitaMedicaEntry.COLUMN_CLINICA,clinica);

        mDb.insert(CitaMedicaContract.CitaMedicaEntry.TABLE_NAME,null,cv);
        Toast.makeText(this,"Agregado Correctamente",Toast.LENGTH_SHORT).show();

    }

    private void updateCita(long id){
        String doctor = edit_doctor.getText().toString();
        String clinica = edit_clinica.getText().toString();

        ContentValues cv = new ContentValues();
        cv.put(CitaMedicaContract.CitaMedicaEntry.COLUMN_DOCTOR,doctor);
        cv.put(CitaMedicaContract.CitaMedicaEntry.COLUMN_CLINICA,clinica);

        mDb.update(CitaMedicaContract.CitaMedicaEntry.TABLE_NAME,
                cv,
                CitaMedicaContract.CitaMedicaEntry._ID + "=?",
                new String[]{String.valueOf(id)});
    }
}
