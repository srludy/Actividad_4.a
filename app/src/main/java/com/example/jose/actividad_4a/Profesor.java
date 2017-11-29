package com.example.jose.actividad_4a;

/**
 * Created by Jose on 28/11/2017.
 */

public class Profesor {
    private String nombre;
    private String edad;
    private String curso;
    private String despacho;
    private int id;


    public Profesor(int id,String nombre, String edad, String curso, String despacho) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.curso = curso;
        this.despacho = despacho;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getDespacho() {
        return despacho;
    }

    public void setDespacho(String despacho) {
        this.despacho = despacho;
    }
}
