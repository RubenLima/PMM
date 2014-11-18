package com.example.mati.spineersimple;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;


public class Spineersimple extends Activity {
    Spinner miSpinner;
   // final static String persona; // con add ,mirar como se hace
    //persona.add=new persona("julio","perez","41");


// asi se meten las personas !important
    private Persona[] personas=new Persona[]{
            new Persona("angeles","campos",21,0),
            new Persona("maria","perez",31,0),
            new Persona("jose","martinez",41,0)

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spineersimple);
        String mensaje;
        miSpinner=(Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String>miAdaptador = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,persona);
        miAdaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        miSpinner.setAdapter(miAdaptador);
        miSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String mensaje="item clicked=>" + persona[position];
                showToast(mensaje);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }

    public void showToast(String text){

        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }


     /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.spineersimple, menu);
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
    } */
}
