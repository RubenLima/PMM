package com.example.mati.miejemplodelayout;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by mati on 16/10/14.
 */
public class clasetabla extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
}
    public void cmdCerrarClick(View v){
        finish();
    }
