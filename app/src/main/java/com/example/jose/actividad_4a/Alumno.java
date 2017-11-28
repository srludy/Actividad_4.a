package com.example.jose.actividad_4a;

/**
 * Created by Jose on 28/11/2017.
 */

public class Alumno {
    private String nombre;
    private String edad;
    private String curso;
    private String notaM;

    public Alumno(String nombre, String edad, String curso, String notaM) {
        this.nombre = nombre;
        this.edad = edad;
        this.curso = curso;
        this.notaM = notaM;
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

    public String getNotaM() {
        return notaM;
    }

    public void setNotaM(String notaM) {
        this.notaM = notaM;
    }
}
