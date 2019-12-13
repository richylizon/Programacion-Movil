package com.loopwiki.loginregisterwithsqlite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HeaderAdapter extends RecyclerView.Adapter<HeaderAdapter.ViewHolder> {

    private ArrayList<Header> mDataset;

     static class ViewHolder extends RecyclerView.ViewHolder {

        TextView headerCode;
        TextView titulo;
        TextView fecha;
        TextView descripcion;

        ViewHolder(View v) {
            super(v);
            headerCode = (TextView)v.findViewById(R.id.txt_headerCode);
            titulo = (TextView)v.findViewById(R.id.txt_titulo);
            fecha = (TextView)v.findViewById(R.id.txt_fecha);
            descripcion = (TextView)v.findViewById(R.id.txt_descripcion);
        }
    }


    public HeaderAdapter(ArrayList<Header> myDataset) {
        mDataset = myDataset;
    }

    //Crear la vista
    @Override
    public HeaderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header, parent, false);

        //Aqui se puede agregar margenes y puddins

        return new ViewHolder(v);
    }

    // Asignar valores a cada textView
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.headerCode.setText(mDataset.get(position).getHeaderCode());
        holder.titulo.setText(mDataset.get(position).getTitulo());
        holder.fecha.setText(mDataset.get(position).getFecha());
        holder.descripcion.setText(mDataset.get(position).getDescripcion());

    }

    //Devolver el tamano del ArrayList
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}