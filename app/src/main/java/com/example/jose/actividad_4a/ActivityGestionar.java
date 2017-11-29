package com.example.jose.actividad_4a;






import android.app.Fragment;
import android.app.FragmentManager;
//import android.support.v4.app.Fragment;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.attr.fragment;

public class ActivityGestionar extends AppCompatActivity implements FragmentNewAlumno.OnFragmentInteractionListener, FragmentNewProfesor.OnFragmentInteractionListener, addObject{

    private static final String FRAGMENT_ADD_ALUMN = "fragmentAddAlumno";
    private static final String FRAGMENT_ADD_PROF= "fragmentAddProf";

    MyDbAdapter myDbAdapter;
    ArrayList <Profesor> profesores;
    ArrayList <Alumno> alumnos;

    Button btnAddAlumn;
    Button btnAddProf;
    Button btnGuardar;

    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionar);

        profesores = new ArrayList();
        alumnos = new ArrayList();
        myDbAdapter = getIntent().getParcelableExtra("adapter");

        frameLayout = (FrameLayout) findViewById(R.id.frameLayoutFragments);

        btnAddAlumn = (Button) findViewById(R.id.btnAddAlum);
        btnAddProf = (Button) findViewById(R.id.btnAddProf);
        btnGuardar = (Button) findViewById(R.id.btnBack);

        btnAddAlumn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = getFragmentManager().findFragmentByTag(FRAGMENT_ADD_ALUMN);
                if(fragment != null){

                }else{
                    deleteFragmentAddProfesor();
                    fragment = FragmentNewAlumno.newInstance("", "");
                    getFragmentManager().beginTransaction().replace(R.id.frameLayoutFragments, fragment, FRAGMENT_ADD_ALUMN).addToBackStack(FRAGMENT_ADD_ALUMN).commit();
                }
            }
        });

        btnAddProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = getFragmentManager().findFragmentByTag(FRAGMENT_ADD_PROF);
                if(fragment != null){

                }else{
                    deleteFragmentAddAlumno();
                    fragment = FragmentNewProfesor.newInstance("", "");
                    getFragmentManager().beginTransaction().replace(R.id.frameLayoutFragments, fragment, FRAGMENT_ADD_PROF).addToBackStack(FRAGMENT_ADD_PROF).commit();
                }
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
    public void deleteFragmentAddAlumno(){
        Fragment fragment = getFragmentManager().findFragmentByTag(FRAGMENT_ADD_ALUMN);
        if(fragment != null){
            getFragmentManager().beginTransaction().remove(fragment).commit();
            getFragmentManager().popBackStack();
        }
    }
    public void deleteFragmentAddProfesor(){
        Fragment fragment = getFragmentManager().findFragmentByTag(FRAGMENT_ADD_PROF);
        if(fragment != null){
            getFragmentManager().beginTransaction().remove(fragment).commit();
            getFragmentManager().popBackStack();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void addProf(Profesor profesor) {

    }

    @Override
    public void addAlum(Alumno alumno) {

    }
}
