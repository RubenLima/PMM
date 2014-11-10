package com.example.mati.ejer6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Ejer6 extends Activity {


   // private TextView textView;
    private Button button;
    private Button button2;
    TextView texto;
    public static int COD_RESPUESTA=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejer6);
        texto=(TextView)findViewById(R.id.textView);
        button=(Button)findViewById(R.id.button);
        button2=(Button)findViewById(R.id.button2);
        texto= (TextView)findViewById(R.id.miLbl);
        //Si regreso de otra actividad
        if (savedInstanceState!=null){
            String mensajePasado=savedInstanceState.getString("TEXTO");
            texto.setText(mensajePasado);

        }
        button.setOnClickListener( new View.OnClickListener(){

                public void onClick(View arg0)
                {
                Intent miIntent= new Intent(Ejer6.this, pantalla2.class);
                Bundle miBundle=new Bundle();
                String mensaje= "Vengo de la 1 " ;
                miBundle.putString("TEXTO", mensaje);
                miIntent.putExtras(miBundle);
                startActivityForResult(miIntent, COD_RESPUESTA);
                //texto.setText(mensaje);
            }

        });
    }




    public void onActivityResult(int cod_resp, int cod_result,Intent intent){
        if (cod_result== RESULT_OK) {
            Bundle otroBundle = intent.getExtras();
            texto.setText(otroBundle.getString("DEVUELTO"));
        }
    }



}