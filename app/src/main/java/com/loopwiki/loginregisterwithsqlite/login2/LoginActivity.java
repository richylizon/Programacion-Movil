package com.loopwiki.loginregisterwithsqlite.login2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.loopwiki.loginregisterwithsqlite.MainActivity;
import com.loopwiki.loginregisterwithsqlite.R;
import com.loopwiki.loginregisterwithsqlite.login2.database.DatabaseManagerUser;

public class LoginActivity extends AppCompatActivity {

    private EditText eEmail, ePassword;
    private Button acceder;
    private TextView registrar;
    private String email;
    private String password;
    private Cursor comprobar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        eEmail = (EditText)findViewById(R.id.etusuario);
        ePassword = (EditText)findViewById(R.id.etpass);
        acceder = (Button)findViewById(R.id.button);
        registrar = (TextView)findViewById(R.id.signup);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),UserRegistro.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        acceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciar();
            }
        });
    }

    private void iniciar() {

        if (!validar()) return;

        email = eEmail.getText().toString();
        password = ePassword.getText().toString();

        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Iniciando...");
        progressDialog.show();

        final DatabaseManagerUser databaseManager = new DatabaseManagerUser(getApplicationContext());

        eEmail.getText().clear();
        ePassword.getText().clear();

        new Handler().postDelayed(
                new Runnable() {
                    public void run() {

                        if (databaseManager.comprobarRegistro(email)){
                            comprobar = databaseManager.getDb().rawQuery("SELECT correo, password FROM demo" + " WHERE correo='"+email+"' AND password='"+password+"'",null);
                            if(comprobar.moveToFirst()){
                                Intent intent =new Intent(getApplicationContext(), MainActivity.class);
                                intent.putExtra("IDENT",email);
                                startActivity(intent);
                                finish();
                                progressDialog.dismiss();
                            }else{
                                eEmail.setText(email);
                                progressDialog.dismiss();
                                String mesg = String.format("Password incorrecto", null);
                                Toast.makeText(getApplicationContext(),mesg, Toast.LENGTH_LONG).show();
                            }
                        }else{
                            progressDialog.dismiss();
                            String mesg = String.format("El E-mail que has introducido no coinciden con ninguna cuenta", null);
                            Toast.makeText(getApplicationContext(),mesg, Toast.LENGTH_LONG).show();
                        }
                    }
                }, 3000);

    }

    private boolean validar() {
        boolean valid = true;

        String email = eEmail.getText().toString();
        String password = ePassword.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            eEmail.setError("Introduzca una dirección de correo electrónico válida");
            valid = false;
        } else {
            eEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            ePassword.setError("Entre 4 y 10 caracteres alfanuméricos");
            valid = false;
        } else {
            ePassword.setError(null);
        }

        return valid;
    }
}
