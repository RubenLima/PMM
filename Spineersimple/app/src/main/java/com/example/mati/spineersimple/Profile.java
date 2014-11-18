package com.example.mati.spineersimple;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mati.spineersimple.Persona;
import com.example.mati.spineersimple.R;

/**
 * Created by mati on 18/11/14.
 */
public class Profile extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        final TextView nameTV = (TextView)findViewById(R.id.nameTV);
        final TextView ageTV = (TextView)findViewById(R.id.ageTV);
        final ImageView photoIV = (ImageView)findViewById(R.id.photoIV);
        final Button returnBtn = (Button)findViewById(R.id.returnBtn);

        Bundle act1Bundle = getIntent().getExtras();
        Persona persona = (Persona)act1Bundle.getSerializable("PROFILE");

        nameTV.setText("Nombre: " +persona.getNombre()+ " " +persona.getApellido());
        ageTV.setText("Edad: " +Integer.toString(persona.getEdad()));
        photoIV.setBackgroundResource(persona.getFoto());

        returnBtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent retIntent = new Intent();
                Bundle retBundle = new Bundle();
                String retText = "Devuelto con éxito";

                retBundle.putString("RETURNED", retText);
                retIntent.putExtras(retBundle);

                setResult(RESULT_OK, retIntent);
                finish();

            }

        });

    }

}
