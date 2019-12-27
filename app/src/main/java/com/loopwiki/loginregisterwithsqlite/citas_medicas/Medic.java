package com.loopwiki.loginregisterwithsqlite.citas_medicas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.loopwiki.loginregisterwithsqlite.R;

public class Medic extends AppCompatActivity implements Medica {

    private RecyclerView recyclerView;
    private Button agregar;

    private CitaMedicaAdapter adapter;
    SQLiteDatabase mDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicas);

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        agregar=findViewById(R.id.button);
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Medic.this, SecondActivity.class));
            }
        });
        CitaMedicaDbHelp dbHelp = new CitaMedicaDbHelp(this);
        mDb = dbHelp.getWritableDatabase();

        Cursor cursor = getAllCitas();
        adapter = new CitaMedicaAdapter(this,cursor, (ListItemClickListener) this);
        recyclerView.setAdapter(adapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                long id = (long) viewHolder.itemView.getTag();
                removeCita(id);

                adapter.swapCursor(getAllCitas());
            }
        }).attachToRecyclerView(recyclerView);
    }

    private void removeCita(long id) {
        mDb.delete(CitaMedicaContract.CitaMedicaEntry.TABLE_NAME,
                CitaMedicaContract.CitaMedicaEntry._ID +"="+id,
                null);
        Toast.makeText(this,"Cita Eliminada",Toast.LENGTH_SHORT).show();
    }

    private Cursor getAllCitas(){
        return mDb.query(CitaMedicaContract.CitaMedicaEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                CitaMedicaContract.CitaMedicaEntry.COLUMN_TIMESTAMP);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.swapCursor(getAllCitas());
    }


    @Override
    public void onClick(View view, int position) {
        long id = (long) view.getTag();

        Intent intent = new Intent(Medic.this, SecondActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cita_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.eliminar_all){
            mDb.delete(CitaMedicaContract.CitaMedicaEntry.TABLE_NAME,
                    null,
                    null);
            adapter.swapCursor(getAllCitas());
            Toast.makeText(this,"Eliminamos todas las citas",Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
