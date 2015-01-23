package com.example.mati.basedatos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity2 extends ActionBarActivity {

    private EditText codigo, nombre, telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        codigo=(EditText)findViewById(R.id.code);
        nombre=(EditText)findViewById(R.id.name);
        telefono=(EditText)findViewById(R.id.phone);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void limpiar(View view){
        codigo.setText("");
        nombre.setText("");
        telefono.setText("");

    }

    public void agregar(View view){
        String nombre,apellido,telefono;
        nombre=this.codigo.getText().toString();
        apellido=this.nombre.getText().toString();
        telefono=this.telefono.getText().toString();


        ConexionBD ObjCnx = new ConexionBD(this);

        ObjCnx.abrirConexion();



   
        if(ObjCnx.insertar(nombre,apellido,telefono)==true){
            String texto ="Elemento Agregado Corectamente";
            Toast toast = Toast.makeText(this, texto, Toast.LENGTH_LONG);
            toast.show();


        }
        else{
            String texto ="Error al Agregar Elemento";
            Toast toast = Toast.makeText(this, texto, Toast.LENGTH_LONG);
            toast.show();
        }


        ObjCnx.cerrarConexion();

   



    }
}
