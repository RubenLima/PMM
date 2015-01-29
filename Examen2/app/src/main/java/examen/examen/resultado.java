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

    private Canvas clear;
    private String figura = " ";
    private float radius, height, width = 10;


    @Override

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final TextView nameTV = (TextView)findViewById(R.id.textView);
        final TextView ageTV = (TextView)findViewById(R.id.textView1);
        final ImageView photoIV = (ImageView)findViewById(R.id.imageView);
        final Button button= (Button)findViewById(R.id.button);
        Bundle act1Bundle = getIntent().getExtras();
        sonido persona = (sonido)act1Bundle.getSerializable("PROFILE");
        nameTV.setText("Zona: " +persona.getZona()+ " " +persona.getContinente());

        ageTV.setText("Precio: " +Integer.toString(persona.getPrecio()));
        //photoIV.setBackgroundResource(persona.getFoto());
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

    public boolean onCreateOptionsMenu(Menu menu) {
//Alternativa 1
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.m, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.MnuOpc1:



                showToast("Insercion");


                return true;
            case R.id.MnuOpc2:
                 class MyView extends View {
                    public MyView(Context context) {
                        super(context);
                    }
                    protected void onDraw(Canvas canvas) {
                        //Dentro de este método utilizamos los métodos para dibujar BitmapDrawable
                        Paint pincel = new Paint();
                        pincel.setColor(Color.BLUE);
                        pincel.setStrokeWidth(15);
                        pincel.setStyle(Paint.Style.FILL_AND_STROKE);

                            canvas.drawCircle((getWidth() / 2), (getHeight() / 2), radius, pincel);

                    }
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }}

    private void showToast(String s) {
    }
}
