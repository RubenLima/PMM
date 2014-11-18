package com.test.juankza.pmyspinner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends Activity {

    Spinner mySpinner;
    final static ArrayList<Persona> personas = new ArrayList<Persona>();
    private boolean isFirst = false;
    public static int COD_RESPONSE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button profileBtn = (Button)findViewById(R.id.profileBtn);

        personas.add(new Persona("Ángela", "Ruiz", 18, R.drawable.ic_profile));
        personas.add(new Persona("Cristina", "García", 30, R.drawable.ic_profile));
        personas.add(new Persona("David", "González", 25, R.drawable.ic_profile));
        personas.add(new Persona("Juan", "Ferrer", 20, R.drawable.ic_profile));
        personas.add(new Persona("Victor", "López", 25, R.drawable.ic_profile));

        final String aPersonas[] = new String[personas.size()];
        for(int i = 0; i < personas.size(); i++){ aPersonas[i] = personas.get(i).getNombre();}

        mySpinner = (Spinner)findViewById(R.id.mySpinner);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, aPersonas);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mySpinner.setAdapter(myAdapter);

        profileBtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent myIntent = new Intent(MainActivity.this, ProfileActivity.class);
                Bundle myBundle = new Bundle();
                Persona persona = personas.get(mySpinner.getSelectedItemPosition());
                myBundle.putSerializable("PROFILE", persona);
                myIntent.putExtras(myBundle);
                startActivityForResult(myIntent, COD_RESPONSE); // CREATED ProfileActivity in AndroidManifest.xml

            }

        });

    }

    public void onActivityResult(int cod_resp, int cod_result, Intent intent){
        if (cod_result== RESULT_OK) {
            Bundle otherBundle = intent.getExtras();
            showToast(otherBundle.getString("RETURNED"));
        }
    }

    private void showToast(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();

    }

}
