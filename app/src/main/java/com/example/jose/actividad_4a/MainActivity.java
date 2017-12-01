package com.example.jose.actividad_4a;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    //FINALS
    private static final int ACT_GEST = 2;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapterProfesores;
    private AdapterAlumnos adapterAlumnos;
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

        //Adaptador de la base de datos
        myDbAdapter = new MyDbAdapter(this);

        //ArrayLists
        profesors = new ArrayList();
        alumnos = new ArrayList();
        actualizaArrayPorf();
        actualizaArrayAlum();

        //RecyclerView iniciaciones
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapterAlumnos = new AdapterAlumnos(alumnos);
        adapterProfesores = new AdapterProfesores(profesors);

        //Iniciacion Botones
        btnVerAlum = (Button) findViewById(R.id.button);
        btnVerProf = (Button) findViewById(R.id.button2);
        btnGestionar = (Button) findViewById(R.id.btnGestor);


        btnVerAlum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setVisibility(View.VISIBLE);
                recyclerView.setAdapter(adapterAlumnos);
            }
        });

        btnVerProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setVisibility(View.VISIBLE);
                recyclerView.setAdapter(adapterProfesores);
            }
        });

        btnGestionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplication(), ActivityGestionar.class);
                // i.putExtra("adapter", myDbAdapter);
                startActivityForResult(i,ACT_GEST);
            }
        });
    }

    private void actualizaArrayAlum() {
        myDbAdapter.open();
        alumnos = myDbAdapter.selectAlumnos();
        myDbAdapter.close();
    }
    private void actualizaArrayPorf(){
        myDbAdapter.open();
        profesors = myDbAdapter.selectProfesores();
        myDbAdapter.close();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(resultCode){
            case RESULT_OK:
                recyclerView.setVisibility(View.INVISIBLE);
                actualizaArrayPorf();
                actualizaArrayAlum();
                adapterAlumnos.updateData(alumnos);
                break;
            case RESULT_CANCELED:
                recyclerView.setVisibility(View.GONE);
                break;
        }
    }
}
