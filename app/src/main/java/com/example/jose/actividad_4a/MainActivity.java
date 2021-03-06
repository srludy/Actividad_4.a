package com.example.jose.actividad_4a;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    //FINALS
    private static final int ACT_GEST = 2;

    private RecyclerView recyclerView;
    private AdapterProfesores adapterProfesores;
    private AdapterAlumnos adapterAlumnos;
    private RecyclerView.LayoutManager layoutManager;
    private String adapterString = "";
    private int idSelected ;
    private int positionSelected;

    ArrayList <Alumno> alumnos;
    ArrayList <Profesor> profesors;
    MyDbAdapter myDbAdapter;

    Button btnVerProf;
    Button btnVerAlum;
    Button btnGestionar;
    Button btnFiltrar;
    Button btnDeleteDataBase;

    Spinner spinner2;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Adaptador de la base de datos
        myDbAdapter = new MyDbAdapter(this);

        //ArrayLists
        profesors = new ArrayList();
        alumnos = new ArrayList();
        actualizaArrayPorf("TODOS","TODOS");
        actualizaArrayAlum("TODOS","TODOS");

        //RecyclerView iniciaciones
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapterAlumnos = new AdapterAlumnos(alumnos,this);
        adapterProfesores = new AdapterProfesores(profesors, this);

        //Iniciacion Botones
        btnFiltrar = (Button) findViewById(R.id.button3);
        btnVerAlum = (Button) findViewById(R.id.button);
        btnVerProf = (Button) findViewById(R.id.button2);
        btnGestionar = (Button) findViewById(R.id.btnGestor);
        btnDeleteDataBase = (Button) findViewById(R.id.deleteDataBase);

        //Spiners
        spinner2 = (Spinner)findViewById(R.id.spinner2);
        spinner = (Spinner) findViewById(R.id.spinner);
        String[] cursos = {"TODOS","1","2"};
        String[] ciclos = {"TODOS","DAM","ASIR","DAW"};
        spinner2.setAdapter(new ArrayAdapter(this, android.R.layout.simple_spinner_item,cursos));
        spinner.setAdapter(new ArrayAdapter(this, android.R.layout.simple_spinner_item,ciclos));


        btnDeleteDataBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDbAdapter.open();
                myDbAdapter.dropDatabase();
                alumnos.clear();
                profesors.clear();
            }
        });

        btnFiltrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adapterString.equals("prof")){
                    actualizaArrayPorf(spinner.getSelectedItem().toString(),spinner2.getSelectedItem().toString());
                    adapterProfesores.updateData(profesors);
                }
                if(adapterString.equals("alum")){
                    actualizaArrayAlum(spinner.getSelectedItem().toString(),spinner2.getSelectedItem().toString());
                    adapterAlumnos.updateData(alumnos);
                }
            }
        });

        btnVerAlum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterString = "alum";
                recyclerView.setVisibility(View.VISIBLE);
                recyclerView.setAdapter(adapterAlumnos);
            }
        });

        btnVerProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterString = "prof";
                recyclerView.setVisibility(View.VISIBLE);
                recyclerView.setAdapter(adapterProfesores);
            }
        });

        btnGestionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplication(), ActivityGestionar.class);
                startActivityForResult(i,ACT_GEST);
            }
        });
    }


    private void actualizaArrayAlum(String selectCiclo, String selectCurso) {
        myDbAdapter.open();
        alumnos = myDbAdapter.selectAlumnos(selectCiclo, selectCurso);
        myDbAdapter.close();
    }
    private void actualizaArrayPorf(String selectCiclo, String selectCurso){
        myDbAdapter.open();
        profesors = myDbAdapter.selectProfesores(selectCiclo,selectCurso);
        myDbAdapter.close();
    }
    public void getIdItem(int id, int position){
        idSelected = id;
        positionSelected = position;
        Toast.makeText(getApplicationContext(),alumnos.get(position).getId()+alumnos.get(position).getNombre()+"  "+idSelected,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(resultCode){
            case RESULT_OK:
                recyclerView.setVisibility(View.INVISIBLE);
                actualizaArrayPorf("TODOS","TODOS");
                actualizaArrayAlum("TODOS","TODOS");
                adapterAlumnos.updateData(alumnos);
                adapterProfesores.updateData(profesors);
                adapterString = "";
                break;
            case RESULT_CANCELED:
                adapterString = "";
                recyclerView.setVisibility(View.INVISIBLE);
                break;
        }
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contextmenu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        myDbAdapter.open();
        switch (item.getItemId()){
            case R.id.eliminar:
                myDbAdapter.deleteItem(idSelected, adapterString);
                switch (adapterString){
                    case "prof":
                        profesors.remove(positionSelected);
                        adapterProfesores.updateAfterDelete(profesors , positionSelected);
                        break;
                    case "alum":
                        alumnos.remove(positionSelected);
                        adapterAlumnos.updateAfterDelete(alumnos , positionSelected);
                        break;
                }
                break;
            case R.id.modificar:
                break;
        }
        myDbAdapter.close();
        return super.onContextItemSelected(item);
    }
}
