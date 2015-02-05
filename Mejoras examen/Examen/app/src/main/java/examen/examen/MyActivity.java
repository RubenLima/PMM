package examen.examen;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MyActivity extends Activity {

    Spinner miSpinner;
    Button miButton;
    EditText Etext;
    TextView Text;
    RadioButton Radiobutton1 ;
    RadioButton Radiobutton2 ;
    CheckBox Checkbox1;
    CheckBox Checkbox2;
    EditText pes;
    double tarifa;



    final static ArrayList<sonido> personas = new ArrayList<sonido>();
    public static int COD_RESPONSE =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        personas.add(new sonido("Zona A", "Asia y oceania", 30 ));
        personas.add(new sonido("Zona B", "America y Africa", 20));
        personas.add(new sonido("Zona C", "Europa", 10));


        final String aPersonas[] = new String[personas.size()];
        for(int i = 0; i < personas.size(); i++){ aPersonas[i] = personas.get(i).getZona();}
        miSpinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, aPersonas);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        miSpinner.setAdapter(myAdapter);
        miButton = (Button) findViewById(R.id.button);
        pes = (EditText)findViewById(R.id.editText);
        Radiobutton1 = (RadioButton)findViewById(R.id.radioButton);
        Radiobutton2 = (RadioButton)findViewById(R.id.radioButton2);
        Checkbox1=(CheckBox)findViewById(R.id.checkBox);
        Checkbox2=(CheckBox)findViewById(R.id.checkBox2);

        miButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent myIntent = new Intent(MyActivity.this, resultado.class);
                Bundle myBundle = new Bundle();
                sonido persona = personas.get(miSpinner.getSelectedItemPosition());
                myBundle.putSerializable("PROFILE", persona);


                String deco=" ";
                if(Checkbox1.isChecked() && Checkbox2.isChecked()){ deco = "Con caja regalo y con dedicatoria";}
                else if(Checkbox1.isChecked()){ deco = "Con caja regalo";}
                else if(Checkbox2.isChecked()){ deco = "Con dedicatoria";}
                else{ deco = "No";}
                myBundle.putSerializable("DECO", deco);


                if(Radiobutton1.isChecked()){ myBundle.putSerializable("TAX", "Normal");}
                else{ myBundle.putSerializable("TAX", "Urgente");}

                myBundle.putSerializable("WEIGHT", pes.getText().toString());


                if(!pes.getText().toString().equals(""))
                {
                   tarifa = calcTarifa(persona.getPrecio(), Double.parseDouble(pes.getText().toString()), Radiobutton2.isChecked());
                    myBundle.putSerializable("Tarifa", tarifa);


                }
                else{ myBundle.putSerializable("PVP", 0.0); showToast("ERROR. Peso incorrecto");}
                myIntent.putExtras(myBundle);
                startActivityForResult(myIntent, COD_RESPONSE); // CREATED ProfileActivity in AndroidManifest.xml

            }
        });

    }




    public void onActivityResult(int cod_resp, int cod_result, Intent intent){
        if (cod_result== RESULT_OK) {
            Bundle otherBundle = intent.getExtras();
            showToast(otherBundle.getString("RETURNED"));
        }
    }

    private  double calcTarifa(double precio, double peso, boolean urgente)
    {
        double var = 0.0;

        if(peso <= 5.0){ var = 1.0;}
        else if(peso <= 10.0){ var = 1.5;}
        else if(peso > 10.0){ var = 2.0;}

        double res = precio + (var * peso);

        if(urgente){ return (res * 1.3);}
        else{ return res;}

    }

    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
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
        switch(item.getItemId())
        {
            case R.id.pinta:
                startActivity(new Intent(MyActivity.this, ClientesSQLiteHelper.class));
                return true;

            case R.id.guarda:
                showToast("Save");
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }




}