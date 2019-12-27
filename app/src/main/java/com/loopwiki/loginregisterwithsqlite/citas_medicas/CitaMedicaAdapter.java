package com.loopwiki.loginregisterwithsqlite.citas_medicas;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.loopwiki.loginregisterwithsqlite.R;

public class CitaMedicaAdapter extends RecyclerView.Adapter<CitaMedicaAdapter.CitaViewHolder> {

    private Context nContext;
    private Cursor cursor;
    private ListItemClickListener listItemClickListener;

    public CitaMedicaAdapter(Context nContext,Cursor cursor,ListItemClickListener listItemClickListener){
        this.nContext = nContext;
        this.cursor = cursor;
        this.listItemClickListener=listItemClickListener;
    }

    @NonNull
    @Override
    public CitaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(nContext).inflate(R.layout.cita_item,viewGroup,false);
        return new CitaViewHolder(view);

    }
    @Override
    public void onBindViewHolder(@NonNull CitaViewHolder citaViewHolder, int i){
        if (!cursor.moveToPosition(i)){
            return;
        }
        String doctor=cursor.getString(cursor.getColumnIndex(CitaMedicaContract.CitaMedicaEntry.COLUMN_DOCTOR));
        String clinica = cursor.getString(cursor.getColumnIndex(CitaMedicaContract.CitaMedicaEntry.COLUMN_CLINICA));
        String time = cursor.getString(cursor.getColumnIndex(CitaMedicaContract.CitaMedicaEntry.COLUMN_TIMESTAMP));

        long id = cursor.getLong(cursor.getColumnIndex(CitaMedicaContract.CitaMedicaEntry._ID));
        citaViewHolder.itemView.setTag(id);

        citaViewHolder.cita_doctor.setText(doctor);
        citaViewHolder.cita_clinica.setText(clinica);
        citaViewHolder.timestamp_text.setText(time);

    }
    @Override
    public int getItemCount(){
        return cursor.getCount();
    }

    public class CitaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView cita_doctor,cita_clinica,timestamp_text;
        public CitaViewHolder(@NonNull View itemView){
            super(itemView);

            cita_doctor = itemView.findViewById(R.id.cita_doctor);
            cita_clinica = itemView.findViewById(R.id.cita_clinica);
            timestamp_text = itemView.findViewById(R.id.timestamp);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            listItemClickListener.onClick(v,getAdapterPosition());
        }
    }

    public void swapCursor(Cursor newCursor){
        if (cursor != null){
            cursor.close();
        }
        cursor = newCursor;
        if (newCursor != null){
            this.notifyDataSetChanged();
        }
    }
}
