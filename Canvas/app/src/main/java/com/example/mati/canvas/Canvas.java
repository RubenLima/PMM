package com.example.mati.canvas;

import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class Canvas extends Activity {
    private BitmapDrawable miImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new EjemploView(this));
    }

    public class EjemploView extends View {
        public EjemploView(Context contexto) {
            super(contexto);
            Resources res = contexto.getResources();
            miImagen = (BitmapDrawable) res.getDrawable(R.drawable.ic_launcher);
            miImagen.setBounds(new Rect(30,30,300,300));
            this.setBackgroundColor(Color.BLACK);
        }
        @Override
        protected void onDraw(android.graphics.Canvas canvas) {
            //Dentro de este método utilizamos los métodos para dibujar BitmapDrawable
            miImagen.draw(canvas);
            Paint pincel = new Paint ();
            pincel.setColor(Color.BLUE);
            pincel.setStrokeWidth(15);
           // pincel.setTextSize(20);
            pincel.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(175,175,80,pincel);
            pincel.setStrokeWidth(1);
            pincel.setStyle(Paint.Style.FILL_AND_STROKE);
           // canvas.drawText("hi",80,300,pincel);
            pincel.setColor(Color.RED);
            pincel.setStrokeWidth(15);
            canvas.drawPoint(0,0,pincel);
            pincel.setColor(Color.YELLOW);
            pincel.setStrokeWidth(15);
            canvas.drawPoint(300,300,pincel);
            pincel.setColor(Color.GRAY);
            pincel.setStrokeWidth(15);
            canvas.drawPoint(300,100,pincel);
            pincel.setColor(Color.GREEN);
            pincel.setStrokeWidth(15);
            canvas.drawPoint(0,300,pincel);
            pincel.setColor(Color.BLUE);
            pincel.setStrokeWidth(5);
            canvas.drawPoint(150,150,pincel);



        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.canvas, menu);
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
