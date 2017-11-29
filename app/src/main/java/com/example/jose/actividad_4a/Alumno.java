package com.example.jose.actividad_4a;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jose on 28/11/2017.
 */

public class Alumno implements Parcelable {
    private String nombre;
    private String edad;
    private String curso;
    private String notaM;
    //private int id;

    public Alumno( String nombre, String edad, String curso, String notaM) {
     //   this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.curso = curso;
        this.notaM = notaM;
    }

   /* public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    */
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getNotaM() {
        return notaM;
    }

    public void setNotaM(String notaM) {
        this.notaM = notaM;
    }

    protected Alumno(Parcel in) {
       // id = in.readInt();
        nombre = in.readString();
        edad = in.readString();
        curso = in.readString();
        notaM = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //dest.writeInt(id);
        dest.writeString(nombre);
        dest.writeString(edad);
        dest.writeString(curso);
        dest.writeString(notaM);
    }

    public static final Parcelable.Creator<Alumno> CREATOR = new Parcelable.Creator<Alumno>() {
        @Override
        public Alumno createFromParcel(Parcel in) {
            return new Alumno(in);
        }

        @Override
        public Alumno[] newArray(int size) {
            return new Alumno[size];
        }
    };
}