package com.loopwiki.loginregisterwithsqlite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolderDatos> implements View.OnClickListener{

    ArrayList<NotasVo> listaNotas;
    private View.OnClickListener listener;


    public AdapterDatos(ArrayList<NotasVo> listaNotas) {
        this.listaNotas = listaNotas;
    }

    @Override
    public ViewHolderDatos onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_datos,null,false);

        //escucha del evento
        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }

    //Llenar datos
    @Override
    public void onBindViewHolder(ViewHolderDatos holder, int position) {
        holder.Et_codigo.setText(listaNotas.get(position).getCodigo());
        holder.Et_titulo.setText(listaNotas.get(position).getTitulo());
        holder.Et_descripcion.setText(listaNotas.get(position).getDescripcion());
        holder.Et_fecha.setText(listaNotas.get(position).getFecha());

    }

    //tamanio del arraylist
    @Override
    public int getItemCount() {
        return listaNotas.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    //Estructura basica para generar los eventos
    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView Et_codigo, Et_titulo, Et_descripcion, Et_fecha;

        public ViewHolderDatos(View itemView) {
            super(itemView);
            Et_codigo = (TextView) itemView.findViewById(R.id.id_codigo);
            Et_titulo = (TextView) itemView.findViewById(R.id.id_titulo);
            Et_descripcion = (TextView) itemView.findViewById(R.id.id_descripcion);
            Et_fecha = (TextView) itemView.findViewById(R.id.id_fecha);
        }
    }
}
