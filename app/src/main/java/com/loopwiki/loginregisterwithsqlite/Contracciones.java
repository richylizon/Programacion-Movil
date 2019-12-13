package com.loopwiki.loginregisterwithsqlite;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Vector;

public class Contracciones extends AppCompatActivity {

    //Tag para el Log
    private final static String TAG="Contracciones";
    private Chronometer cronometro;
    private Button btn_iniciar;
    private ListView listaRegistros;
    private ArrayAdapter<String> adapter;
    //Diálogo de progreso que se presenta durante las búsquedas en la base de datos
    private ProgressDialog progreso;
    //Indica si el cronómetro ha sido iniciado
    private boolean iniciado=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contracciones);
        cronometro=(Chronometer)findViewById(R.id.chronometer1);
        btn_iniciar=(Button)findViewById(R.id.btn_cronometro);
        adapter=new ArrayAdapter<String>(this, R.layout.layout_item_lista,new Vector<String>());
        listaRegistros=(ListView) findViewById(R.id.listaRegistros);
        listaRegistros.setAdapter(adapter);
        progreso=new ProgressDialog(this);
        progreso.setMessage("Realizando b\u00fasqueda");
        progreso.setTitle("PROGRESO");
        //Inicia la búsqueda de registros guardados en la base de datos en un AsyncTask
        TareaQuery tarea=new TareaQuery();
        tarea.execute("INTERVALO");
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.diario){
            Intent intent=new Intent(this,PanelDiario.class);
            startActivity(intent);
        }
        else if(id == R.id.info) {
            Intent intent=new Intent(this, Info_diaria.class);
            startActivity(intent);
        }
        else if(id == R.id.contracciones) {
            Toast.makeText( this, "Contador Contracciones",Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.semana_semana) {
            Intent intent=new Intent(this, FetoSemana.class);
            startActivity(intent);
        }
        else if(id == R.id.salir){
            Intent intent=new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void controlCronometro(View v){
        if(!iniciado){
            //reinicia la base del cronómetro para ce inicie de cero
            cronometro.setBase(SystemClock.elapsedRealtime());
            cronometro.start();
            iniciado=true;
            btn_iniciar.setText("Detener");
        }else{
            cronometro.stop();
            iniciado=false;
            btn_iniciar.setText("Iniciar");
            String registroActual=cronometro.getText().toString();
            adapter.add(registroActual);
            //Iniica el almacenamiento en la base de datos.
            BDHelper helper=new BDHelper(this, BDHelper.NOMBRE_BD, null, BDHelper.VERSION_BD);
            SQLiteDatabase bd=helper.getWritableDatabase();
            ContentValues values=new ContentValues();
            values.put("INTERVALO", registroActual);
            //Si la versión de la base de datos es mayor a 1 entoncas se ha incluido la columna
            //ID_USUARIO a la tabla de registros
          //  if(BDHelper.VERSION_BD > 1)
                //values.put("ID_USUARIO", idUsuarioActual);
            if(bd.insert(BDHelper.NOMBRE_TABLA_REG, null, values)==-1){
                Toast.makeText(getApplicationContext(), "Error al insertar datos", Toast.LENGTH_LONG).show();
            }
            bd.close();
        }
    }
    public String[] buscarRegistros(){
        String[] registrosEncontrados=null;
        BDHelper helper=new BDHelper(getApplicationContext(), BDHelper.NOMBRE_BD, null, BDHelper.VERSION_BD);
        SQLiteDatabase bd=helper.getReadableDatabase();
        //Array con el nombre de las columnas a ser retornadas
        String columnas[]=new String[]{"ID","INTERVALO"};
        //Sentencia SQL WHERE sin el ´WHERE´
        String where="ID < ? AND ID > ?";
        //Array con los valores que sustituyen a los signos de interrogación en el String anterior
        String selectionArgs[]=new String[]{"10","4"};
        //Instrucción que realiza la búsqueda en la base de datos
        Cursor cursor=bd.query(BDHelper.NOMBRE_TABLA_REG, columnas, where,selectionArgs , null, null, null);
        //Retorna true si hay algún dato en el cursor
        if(cursor.moveToFirst()){
            registrosEncontrados=new String[cursor.getCount()];
            do{
                registrosEncontrados[cursor.getPosition()]=cursor.getString(cursor.getColumnIndex("ID"));
                registrosEncontrados[cursor.getPosition()]=cursor.getString(cursor.getColumnIndex("INTERVALO"));
                //adapter.add(cursor.getString(cursor.getColumnIndex("INTERVALO")));
            }while(cursor.moveToNext());
        }
        cursor.close();
        bd.close();
        return registrosEncontrados;
    }


    //AsyncTask en el que se realiza la búsqueda de registros
    public class TareaQuery extends AsyncTask<String, Integer, String[]> {

        //variable que se utiliza para identificar la búsqueda que se realiza en el AsyncTask
        //Usuarios o Intervalos
        private String tipoBusqueda;
        @Override
        protected String[] doInBackground(String... params) {
            tipoBusqueda=params[0];
            String leidos[]=null;
            if(tipoBusqueda.equals("INTERVALO"))
                leidos = buscarRegistros();
           // if(tipoBusqueda.equals("USUARIO"))
               // leidos = buscarUsuarios();
            return leidos;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progreso.show();
        }

        @Override
        protected void onPostExecute(String[] result) {
            if (result != null) {
                super.onPostExecute(result);
                if (tipoBusqueda.equals("INTERVALO")) {
                    //adapter.addAll(result); //aplica a versiones API>10
                    for (String resultado : result)
                        adapter.add(resultado);
                }
                /*if (tipoBusqueda.equals("USUARIO"))
                    seleccionarUsuario(result);*/
            }
            progreso.dismiss();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }


}
