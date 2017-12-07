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
    static MainActivity mainActivity;

    public AdapterProfesores (ArrayList <Profesor> profesores, MainActivity mainActivity){
        this.profesores = profesores;
        this.mainActivity = mainActivity;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        View v;
        TextView nomView;
        TextView edadView;
        TextView cursoView;
        TextView despachoView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.v = itemView;
            nomView = (TextView) itemView.findViewById(R.id.txtNomP);
            edadView = (TextView) itemView.findViewById(R.id.txtEdadP);
            cursoView = (TextView) itemView.findViewById(R.id.txtCursoP);
            despachoView = (TextView) itemView.findViewById(R.id.txtDespacho);
            itemView.setOnCreateContextMenuListener(mainActivity);
        }
    }

    public void updateData(ArrayList<Profesor> profesores) {
        this.profesores.clear();
        this.profesores.addAll(profesores);
        notifyDataSetChanged();
    }
    public void updateAfterDelete(ArrayList<Profesor> profesores, int position){
        this.profesores = profesores;
        notifyItemRemoved(position);
    }

    @Override
    public AdapterProfesores.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.prof_cardview, parent, false);
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(AdapterProfesores.ViewHolder holder, final int position) {
        holder.nomView.setText(profesores.get(position).getNombre());
        holder.edadView.setText(profesores.get(position).getEdad());
        holder.cursoView.setText(profesores.get(position).getCurso());
        holder.despachoView.setText(profesores.get(position).getDespacho());
        holder.v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mainActivity.getIdItem(profesores.get(position).getId(), position);
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return profesores.size();
    }

    public ArrayList<Profesor> getProfesores() {
        return profesores;
    }

    public void setProfesores(ArrayList<Profesor> profesores) {
        this.profesores = profesores;
    }
}
