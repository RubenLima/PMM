package com.example.mati.solomibici;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.mati.solomibici.R;


public class SoloBici extends Activity {

    private Button bAcercaDe;
    private Button bJuego;
    //private Button bPreferencias;
    private Button bSalir;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        //Boton y escuchador para la pantalla "Juego"
        bJuego = (Button) findViewById(R.id.Boton01);
        bJuego.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                lanzarJuego();
            }
        });
        //Boton y escuchador para la pantalla "Acerca de"
        bAcercaDe = (Button) findViewById(R.id.Boton03);
        // bAcercaDe.setOnClickListener(new OnClickListener(){
        // public void onClick(View view) {
        //   lanzarAcercaDe();
        //   }
        // });

        //Boton y escuchador para la pantalla "Salir"
        bSalir = (Button) findViewById(R.id.Boton04);
        bSalir.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                lanzarSalir();
            }
        });
        }
    //Metodo que activa la pantalla Juego

    public void lanzarJuego(){
        Intent i = new Intent(this, Juego.class);
        startActivity(i);
    }

    //Metodo que activa la pantalla AcercaDe
   // public void lanzarAcercaDe(){
     //   Intent i = new Intent(this, AcercaDe.class);
     //   startActivity(i);
   // }


    //Metodo que activa la pantalla AcercaDe
    public void lanzarSalir(){
        finish();
    }

    @Override protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
    }
    @Override protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
    }
    @Override protected void onPause() {
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
        super.onPause();
    }
    @Override protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
    }
    @Override protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
    }

    @Override protected void onDestroy() {
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }


     // aqui emter esto en primera linea :
    //SharedPreferences pref =
   // PreferenceManager.getDefaultSharedPreferences(
   // MyActivity.this);


    }
