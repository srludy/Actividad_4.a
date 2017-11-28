package com.example.jose.actividad_4a;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jose on 28/11/2017.
 */

public class AdapterProfesores extends RecyclerView.Adapter<AdapterProfesores.ViewHolder>{

    ArrayList <Profesor> profesores;

    public AdapterProfesores (ArrayList <Profesor> profesores){
        this.profesores = profesores;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView nomView;
        TextView edadView;
        TextView cursoView;
        TextView despachoView;

        public ViewHolder(View itemView) {
            super(itemView);
            nomView = (TextView) itemView.findViewById(R.id.txtNomP);
            edadView = (TextView) itemView.findViewById(R.id.txtEdadP);
            cursoView = (TextView) itemView.findViewById(R.id.txtCursoP);
            despachoView = (TextView) itemView.findViewById(R.id.txtDespacho);
        }
    }


    @Override
    public AdapterProfesores.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.prof_cardview, parent, false);
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(AdapterProfesores.ViewHolder holder, int position) {
        holder.nomView.setText(profesores.get(position).getNombre());
        holder.edadView.setText(profesores.get(position).getEdad());
        holder.cursoView.setText(profesores.get(position).getCurso());
        holder.despachoView.setText(profesores.get(position).getDespacho());

    }

    @Override
    public int getItemCount() {
        return profesores.size();
    }

}
