package examen.examen;

/**
 package examen.examen;

 /**
 * Created by mati on 29/01/15.
 */



import java.io.Serializable;
public class sonido  implements Serializable{
    private String zona;
    private String continente;
    private double precio;

    sonido (){}
    sonido (String zona, String continente, double precio )
    {
        this.zona = zona;
        this.continente = continente;
        this.precio = precio;

    }
    // Zona
    public String getZona(){ return zona;}
    // Continente
    public String getContinente(){ return continente;}
    // Precio
    public  Double getPrecio(){ return precio;}

}