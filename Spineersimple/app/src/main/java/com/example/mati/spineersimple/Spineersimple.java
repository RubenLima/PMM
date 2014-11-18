package com.example.mati.spineersimple;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class Spineersimple extends Activity {
    Spinner miSpinner;

        final static ArrayList<Persona> personas = new ArrayList<Persona>();
        private boolean isFirst = false;
        public static int COD_RESPONSE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spineersimple);
        // final Button profileBtn = (Button)findViewById(R.id.profileBtn);
        // asi añade personas en un array list
        personas.add(new Persona("Ángela", "Ruiz", 18, R.drawable.ic_launcher));
        personas.add(new Persona("Cristina", "García", 30, R.drawable.ic_launcher));
        personas.add(new Persona("David", "González", 25, R.drawable.ic_launcher));
        personas.add(new Persona("Juan", "Ferrer", 20, R.drawable.ic_launcher));
        personas.add(new Persona("Victor", "López", 25, R.drawable.ic_launcher));
        //String mensaje;

        final String aPersonas[] = new String[personas.size()];
        for(int i = 0; i < personas.size(); i++){ aPersonas[i] = personas.get(i).getNombre();}

        miSpinner=(Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String>miAdaptador = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,aPersonas);
        miAdaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        miSpinner.setAdapter(miAdaptador);
        miSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
              //  String mensaje="item clicked=>" + persona[position];
                //showToast(mensaje);
                Intent myIntent = new Intent(Spineersimple.this, Profile.class);
                Bundle myBundle = new Bundle();
                Persona persona = personas.get(miSpinner.getSelectedItemPosition());
                myBundle.putSerializable("PROFILE", persona);
                myIntent.putExtras(myBundle);
                startActivityForResult(myIntent, COD_RESPONSE);
            }



            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    public void onActivityResult(int cod_resp, int cod_result, Intent intent){
        if (cod_result== RESULT_OK) {
            Bundle otherBundle = intent.getExtras();
            showToast(otherBundle.getString("RETURNED"));
        }
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
