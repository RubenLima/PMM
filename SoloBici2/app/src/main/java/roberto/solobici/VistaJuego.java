package roberto.solobici;

/**
 *
 */
import java.util.Vector;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import com.roberto.solobici.R;

public class VistaJuego extends View {
    //	COCHES	//
    private Vector<Grafico> Coches;	//Vector con los Coches
    //private Vector<Grafico> Destruyecoches;
    private int numCoches = 8;		//Numero inicial de Coches
    private int numMotos = 3;		//Fragmentos/Motos en que se dividir un Coche

    // BICI //
    private Grafico bici;
    private int giroBici;	//Incremento direccion de la bici
    private float aceleracionBici;//Aumento de velocidad en la bici
    private static final int PASO_GIRO_BICI = 5;
    private static final float PASO_ACELERACION_BICI = 0.5f;

    //RUEDA//
    private Grafico rueda;
    private static int VELOCIDAD_RUEDA = 12;
    private boolean ruedaActiva;
    private int distanciaRueda;

    // THREAD Y TIEMPO //
    //Hilo encargado de procesar el tiempo
    private HiloJuego hiloJuego;
    //Tiempo que debe transcurrir para procesar cambios (ms)
    private static int PERIODO_PROCESO = 50;
    //Momento en el que se realiza el ultimo proceso
    private long ultimoProceso = 0;


    public VistaJuego(Context contexto, AttributeSet atributos) {
        super(contexto, atributos);
        Drawable graficoBici, graficoCoche, graficoRueda;

        //Obtenemos la imagen/recurso del coche
        graficoCoche = contexto.getResources().getDrawable(R.drawable.coche);
        //Obtengo la imagen de la bici
        graficoBici = contexto.getResources().getDrawable(R.drawable.bici);
        //Obtenemos el grafico de la rueda
        graficoRueda = contexto.getResources().getDrawable(R.drawable.rueda);

        //Creamos un vector para contener todos los coches que se ven en la pantalla
        //y lo rellenamos con graficos de coches
        // con valores aleatorios para su velocidad, direccion y rotacion.
        Coches = new Vector<Grafico>();
        for (int i=0; i<numCoches; i++) {
            Grafico coche = new Grafico(this, graficoCoche);
            coche.setIncX(Math.random() * 4 - 2);
            coche.setIncY(Math.random() * 4 - 2);
            coche.setAngulo((int) (Math.random() * 360));
            coche.setRotacion((int) (Math.random() * 8 - 4));
            Coches.add(coche);
        }

        //BICI
        bici = new Grafico(this, graficoBici);
        //RUEDA
        rueda = new Grafico(this,graficoRueda);
        ruedaActiva = false;
    }

    //Al comenzar y dibujar por primera vez la pantalla del juego
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        //Dibujamos los coches en posiciones aleatorias
        for (Grafico coche: Coches) {
            do {
                coche.setPosX(Math.random()*(w-coche.getAncho()));
                coche.setPosY(Math.random()*(h-coche.getAlto()));
            } while (coche.distancia(bici) < (w+h)/5);
        }

        //Dibujo la bici
        bici.setPosX((w-bici.getAncho())/2);
        bici.setPosY((h-bici.getAlto())/2);

        hiloJuego = new HiloJuego();
        hiloJuego.start();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Dibujamos cada uno de los coches
        for (Grafico coche: Coches) {
            coche.dibujaGrafico(canvas);
        }
        //Dibujo bici
        bici.dibujaGrafico(canvas);
        //Dibujamos la rueda si lo indica la variable ruedaActiva
        if(ruedaActiva)
            rueda.dibujaGrafico(canvas);

    }

    private class HiloJuego extends Thread {
        @Override
        public void run() {
            while (true) {
                actualizaMovimiento();
            }
        }

    }


    protected synchronized void actualizaMovimiento() {
        long ahora = System.currentTimeMillis();
        // No hacemos nada si el perÃ­odo de proceso no se ha cumplido.
        if (ultimoProceso + PERIODO_PROCESO > ahora) {
            return;
        }
        // Para una ejecuciÃ³n en tiempo real calculamos retardo
        double retardo = (ahora - ultimoProceso) / PERIODO_PROCESO;
        // Actualizamos la posiciÃ³n de la bici
        bici.setAngulo((int) (bici.getAngulo() + giroBici * retardo));
        double nIncX = bici.getIncX() + aceleracionBici
                * Math.cos(Math.toRadians(bici.getAngulo())) * retardo;
        double nIncY = bici.getIncY() + aceleracionBici
                * Math.sin(Math.toRadians(bici.getAngulo())) * retardo;
        if (Grafico.distanciaE(0, 0, nIncX, nIncY) <= Grafico.getMaxVelocidad()) {
            bici.setIncX(nIncX);
            bici.setIncY(nIncY);
        }
        bici.incrementaPos();

        //Movemos los coches
        for (Grafico coche : Coches) {
            coche.incrementaPos();
        }

        //Movemos la rueda
        if(ruedaActiva){
            rueda.incrementaPos();
            distanciaRueda--;
            if (distanciaRueda<0){
                ruedaActiva = false;
            }else{
                for(int i=0; i<Coches.size();i++){
                    if (rueda.verificaColision(Coches.elementAt(i))){
                        destruyeCoche(i);
                        i=Coches.size();
                        ruedaActiva=false;
                    }
                }
            }
        }

        ultimoProceso = ahora;

    }

    private void destruyeCoche(int i){
        Coches.remove(i);
        //Destruyecoches= Coches.get(i);

        ruedaActiva = false;
    }

    private void lanzarRueda(){
        rueda.setPosX(bici.getPosX() + bici.getAncho()/2 - rueda.getAncho()/2);
        rueda.setPosY(bici.getPosY() + bici.getAlto()/2 - rueda.getAlto()/2);
        rueda.setAngulo(bici.getAngulo());
        rueda.setIncX(Math.cos(Math.toRadians(rueda.getAngulo()))+ VELOCIDAD_RUEDA);
        rueda.setIncY(Math.sin(Math.toRadians(rueda.getAngulo()))+ VELOCIDAD_RUEDA);

        distanciaRueda = (int)Math.min(this.getWidth()/Math.abs(rueda.getIncX()),
                                       this.getHeight()/Math.abs(rueda.getIncY())) -2;
        ruedaActiva = true;
    }

    /***********************************************************************************/
    // manejador de eventos de teclado
    @Override
    public boolean onKeyDown(int codigoTecla, KeyEvent evento){
        super.onKeyDown(codigoTecla, evento);
        //Procesamos la pulsacion
        boolean pulsacion=true;
        switch (codigoTecla){
            case KeyEvent.KEYCODE_DPAD_UP:
                aceleracionBici=+PASO_ACELERACION_BICI;
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                giroBici=-PASO_GIRO_BICI;
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                giroBici=+PASO_GIRO_BICI;
                break;
            case KeyEvent.KEYCODE_DPAD_CENTER:
            case KeyEvent.KEYCODE_ENTER:
                lanzarRueda();
            default:
                //No hemos pulsado nada que nos interese
                pulsacion=false;
                break;
        }
        return pulsacion;
    }
    public boolean onKeyUp(int codigoTecla, KeyEvent evento){
        super.onKeyDown(codigoTecla, evento);
        //Procesamos la pulsacion
        boolean pulsacion=true;
        switch (codigoTecla){
            case KeyEvent.KEYCODE_DPAD_UP:
                aceleracionBici=0;
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                giroBici=0;
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                giroBici=0;
                break;
            case KeyEvent.KEYCODE_DPAD_CENTER:
            case KeyEvent.KEYCODE_ENTER:
                lanzarRueda();
            default:
                //No hemos pulsado nada que nos interese
                pulsacion=false;
                break;
        }
        return pulsacion;
    }
    //Fin manejador de eventos de teclado

    // PANTALLA TACTIL//
    private float mX=0, mY=0;
    private boolean disparo=false;

    @Override
    public boolean onTouchEvent(MotionEvent evento){
        super.onTouchEvent(evento);
        //Obtenemos la posicion de la pulsacion
        float x = evento.getX();
        float y = evento.getY();

        switch (evento.getAction()){
            //Si comienza una pulsacion activamos la variable disparo
            case MotionEvent.ACTION_DOWN:
                disparo=true;
                break;
            //Comprobamos si la pulsacion es continuada con el desplazamiento
            //En caso de ser así desactivamos disparo porque se trata de un mivimiento
            //en vez de un disparo
            case MotionEvent.ACTION_MOVE:
                float dx=Math.abs(x-mX);
                float dy=Math.abs(y-mY);
                if (dy<6 && dx>6)//Un desplazamiento del dedo horizontal hace girar la bici
                {
                    giroBici = Math.round((x-mX)/2);
                    disparo = false;
                }else if (dx<6 && dy>6)//Desplazamiento vertical produce aceleracion
                {
                    aceleracionBici = Math.round((mY-y)/25);
                    disparo = false;
                }
                break;
            //Si se levanta el dedo sin haberse producido desplazamiento horizontal o vertical
            //disparo estará activado y disparamos
            case MotionEvent.ACTION_UP:
                giroBici=0;
                if(disparo){
                    lanzarRueda();
                }
                break;
        }
        mX=x; mY=y;
        return true;
    }


}

