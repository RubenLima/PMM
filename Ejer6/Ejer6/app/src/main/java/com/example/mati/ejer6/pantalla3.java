package com.example.mati.ejer6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class pantalla3 extends Ejer6 {

    private TextView texto1;
    private Button button;
    private Button button2;
    //TextView textView;
    public static int COD_RESPUESTA=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejer6);
        texto1 = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla2);

        final TextView otroSaludo= (TextView)findViewById(R.id.textView);
        final Button button= (Button)findViewById(R.id.button);
        final Button button2= (Button)findViewById(R.id.button2);
        Bundle  miBundleRecoger = getIntent().getExtras();
        texto1.setText(miBundleRecoger.getString("TEXTO"));
        final String completarSaludo=miBundleRecoger.getString("TEXTO");
        button.setOnClickListener( new View.OnClickListener(){
            public void onClick(View v){
                Intent vueltaIntent= new Intent();
                Bundle vueltaBundle=new Bundle();
                String mensajeDevuelto= "Devuelvo a Principal " + completarSaludo;
                vueltaBundle.putString("DEVUELTO", mensajeDevuelto);
                vueltaIntent.putExtras(vueltaBundle);
                setResult(RESULT_OK, vueltaIntent);
                finish();

            }

        });


    }



}

