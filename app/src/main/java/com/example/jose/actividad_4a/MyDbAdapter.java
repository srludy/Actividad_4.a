package com.example.jose.actividad_4a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Jose on 28/11/2017.
 */

public class MyDbAdapter implements Parcelable {


    private static final String DATA_BASE_NAME = "dbEscuela.db";
    private static final String TABLE_PROFESORES = "profesores";
    private static final String TABLE_ALUMNOS = "alumnos";
    private static final int DATA_VERSION = 1;

    private static final String ATR_NOM = "nombre";
    private static final String ATR_EDAD = "edad";
    private static final String ATR_CURSO = "curso";
    private static final String ATR_DESPACHO = "despacho";
    private static final String ATR_NOTAM = "notamedia";


    private static final String CREATE_TABLE_PROF = "CREATE TABLE "+TABLE_PROFESORES+" (_id integer primary key autoincrement,"+ATR_NOM+" text,"+ATR_EDAD+" text,"+ATR_CURSO+" text,"+ATR_DESPACHO+" text);";
    private static final String CREATE_TABLE_ALUM = "CREATE TABLE "+TABLE_ALUMNOS+" (_id integer primary key autoincrement,"+ATR_NOM+" text,"+ATR_EDAD+" text,"+ATR_CURSO+" text,"+ATR_NOTAM+" text);";

    private static final String DROP_TABLE_PROF = "DROP TABLE IF EXISTS "+TABLE_PROFESORES+";";
    private static final String DROP_TABLE_ALUM = "DROP TABLE IF EXISTS "+TABLE_ALUMNOS+";";

    private Context context;

    private MyDbHelper myDbHelper;

    private SQLiteDatabase db;


    public MyDbAdapter(Context context){
        this.context = context;
        myDbHelper = new MyDbHelper(context, DATA_BASE_NAME, null, DATA_VERSION);
    }

    public void insertProfesor(String nom, String edad, String curso, String despacho){
        ContentValues CV = new ContentValues();
        CV.put(ATR_NOM,nom);
        CV.put(ATR_EDAD,edad);
        CV.put(ATR_CURSO,curso);
        CV.put(ATR_DESPACHO,despacho);
        db.insert(TABLE_PROFESORES, null, CV);
        Toast.makeText(getContext(),"Añadido: "+nom,Toast.LENGTH_LONG).show();
    }
    public void insertarAlumno(String nom, String edad, String curso, String notaM){
        ContentValues CV = new ContentValues();
        CV.put(ATR_NOM,nom);
        CV.put(ATR_EDAD,edad);
        CV.put(ATR_CURSO,curso);
        CV.put(ATR_NOTAM,notaM);
        db.insert(TABLE_ALUMNOS, null, CV);
        Toast.makeText(getContext(),"Añadido: "+nom,Toast.LENGTH_LONG).show();

    }
    public void close(){
        db.close();
    }
    public void open (){
        try{
            db = myDbHelper.getWritableDatabase();
        }catch (SQLiteException e){
            db = myDbHelper.getReadableDatabase();
        }
    }



    public ArrayList<Alumno> selectAlumnos(String selectCiclo, String selectCurso){
        ArrayList<Alumno> alumnos = new ArrayList();
        Cursor c = null;
        if(selectCiclo.equals("TODOS") && selectCurso.equals("TODOS")){
            c = db.rawQuery("SELECT * FROM "+TABLE_ALUMNOS+";",null);
        }else{
            if(!selectCiclo.equals("TODOS") && !selectCurso.equals("TODOS")){
                c = db.rawQuery("SELECT * FROM "+TABLE_ALUMNOS+" WHERE curso='"+selectCiclo+" "+selectCurso+"';",null);
            }
        }
        try{
            if(c.moveToFirst()) {
                do{
                    int id = c.getInt(0);
                    String nom = c.getString(1);
                    String edad = c.getString(2);
                    String curso = c.getString(3);
                    String notaM = c.getString(4);

                    Alumno alumno = new Alumno(id,nom,edad,curso,notaM);
                    alumnos.add(alumno);
                }while(c.moveToNext());
            }
        }catch (Exception e){
            Toast.makeText(getContext(),"ERROR AL LEER DE LA BASE DE DATOS", Toast.LENGTH_LONG);
        }
        return alumnos;
    }
    public ArrayList<Profesor> selectProfesores(){
        ArrayList<Profesor> profesores = new ArrayList();
        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_PROFESORES+";",null);
        try{
            if(c.moveToFirst()) {
                do{
                    int id = c.getInt(0);
                    String nom = c.getString(1);
                    String edad = c.getString(2);
                    String curso = c.getString(3);
                    String despacho = c.getString(4);

                    Profesor profesor = new Profesor(id,nom,edad,curso,despacho);
                    profesores.add(profesor);
                }while(c.moveToNext());
            }
        }catch (Exception e){
            Toast.makeText(getContext(),"ERROR AL LEER DE LA BASE DE DATOS", Toast.LENGTH_LONG);
        }
        return profesores;

    }

    private static class MyDbHelper extends SQLiteOpenHelper{

        public MyDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_ALUM);
            db.execSQL(CREATE_TABLE_PROF);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DROP_TABLE_ALUM);
            db.execSQL(DROP_TABLE_PROF);
            onCreate(db);
        }
    }

    protected MyDbAdapter(Parcel in) {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<MyDbAdapter> CREATOR = new Parcelable.Creator<MyDbAdapter>() {
        @Override
        public MyDbAdapter createFromParcel(Parcel in) {
            return new MyDbAdapter(in);
        }

        @Override
        public MyDbAdapter[] newArray(int size) {
            return new MyDbAdapter[size];
        }
    };

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public SQLiteDatabase getDb() {
        return db;
    }
}