package com.test.juankza.pmyspinner;

import java.io.Serializable;

public class Persona implements Serializable{

    private String nombre;
    private String apellido;
    private int edad;
    private int foto;

    Persona (){}

    Persona (String nombre, String apellido, int edad, int foto)
    {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.foto = foto;

    }

    // NOMBRE
    public String getNombre(){ return nombre;}
    // APELLIDO
    public String getApellido(){ return apellido;}
    // EDAD
    public int getEdad(){ return edad;}
    //FOTO
    public int getFoto(){ return foto;}

}
