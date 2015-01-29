package traductor.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


public class MyActivity extends Activity {

    EditText Etext;
    Button boton;
    public static int COD_RESPONSE =0;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        boton = (Button) findViewById(R.id.button);
       // miButton = (Button) findViewById(R.id.button);

        boton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent myIntent = new Intent(MyActivity.this, sonido.class);
                Bundle myBundle = new Bundle();
               // Persona persona = personas.get(mySpinner.getSelectedItemPosition());
                myBundle.putSerializable("PROFILE", "Hola");
                myIntent.putExtras(myBundle);
                startActivityForResult(myIntent, COD_RESPONSE); // CREATED ProfileActivity in AndroidManifest.xml
                mp = MediaPlayer.create(MyActivity.this, R.raw.explosion);
                mp.start();

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
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
