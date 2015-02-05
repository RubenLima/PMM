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
   // EditText Etext2;
   // EditText Etext3;
    TextView Text;
   // TextView Text2;
   // TextView Text3;
    RadioButton Radiobutton1 ;
    RadioButton Radiobutton2 ;
    CheckBox Checkbox1;
    CheckBox Checkbox2;
    int resultado;
    EditText pes;
    double tarifa;
    boolean urgente;


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

        miButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent myIntent = new Intent(MyActivity.this, resultado.class);
                Bundle myBundle = new Bundle();
                sonido persona = personas.get(miSpinner.getSelectedItemPosition());
                myBundle.putSerializable("PROFILE", persona);
                myIntent.putExtras(myBundle);
                startActivityForResult(myIntent, COD_RESPONSE); // CREATED ProfileActivity in AndroidManifest.xml

           int ope ;







                if(Checkbox1.isChecked()){

                    if(Checkbox2.isChecked()){

                        showToast("Tarjeta dedicatoria y caja regalo ");
                    }
                else {
                        showToast("Caja regalo");
                    }

                }
                else if(Checkbox2.isChecked()){
                    showToast("Tarjeta dedicatoria");

                }



                else{

                    showToast("");
                }

				
					if(Radiobutton1.isChecked()){ myBundle.putSerializable("TAX", "Normal");}
                else{ myBundle.putSerializable("TAX", "Urgente");}

				
				if(!pes.getText().toString().equals(""))
                {
                    tarifa = calcTarifa(sonido.getPrecio(), Double.parseDouble(pes.getText().toString()), Radiobutton2.isChecked());
                    myBundle.putSerializable("Tarifa", tarifa);

                }
                else{ myBundle.putSerializable("PVP", 0.0); showToast("ERROR. Peso incorrecto");}


            }
        });

        //Abrimos la base de datos en modo escritura
        ClientesSQLiteHelper cliBDh = new ClientesSQLiteHelper(this, "DBClientes", null, 1);

        //Obtenemos referencia a la base de datos para poder modificarla.
        SQLiteDatabase bd = cliBDh.getWritableDatabase();

        //En caso de abrir de forma correcta la base de datos
        if (bd!=null) {

            for (int cont=1; cont<=3; cont++) {
                //Creamos los datos
                int codigo = cont;
                String nombre = "Cliente" + cont;
                String telefono = cont + "XXXXXXX";

                bd.execSQL("INSERT INTO Clientes (codigo, nombre, telefono) " +
                        "VALUES (" + codigo + ", '" + nombre + "', '" + telefono + "')");
            }

            //Insertar un registro
            bd.execSQL("INSERT INTO Clientes (nombre, telefono) VALUES ('cli1','11111') ");

            //Cerramos la base de datos
            bd.close();
        } //del if
    }




    public void onActivityResult(int cod_resp, int cod_result, Intent intent){
        if (cod_result== RESULT_OK) {
            Bundle otherBundle = intent.getExtras();
            showToast(otherBundle.getString("RETURNED"));
        }
    }

    private double calcTarifa(double precio, double peso, boolean urgente)
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
            case R.id.draw:
                startActivity(new Intent(MyActivity.this, ClientesSQLiteHelper.class));
                return true;

            case R.id.save:
                showToast("Save");
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
}




    }