package com.example.mati.proysumaresta;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class proySumaResta extends Activity {

    CheckBox checkBox;
    Button radioButton;
    Button radioButton2;
    TextView txtHobby;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proy_suma_resta);


        final EditText editText = (EditText)findViewById(R.id.editText);
        final EditText editText2 = (EditText)findViewById(R.id.editText2);
        final RadioGroup ruben = (RadioGroup)findViewById(R.id.ruben);

        final RadioButton radioButton = (RadioButton)findViewById(R.id.radioButton);
        final RadioButton radioButton2 = (RadioButton)findViewById(R.id.radioButton2);
        final Button button = (Button)findViewById(R.id.button);

        final CheckBox checkBox = (CheckBox)findViewById(R.id.checkBox);
        final ImageView imageView = (ImageView)findViewById(R.id.imageView);

        ruben.clearCheck();
        ruben.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String textoOpcion = "";
                if (group.getCheckedRadioButtonId() == R.id.radioButton)
                    textoOpcion += "Suma:" + checkedId;
                if (group.getCheckedRadioButtonId() == R.id.radioButton2)
                    textoOpcion += "Resta:" + checkedId;
                editText.setText(textoOpcion);


            }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.proy_suma_resta, menu);
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
    }