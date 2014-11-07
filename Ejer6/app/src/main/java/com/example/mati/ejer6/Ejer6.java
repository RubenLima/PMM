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




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ejer6, menu);
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
}
