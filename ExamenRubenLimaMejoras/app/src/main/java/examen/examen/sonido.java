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
    // NOMBRE
    public String getZona(){ return zona;}
    // APELLIDO
    public String getContinente(){ return continente;}
    // EDAD
    public   double getPrecio(){ return precio;}

}
