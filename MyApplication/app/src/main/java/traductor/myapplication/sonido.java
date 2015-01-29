package traductor.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/**
 * Created by mati on 28/01/15.
 */
public class sonido extends Activity {

   // public class Audio{

      //  MediaPlayer mp;

      //  public void playClick(Context context){
      //      mp = MediaPlayer.create(context, R.raw.explosion);
      //      mp.start();
      //  }
    //}
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sonido);
        //audio = new Audio();
       // audio.playClick(this);

       // Audio audio = new Audio(MyActivity.getApplicationContext());
       // audio.playClick();


     //   MediaPlayer miMediaPlayer =
        //        MediaPlayer.create(getContext(), R.raw.explosion);
      //  miMediaPlayer.start();
        final Button boton = (Button)findViewById(R.id.button);
        Bundle act1Bundle = getIntent().getExtras();
        boton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent retIntent = new Intent();
                Bundle retBundle = new Bundle();
                String retText = "¿Otra traduccion?";
                retBundle.putString("RETURNED", retText);
                retIntent.putExtras(retBundle);
                setResult(RESULT_OK, retIntent);
                finish();
            }
        });
    }
}
