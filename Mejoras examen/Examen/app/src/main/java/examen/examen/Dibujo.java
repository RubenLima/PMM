package examen.examen;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

    public class Dibujo extends Activity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.dibujar);
            final RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
            final Button returnBtn = (Button) findViewById(R.id.button);
            View canvasView = new MyView(getBaseContext());
            relativeLayout.addView(canvasView);
            returnBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    setResult(RESULT_OK, new Intent());
                    finish();
                }
            });
        }

        public class MyView extends View {
            public MyView(Context context) {
                super(context);
            }

            @Override
            protected void onDraw(Canvas canvas) {
                Paint paint = new Paint();
                paint.setStrokeWidth(5);
                paint.setStyle(Paint.Style.FILL_AND_STROKE);
                paint.setColor(Color.BLUE);
//Circulo
                canvas.drawCircle((getWidth() / 2), (float) (getHeight() * 0.25), 25, paint);

            }
        }
    }

