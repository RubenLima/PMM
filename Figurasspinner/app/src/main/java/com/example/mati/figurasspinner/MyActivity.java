package com.example.mati.figurasspinner;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Canvas;

import org.w3c.dom.Text;


public class MyActivity extends Activity {

    Spinner miSpinner;
    Button miButton;
    EditText Etext;
    EditText Etext2;
    EditText Etext3;
    TextView Text;
    TextView Text2;
    TextView Text3;

    final static String figuras[] = {"Circulo", "Rectangulo"};
    private String figura = " ";
    private float radius, height, width = 0;
    private Canvas clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        String mensaje;
        miSpinner = (Spinner) findViewById(R.id.spinner);
        miButton = (Button) findViewById(R.id.button);
        Text = (TextView) findViewById(R.id.text1);
        Text2 = (TextView) findViewById(R.id.text2);
        Text3 = (TextView) findViewById(R.id.text3);
        Etext = (EditText) findViewById(R.id.edittext1);
        Etext2 = (EditText) findViewById(R.id.edittext2);
        Etext3 = (EditText) findViewById(R.id.edittext3);
        final RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.dibujo);

        ArrayAdapter<String> miAdaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, figuras);
        miAdaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        miSpinner.setAdapter(miAdaptador);
        miSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView arg0, View arg1, int position, long id) {
                String mensaje = "";
                mensaje = "Has seleccionado " + figuras[position];
                showToast(mensaje);

                figura = figuras[miSpinner.getSelectedItemPosition()];
                if (figura == "Rectangulo") {
                    Etext3.setVisibility(View.INVISIBLE);
                    Etext.setVisibility(View.VISIBLE);
                    Etext2.setVisibility(View.VISIBLE);
                    Text3.setVisibility(View.VISIBLE);
                    Text.setVisibility(View.INVISIBLE);
                    Text2.setVisibility(View.VISIBLE);

                } else {
                    Etext3.setVisibility(View.VISIBLE);
                    Etext.setVisibility(View.INVISIBLE);
                    Etext2.setVisibility(View.INVISIBLE);
                    Text3.setVisibility(View.INVISIBLE);
                    Text.setVisibility(View.VISIBLE);
                    Text2.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                // meter los 3 campos invisibles

                Etext.setVisibility(View.INVISIBLE);
                Etext2.setVisibility(View.INVISIBLE);
                Etext3.setVisibility(View.INVISIBLE);
            }
        });
        miButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick (View v)
            {
                if(figura == "Rectangulo")
                {
                    if((Etext2.getText().toString().equals("")) || (Etext.getText().toString().equals("")))
                    { showToast("Campos Vacios");}
                    else
                    {
                        height = Float.valueOf(Etext2.getText().toString());
                        width = Float.valueOf(Etext.getText().toString());
                        View canvasView = new MyView(getBaseContext());
                        relativeLayout.addView(canvasView);
                    }

                }
                else if(figura == "Circulo")
                {
                    if(Etext3.getText().toString().equals(""))
                    { showToast("Campo Vacio");}
                    else
                    {
                        radius = Float.valueOf(Etext3.getText().toString());
                        View canvasView = new MyView(getBaseContext());
                        relativeLayout.addView(canvasView);
                    }
                }
            }

        });

    }

    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public class MyView extends View {
        public MyView(Context context) {
            super(context);
        }




    protected void onDraw(Canvas canvas) {
        //Dentro de este método utilizamos los métodos para dibujar BitmapDrawable
        Paint pincel = new Paint();
        pincel.setColor(Color.BLUE);
        pincel.setStrokeWidth(15);
        pincel.setStyle(Paint.Style.FILL_AND_STROKE);
        // ahora meter if

        if (figura == "Rectangulo") {
            canvas.drawRect((getWidth()/2)-(width/2), (getHeight()/2)-(height/2),
                    (getWidth()/2)+(width/2), (getHeight()/2)+(height/2), pincel);
        } else {
            canvas.drawCircle((getWidth()/2), (getHeight()/2), radius, pincel);
        }


    }

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
