package com.example.mati.solomibici;

import android.app.Activity;
import android.os.Bundle;

public class Juego extends Activity {
    private VistaJuego vistaJuego;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juego);
        vistaJuego = (VistaJuego) findViewById(R.id.VistaJuego);
    }


   // @Override
  //  protected void onDestroy() {
  //      vistaJuego.getHilo().detener();
  //      super.onDestroy();
  //  }

  //  protected void onPause() {
  //          super.onPause();
   //         vistaJuego.getHilo().pausar();
   //     }

   // protected void onResume() {
  //          super.onResume();
  //          vistaJuego.getHilo().reanudar();
  //      }

    }
