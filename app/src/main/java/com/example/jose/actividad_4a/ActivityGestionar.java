package com.example.jose.actividad_4a;




import android.app.Fragment;
import android.app.FragmentManager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.attr.fragment;

public class ActivityGestionar extends AppCompatActivity {

    private static final String FRAGMENT_ADD_ALUMN = "fragmentAddAlumno";
    private static final String FRAGMENT_ADD_PROF= "fragmentAddProf";


    ArrayList <Profesor> profesores;
    ArrayList <Alumno> alumnos;

    Button btnAddAlumn;
    Button btnAddProf;

    FrameLayout frameLayout;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionar);

        profesores = new ArrayList();
        alumnos = new ArrayList();

        frameLayout = (FrameLayout) findViewById(R.id.frameLayoutFragments);

        btnAddAlumn = (Button) findViewById(R.id.btnAddAlum);
        btnAddProf = (Button) findViewById(R.id.btnAddProf);

        btnAddAlumn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.Fragment fragment = getSupportFragmentManager().findFragmentByTag(FRAGMENT_ADD_ALUMN);
                if(fragment != null){

                }else{
                    deleteFragmentAddProfesor();
                    fragment = FragmentNewAlumno.newInstance("", "");
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutFragments, fragment, FRAGMENT_ADD_ALUMN).addToBackStack(FRAGMENT_ADD_ALUMN).commit();
                }
            }
        });

        btnAddProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = getSupportFragmentManager().findFragmentByTag(FRAGMENT_ADD_PROF);
                if(fragment != null){

                }else{
                    deleteFragmentAddAlumno();
                    fragment = FragmentNewProfesor.newInstance("", "");
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutFragments, fragment, FRAGMENT_ADD_PROF).addToBackStack(FRAGMENT_ADD_PROF).commit();
                }
            }
        });

    }
    public void deleteFragmentAddAlumno(){
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(FRAGMENT_ADD_ALUMN);
        if(fragment != null){
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            getSupportFragmentManager().popBackStack();
        }
    }
    public void deleteFragmentAddProfesor(){
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(FRAGMENT_ADD_PROF);
        if(fragment != null){
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            getSupportFragmentManager().popBackStack();
        }
    }
}
