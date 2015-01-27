package roberto.solobici;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.roberto.solobici.R;


public class SoloBici extends Activity {

    private Button bAcercaDe;
    private Button bJuego;
    private Button bSalir;
    private Button bConfigurar;

    private Button btnPreferencias;
    private Button btnObtenerPreferencias;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solo_bici);

        //Boton y escuchador para la pantalla "Juego"
        bJuego = (Button) findViewById(R.id.Boton01);
        bJuego.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                lanzarJuego();
            }
        });
        //Boton y escuchador para la pantalla "Configurar"
        bConfigurar = (Button) findViewById(R.id.Boton02);
        bConfigurar.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                lanzarPreferencias();
            }
        });
        //Boton y escuchador para la pantalla "Acerca de"
        bAcercaDe = (Button) findViewById(R.id.Boton03);
        bAcercaDe.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                lanzarAcercaDe();
            }
        });
        /*Boton y escuchador para la pantalla "Salir"*/
        bSalir = (Button) findViewById(R.id.Boton04);
        bSalir.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                lanzarSalir();
            }
        });
    }


    private void mostrarPreferencias(){

       // SharedPreferences pref = getSharedPreferences("com.example.roberto.solobici.preferencias",MODE_PRIVATE);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(SoloBici.this);
        String s = "Música: " + pref.getBoolean("musica",true) + ", Preguntas: " + pref.getString("preguntas","")+", Conexion: " + pref.getString("conexion","");

        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();

    }

    //Metodo que activa la pantalla Juego
    public void lanzarJuego(){
        Intent i = new Intent(this, Juego.class);
        startActivity(i);
    }

    //Metodo que activa la pantalla AcercaDe
    public void lanzarAcercaDe(){
        Intent i = new Intent(this, AcercaDe.class);
        startActivity(i);
    }

    //Metodo que activa la pantalla Preferencias
    public void lanzarPreferencias(){
        Intent i = new Intent(this, Preferencias.class);
        startActivity(i);
    }

    //Metodo que activa la pantalla AcercaDe
    public void lanzarSalir(){
        mostrarPreferencias();
        finish();
    }


}
