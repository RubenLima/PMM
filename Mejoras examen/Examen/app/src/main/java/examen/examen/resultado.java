package examen.examen;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * Created by mati on 29/01/15.
 */
public class resultado  extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final TextView nameTV = (TextView)findViewById(R.id.textView);//zona
        final TextView ageTV = (TextView)findViewById(R.id.textView1);//tarifa
        final TextView precio = (TextView)findViewById(R.id.textView2);//precio
        final TextView decoracion =(TextView)findViewById(R.id.textView3);//decoracion
        final ImageView photoIV = (ImageView)findViewById(R.id.imageView);
        final Button button= (Button)findViewById(R.id.button);
        Bundle act1Bundle = getIntent().getExtras();
        sonido persona = (sonido)act1Bundle.getSerializable("PROFILE");
        String destino = (String)act1Bundle.getSerializable("ZONE");
       // sonido destino = (sonido)act1Bundle.getSerializable("ZONE");
        String tarifa = (String)act1Bundle.getSerializable("TAX");
        String peso = (String)act1Bundle.getSerializable("WEIGHT");
        String deco = (String)act1Bundle.getSerializable("DECO");
        String pre = (String)act1Bundle.getSerializable("PRE");

        //MAPA
        if(persona.getZona().equals(" Zona A")){ photoIV.setBackgroundResource(R.drawable.asia_oceania);}
        else if(persona.getZona().equals("Zona B")){ photoIV.setBackgroundResource(R.drawable.america_africa);}
        else{ photoIV.setBackgroundResource(R.drawable.europa);}
        //ZONA
        nameTV.setText("ZONA: " + persona.getZona() + " (" + persona.getContinente() + ")");
        //TARIFA
        ageTV.setText("TARIFA: " + tarifa);

        //DECO
        decoracion.setText(""); decoracion.setText("DECORACION: " + deco);
        //PVP
        precio.setText("COSTE FINAL: " + pre + " €");

        button.setOnClickListener(new View.OnClickListener()
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