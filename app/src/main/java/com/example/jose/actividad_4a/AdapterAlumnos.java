package com.example.jose.actividad_4a;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Jose on 28/11/2017.
 */

public class AdapterAlumnos extends RecyclerView.Adapter<AdapterAlumnos.ViewHolder>{


    ArrayList <Alumno> alumnos;
    AdapterAlumnos.ViewHolder vh;
    static MainActivity mainActivity;

    public AdapterAlumnos (ArrayList <Alumno> alumnos, MainActivity mainActivity){
        this.alumnos = alumnos;
        this.mainActivity = mainActivity;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        View v;
        TextView nomView;
        TextView edadView;
        TextView cursoView;
        TextView notaMView;

        public ViewHolder(View itemView) {
            super(itemView);

             this.v = itemView;
             nomView = (TextView) itemView.findViewById(R.id.txtNom);
             edadView = (TextView) itemView.findViewById(R.id.txtEdad);
             cursoView = (TextView) itemView.findViewById(R.id.txtCurso);
             notaMView = (TextView) itemView.findViewById(R.id.txtNotaM);
             itemView.setOnCreateContextMenuListener(mainActivity);
        }

    }

    public void updateData(ArrayList<Alumno> alumnos) {
        this.alumnos.clear();
        this.alumnos.addAll(alumnos);
        notifyDataSetChanged();
    }
    public void updateAfterDelete(ArrayList<Alumno> alumnos, int position){
        this.alumnos = alumnos;
        notifyItemRemoved(position);
    }

    @Override
    public AdapterAlumnos.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_cardview, parent, false);
        vh = new ViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(AdapterAlumnos.ViewHolder holder, final int position) {
        holder.nomView.setText(alumnos.get(position).getNombre());
        holder.edadView.setText(alumnos.get(position).getEdad());
        holder.cursoView.setText(alumnos.get(position).getCurso());
        holder.notaMView.setText(alumnos.get(position).getNotaM());
        holder.v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mainActivity.getIdItem(alumnos.get(position).getId(), position);
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return alumnos.size();
    }

    public ArrayList<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(ArrayList<Alumno> alumnos) {
        this.alumnos = alumnos;
    }
}
