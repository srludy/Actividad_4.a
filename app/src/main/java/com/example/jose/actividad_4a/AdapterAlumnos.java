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

public class AdapterAlumnos extends RecyclerView.Adapter<AdapterAlumnos.ViewHolder>{


    ArrayList <Alumno> alumnos;

    public AdapterAlumnos (ArrayList <Alumno> alumnos){
        this.alumnos = alumnos;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nomView;
        TextView edadView;
        TextView cursoView;
        TextView notaMView;

        public ViewHolder(View itemView) {
            super(itemView);

             nomView = (TextView) itemView.findViewById(R.id.txtNom);
             edadView = (TextView) itemView.findViewById(R.id.txtEdad);
             cursoView = (TextView) itemView.findViewById(R.id.txtCurso);
             notaMView = (TextView) itemView.findViewById(R.id.txtNotaM);

        }
    }

    @Override
    public AdapterAlumnos.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_cardview, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(AdapterAlumnos.ViewHolder holder, int position) {
        holder.nomView.setText(alumnos.get(position).getNombre());
        holder.edadView.setText(alumnos.get(position).getEdad());
        holder.cursoView.setText(alumnos.get(position).getCurso());
        holder.notaMView.setText(alumnos.get(position).getNotaM());
    }

    @Override
    public int getItemCount() {
        return alumnos.size();
    }

}
