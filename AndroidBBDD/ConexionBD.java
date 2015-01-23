package com.example.mati.basedatos;

/**
 * Created by mati on 13/01/15.
 */
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ConexionBD {
    private SQLiteDatabase db;
    private Context nContext;
    private ClientesSQLiteHelper objBD;


    //Constructor de la Clase
    //Recibe como parámetro una variable de Tipo contexto
    // Esto debido a que Para acceder o crear la BD lo pide la Clase SQLiteOpenHelper
    public ConexionBD(Context c){
        nContext=c;

    }

    public void abrirConexion(){
        objBD =new ClientesSQLiteHelper(nContext,"DBClientes",null,1 );
        db=objBD.getWritableDatabase();
    }

    public void cerrarConexion(){
        db.close();
    }

    public boolean insertar(String codigo, String nombre,String telefono){
        boolean resultado=false;


        try{
            String query="INSERT INTO Clientes(codigo, nombre, telefono) VALUES('"+codigo+"','"+nombre+"','"+
                    telefono+"')";

            db.execSQL(query);
            resultado=true;
            return resultado;

        }
        catch (Exception e){
            resultado=false;
            return resultado;
        }
    }


    


 }

