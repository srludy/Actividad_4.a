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

    //DATABASE
    private static final String DATA_BASE_NAME = "dbEscuela.db";
    //TABLAS
    private static final String TABLE_PROFESORES = "profesores";
    private static final String TABLE_ALUMNOS = "alumnos";
    private static final int DATA_VERSION = 1;

    //ATRIBUTOS TABLAS
    private static final String ATR_NOM = "nombre";
    private static final String ATR_EDAD = "edad";
    private static final String ATR_CURSO = "curso";
    private static final String ATR_DESPACHO = "despacho";
    private static final String ATR_NOTAM = "notamedia";

    //CREATE TABLE SQL
    private static final String CREATE_TABLE_PROF = "CREATE TABLE "+TABLE_PROFESORES+" (_id integer primary key autoincrement,"+ATR_NOM+" text,"+ATR_EDAD+" text,"+ATR_CURSO+" text,"+ATR_DESPACHO+" text);";
    private static final String CREATE_TABLE_ALUM = "CREATE TABLE "+TABLE_ALUMNOS+" (_id integer primary key autoincrement,"+ATR_NOM+" text,"+ATR_EDAD+" text,"+ATR_CURSO+" text,"+ATR_NOTAM+" text);";

    //DROP TABLE SQL
    private static final String DROP_TABLE_PROF = "DROP TABLE IF EXISTS "+TABLE_PROFESORES+";";
    private static final String DROP_TABLE_ALUM = "DROP TABLE IF EXISTS "+TABLE_ALUMNOS+";";

    private Context context;

    //OBJETOS CLASE HELPER Y SQLITEDB
    private MyDbHelper myDbHelper;
    private SQLiteDatabase db;


    //CONSTRUCTOR DE LA CLASE ADAPTADOR
    public MyDbAdapter(Context context){
        this.context = context;
        myDbHelper = new MyDbHelper(context, DATA_BASE_NAME, null, DATA_VERSION);
    }

    //INSERT PROFESORES
    public void insertProfesor(String nom, String edad, String curso, String despacho){
        ContentValues CV = new ContentValues();
        CV.put(ATR_NOM,nom);
        CV.put(ATR_EDAD,edad);
        CV.put(ATR_CURSO,curso);
        CV.put(ATR_DESPACHO,despacho);
        db.insert(TABLE_PROFESORES, null, CV);
        Toast.makeText(getContext(),"Añadido: "+nom,Toast.LENGTH_LONG).show();
    }
    //INSERT ALUMNOS
    public void insertarAlumno(String nom, String edad, String curso, String notaM){
        ContentValues CV = new ContentValues();
        CV.put(ATR_NOM,nom);
        CV.put(ATR_EDAD,edad);
        CV.put(ATR_CURSO,curso);
        CV.put(ATR_NOTAM,notaM);
        db.insert(TABLE_ALUMNOS, null, CV);
        Toast.makeText(getContext(),"Añadido: "+nom,Toast.LENGTH_LONG).show();

    }
    //CERRAR BD
    public void close(){
        db.close();
    }
    //ABRIR BD
    public void open (){
        try{
            db = myDbHelper.getWritableDatabase();
        }catch (SQLiteException e){
            db = myDbHelper.getReadableDatabase();
        }
    }


    //SELECT de ALUMNOS
    public ArrayList<Alumno> selectAlumnos(String selectCiclo, String selectCurso){
        ArrayList<Alumno> alumnos = new ArrayList();
        Cursor c = null;
        if(selectCiclo.equals("TODOS") && selectCurso.equals("TODOS")){
            c = db.rawQuery("SELECT * FROM "+TABLE_ALUMNOS+";",null);
        }else{
            if(!selectCiclo.equals("TODOS") && !selectCurso.equals("TODOS")){
                c = db.rawQuery("SELECT * FROM "+TABLE_ALUMNOS+" WHERE curso='"+selectCiclo+" "+selectCurso+"';",null);
            }else{
                if(!selectCiclo.equals("TODOS") && selectCurso.equals("TODOS")){
                    c = db.rawQuery("SELECT * FROM "+TABLE_ALUMNOS+" WHERE curso LIKE '"+selectCiclo+" %';",null);
                }else{
                    c = db.rawQuery("SELECT * FROM "+TABLE_ALUMNOS+" WHERE curso LIKE '% "+selectCurso+"';",null);
                }
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
    //DELETE ITEM
    public void deleteItem(int id, String destino){
        switch (destino){
            case "prof":
                db.execSQL("DELETE FROM "+TABLE_PROFESORES+" WHERE _id = "+id+";");
                break;
            case "alum":
                db.execSQL("DELETE FROM "+TABLE_ALUMNOS+" WHERE _id = "+id+";");
                break;
        }
    }

    //SELECT PROFESORES
    public ArrayList<Profesor> selectProfesores(String selectCiclo, String selectCurso){
        ArrayList<Profesor> profesores = new ArrayList();
        Cursor c = null;
        if(selectCiclo.equals("TODOS") && selectCurso.equals("TODOS")){
            c = db.rawQuery("SELECT * FROM "+TABLE_PROFESORES+";",null);
        }else{
            if(!selectCiclo.equals("TODOS") && !selectCurso.equals("TODOS")){
                c = db.rawQuery("SELECT * FROM "+TABLE_PROFESORES+" WHERE curso='"+selectCiclo+" "+selectCurso+"';",null);
            }else{
                if(!selectCiclo.equals("TODOS") && selectCurso.equals("TODOS")){
                    c = db.rawQuery("SELECT * FROM "+TABLE_PROFESORES+" WHERE curso LIKE '"+selectCiclo+" %';",null);
                }else{
                    c = db.rawQuery("SELECT * FROM "+TABLE_PROFESORES+" WHERE curso LIKE '% "+selectCurso+"';",null);
                }
            }
        }
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
    //ELIMINAR BASE DE DATOS
    public void dropDatabase(){
        context.deleteDatabase(DATA_BASE_NAME);
    }


    //CLASE MY DBHELPER
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