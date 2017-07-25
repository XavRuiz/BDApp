package com.example.javier.bdapp;

/**
 * Created by alumno on 24/07/2017.
 */

public class Asignatura {
    private int codigo, cantidadEstudiantes;
    private String nombre;

    public Asignatura (){

    }

    public Asignatura(int codigo, int cantidadEstudiantes, String nombre) {
        this.codigo = codigo;
        this.cantidadEstudiantes = cantidadEstudiantes;
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCantidadEstudiantes() {
        return cantidadEstudiantes;
    }

    public void setCantidadEstudiantes(int cantidadEstudiantes) {
        this.cantidadEstudiantes = cantidadEstudiantes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String toString() {
        return "codigo: " + codigo + "nombre: "+ nombre + "cantidad Estudiantes: "+ cantidadEstudiantes;
    }
}
