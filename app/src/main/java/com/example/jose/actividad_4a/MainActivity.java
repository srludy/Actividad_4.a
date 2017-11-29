package com.example.jose.actividad_4a;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    //FINALS
    private static final int ACT_GEST = 2;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapterProfesores;
    private RecyclerView.Adapter adapterAlumnos;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList <Alumno> alumnos;
    ArrayList <Profesor> profesors;
    MyDbAdapter myDbAdapter;

    Button btnVerProf;
    Button btnVerAlum;
    Button btnGestionar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ArrayLists
        profesors = new ArrayList();
        alumnos = new ArrayList();


        //RecyclerView iniciaciones
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Adaptador de la base de datos
        myDbAdapter = new MyDbAdapter(this);
        myDbAdapter.open();
       /* try{
            myDbAdapter.insertProfesor("Ana Asins Aleixandre","23","DAW 2","10");
            Toast.makeText(getApplicationContext(),"Se ha añadido",Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"No se ha podido añadir",Toast.LENGTH_LONG).show();
        }
        */

        //Iniciacion Botones
        btnVerAlum = (Button) findViewById(R.id.button);
        btnVerProf = (Button) findViewById(R.id.button2);
        btnGestionar = (Button) findViewById(R.id.btnGestor);


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

        btnGestionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplication(), ActivityGestionar.class);
                i.putExtra("adapter", myDbAdapter);
                startActivityForResult(i,ACT_GEST);
            }
        });
    }
}
