package com.example.mati.spineersimple;

import com.example.mati.spineersimple.Spineersimple;

/**
 * Created by mati on 13/11/14.
 */
public class Persona extends Spineersimple {

    private String nombre;
    private String apellido;
    private int edad;
    private int foto;


     Persona( String nombre,String apellido,int edad,int foto){
        this.nombre=nombre;
        this.apellido=apellido;
        this.edad=edad;
        this.foto=foto;

    }
    public String getNombre=nombre;
    public String getApellido=apellido;
    public int getEdad=edad;
    public int getFoto=foto;
}
