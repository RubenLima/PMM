package com.example.mati.miejemplodelayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
    }

    final Button b1 = (Button) findViewById(R.id, boton1);
    final Button b2 = (Button) findViewById(R.layout, boton2);

    b1.setOnClickListener(new View.OnClickListener()

    {
        public void onClick (View v){
        Intent miIntent = new Intent(this, ClaseTabla.class);
        startActivity(miIntent);
    }
    });


    b2.setOnClickListener((v)->

    {
        public void onClick (View v){
        Intent miIntent = new Intent(this, ClaseGrid.class);
        startActivity(miIntent);

    });
}
}
