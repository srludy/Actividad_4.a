package com.example.jose.actividad_4a;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapterProfesores;
    private RecyclerView.Adapter adapterAlumnos;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList <Alumno> alumnos;
    ArrayList <Profesor> profesors;

    Button btnVerProf;
    Button btnVerAlum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ArrayLists
        profesors = new ArrayList();
        alumnos = new ArrayList();
        Alumno alumno = new Alumno("Pepe","23","DAM","10");
        Profesor profesor = new Profesor("Antonio","42","DAM","2.A");
        profesors.add(profesor);
        alumnos.add(alumno);

        //RecyclerView iniciaciones
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Adaptador de la base de datos
        MyDbAdapter myDbAdapter = new MyDbAdapter(this);
        myDbAdapter.open();

        //Iniciacion Botones
        btnVerAlum = (Button) findViewById(R.id.button);
        btnVerProf = (Button) findViewById(R.id.button2);


        btnVerAlum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterAlumnos = new AdapterAlumnos(alumnos);
                recyclerView.setAdapter(adapterAlumnos);
            }
        });

        btnVerProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterProfesores = new AdapterProfesores(profesors);
                recyclerView.setAdapter(adapterProfesores);
            }
        });

    }
}
