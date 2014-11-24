package com.example.mati.shapedrawable;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.View;


public class EshapeDrawable extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new VistaAmedida(this));
    }

    public class VistaAmedida extends View {
        private ShapeDrawable miDrawable;

        public VistaAmedida(Context contexto) {
            super(contexto);
            int x = 10, y = 10;
            int ancho = 300;
            int alto = 50;
            miDrawable = new ShapeDrawable(new OvalShape());
            miDrawable.getPaint().setColor(000);
            miDrawable.setBounds(x, y, x + ancho, y + alto);
        }

        public void onDraw(Canvas canvas) {
            miDrawable.draw(canvas);


        }

    }
}

