package com.example.mati.solomibici;

import java.util.Vector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.View;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class VistaJuego extends View {
	//	COCHES	//
	private Vector<Grafico> Coches;	//Vector con los Coches
	private int numCoches = 5;		//Numero inicial de Coches
	private int numMotos = 3;		//Fragmentos/Motos en que se dividir un Coche

	// BICI //
	private Grafico bici;
	private int giroBici;	//Incremento direccion de la bici
	private float aceleracionBici;//Aumento de velocidad en la bici
	private static final int PASO_GIRO_BICI = 5;
	private static final float PASO_ACELERACION_BICI = 0.5f;
	
	// THREAD Y TIEMPO //
	//Hilo encargado de procesar el tiempo
    private HiloJuego hiloJuego;
	//Tiempo que debe transcurrir para procesar cambios (ms)
	private static int PERIODO_PROCESO = 50;
	//Momento en el que se realiza el ultimo proceso
	private long ultimoProceso = 0;



    // PANTALLA TÁCTIL //
    // Las variables mX y mY se utilizarán para recordar
    // las coordenadas del último evento.
    private float mX=0, mY=0;
    private boolean disparo=false;

    // RUEDA //
    private Grafico rueda;
    private static int VELOCIDAD_RUEDA = 12;
    private boolean ruedaActiva;
    private int distanciaRueda;

    // VARIABLES GLOBALES //
    //Controlar si la aplicación está en segundo plano
    private boolean corriendo = false;
    //Controlar si la aplicación está en pausa
    private boolean pausa;

    public Vector<Grafico> getCoches() {
        return Coches;
    }

    public void setCoches(Vector<Grafico> coches) {
        Coches = coches;
    }

    public int getNumCoches() {
        return numCoches;
    }

    public void setNumCoches(int numCoches) {
        this.numCoches = numCoches;
    }

    public Grafico getBici() {
        return bici;
    }

    public void setBici(Grafico bici) {
        this.bici = bici;
    }

    public VistaJuego(Context contexto, AttributeSet atributos) {
		super(contexto, atributos);
		Drawable graficoBici, graficoCoche, graficoRueda;
		//Obtenemos la imagen/recurso del coche
		graficoCoche = contexto.getResources().getDrawable(R.drawable.coche);

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
		
		//BICI imagen
		graficoBici = contexto.getResources().getDrawable(R.drawable.ic_launcher);
		bici = new Grafico(this, graficoBici);

        // CONTROL DEL HILO DEL JUEGO
        corriendo = true;

        // RUEDA imagen

        graficoRueda = contexto.getResources().getDrawable(R.drawable.rueda);

        rueda = new Grafico(this, graficoRueda);
        ruedaActiva = false;
	}

    public float getAceleracionBici() {
        return aceleracionBici;
    }

    public void setAceleracionBici(float aceleracionBici) {
        this.aceleracionBici = aceleracionBici;
    }

    public static float getPasoAceleracionBici() {
        return PASO_ACELERACION_BICI;
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



        bici.setPosX(Math.random() * (w - bici.getAncho()));
        bici.setPosY(Math.random()*(h-bici.getAlto()));

        //hilo que controla el juego
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
		
	}



    public int getNumMotos() {
        return numMotos;
    }

    public void setNumMotos(int numMotos) {
        this.numMotos = numMotos;
    }

    public int getGiroBici() {
        return giroBici;
    }

    public void setGiroBici(int giroBici) {
        this.giroBici = giroBici;
    }

    public HiloJuego getHiloJuego() {
        return hiloJuego;
    }

    public void setHiloJuego(HiloJuego hiloJuego) {
        this.hiloJuego = hiloJuego;
    }

    protected synchronized void actualizaMovimiento() {
        long ahora = System.currentTimeMillis();
        // No hacemos nada si el período de proceso no se ha cumplido.
        if (ultimoProceso + PERIODO_PROCESO > ahora) {
            return;
        }
        // Para una ejecución en tiempo real calculamos retardo
        double retardo = (ahora - ultimoProceso) / PERIODO_PROCESO;
        // Actualizamos la posición de la bici
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
        bici.setIncX(0);
        bici.setIncY(0);

        //Movemos los coches
        for (Grafico coche : Coches) {
            coche.incrementaPos();
        }
        ultimoProceso = ahora;

        if (ruedaActiva) { //Movemos la rueda
            rueda.incrementaPos();
            distanciaRueda--;
            if (distanciaRueda<0) {
                ruedaActiva = false;
            } else {
                for (int i=0; i<Coches.size(); i++) {
                    if (rueda.verificaColision(Coches.elementAt(i))) {
                        destruyeCoche(i);
                        i=Coches.size();
                        ruedaActiva=false;
                    }
                }
            }
        }

    }

    @Override
    public boolean onKeyDown(int codigoTecla, KeyEvent evento) {
        super.onKeyDown(codigoTecla, evento);
        //Procesamos la pulsación
        boolean pulsacion=true;
        switch (codigoTecla) {
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
                break;
            default:
                //Si estamos aquí no hemos pulsado nada que nos interese
                pulsacion=false;
                break;
        }
        return pulsacion;
    }

    @Override
    public boolean onKeyUp(int codigoTecla, KeyEvent evento) {
        super.onKeyUp(codigoTecla, evento);
        //Procesamos la pulsación
        boolean pulsacion=true;
        switch (codigoTecla) {
            case KeyEvent.KEYCODE_DPAD_UP:
                //aceleracionBici=0;
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                giroBici=0;
                break;
            default:
                //Si estamos aquí, no hemos pulsado nada que interese
                pulsacion=false;
                break;
        }
        return pulsacion;
    }

    public static int getPERIODO_PROCESO() {
        return PERIODO_PROCESO;
    }

    public static void setPERIODO_PROCESO(int PERIODO_PROCESO) {
        VistaJuego.PERIODO_PROCESO = PERIODO_PROCESO;
    }

    public long getUltimoProceso() {
        return ultimoProceso;
    }

    public void setUltimoProceso(long ultimoProceso) {
        this.ultimoProceso = ultimoProceso;
    }

    public float getmX() {
        return mX;
    }

    public void setmX(float mX) {
        this.mX = mX;
    }

    public float getmY() {
        return mY;
    }

    public void setmY(float mY) {
        this.mY = mY;
    }

    public boolean isDisparo() {
        return disparo;
    }

    public void setDisparo(boolean disparo) {
        this.disparo = disparo;
    }

    public Grafico getRueda() {
        return rueda;
    }

    public void setRueda(Grafico rueda) {
        this.rueda = rueda;
    }

    public static int getVELOCIDAD_RUEDA() {
        return VELOCIDAD_RUEDA;
    }

    public static void setVELOCIDAD_RUEDA(int VELOCIDAD_RUEDA) {
        VistaJuego.VELOCIDAD_RUEDA = VELOCIDAD_RUEDA;
    }

    public boolean isRuedaActiva() {
        return ruedaActiva;
    }

    public void setRuedaActiva(boolean ruedaActiva) {
        this.ruedaActiva = ruedaActiva;
    }

    public int getDistanciaRueda() {
        return distanciaRueda;
    }

    public void setDistanciaRueda(int distanciaRueda) {
        this.distanciaRueda = distanciaRueda;
    }

    public boolean isCorriendo() {
        return corriendo;
    }

    public boolean isPausa() {
        return pausa;
    }

    @Override
    public boolean onTouchEvent(MotionEvent evento) {

        super.onTouchEvent(evento);
        //Obtenemos la posición de la pulsación
        float x=evento.getX();
        float y=evento.getY();
        switch (evento.getAction()) {
            //Al comenzar pulsación (ACTION_DOWN) se activa la variable disparo
            case MotionEvent.ACTION_DOWN:
                disparo=true;
                break;
            //Comprobar pulsación continuada con desplazamiento hor/ver.
            //Si es asi, desactivamos disparo: se tratará de un movimiento
            //se trata de un movimiento en vez de  un disparo.
            case MotionEvent.ACTION_MOVE:
                float dx=Math.abs(x-mX);
                float dy=Math.abs(y-mY);
                //Un desplazamiento del dedo horizontal hace girar la bici.
                if (dy<6 && dx>6)
                {
                    giroBici = Math.round((x-mX)/2);
                    disparo = false;
                } else //Un desplazamiento vertical produce una aceleración.
                    if (dx<6 && dy>6)
                    {
                        aceleracionBici = Math.round((mY-y)/25);
                        disparo = false;
                    }
                break;
            //Si se levanta el dedo (ACTION_UP) sin haberse producido desplazamiento horizontal o vertical
            //disparo estará activado y lo que hacemos es disparar
            case MotionEvent.ACTION_UP:
                giroBici = 0;
                aceleracionBici = 0;
                if (disparo){
                    lanzarRueda();
                }
                break;
        }
        mX=x; mY=y;
        return true;
    }


    private void destruyeCoche(int i) {
        Coches.remove(i);
        ruedaActiva = false;
        //Activamos el sonido de explosión
        MediaPlayer miMediaPlayer =
                MediaPlayer.create(getContext(), R.raw.explosion);
        miMediaPlayer.start();
    }

    private void lanzarRueda() {
        rueda.setPosX(bici.getPosX() + bici.getAncho()/2 - rueda.getAncho()/2);
        rueda.setPosY(bici.getPosY() + bici.getAlto()/2 - rueda.getAlto()/2);
        rueda.setAngulo(bici.getAngulo());
        rueda.setIncX(Math.cos(Math.toRadians(rueda.getAngulo())) * VELOCIDAD_RUEDA);
        rueda.setIncY(Math.sin(Math.toRadians(rueda.getAngulo())) * VELOCIDAD_RUEDA);
        distanciaRueda = (int)Math.min(
                this.getWidth() / Math.abs(rueda.getIncX()),
                this.getHeight() / Math.abs(rueda.getIncY())) - 2;
        ruedaActiva = true;
    }

    public HiloJuego getHilo(){
        return hiloJuego;
    }

    public void setCorriendo(boolean corriendo) {
        this.corriendo = corriendo;
    }

    public void setPausa(boolean pausa) {
        this.pausa = pausa;
    }

    //private class HiloJuego extends Thread {
      //  @Override
      //  public void run() {
       //     while (true) {

        //        while (corriendo) {
          //          actualizaMovimiento();
          //      }
            //    }
      //  }

  //  }

    class HiloJuego extends Thread {
        private boolean pausa,corriendo;
        public synchronized void pausar() {
            pausa = true;
        }
        public synchronized void reanudar() {
            pausa = false;
            notify();
        }
        public void detener() {
            corriendo = false;
            if (pausa) reanudar();
        }
        @Override    public void run() {
            corriendo = true;
            while (corriendo) {
                actualizaMovimiento();
                synchronized (this) {
                    while (pausa) {
                        try {
                            wait();
                        } catch (Exception e) {
                        }
                    }
                }
            } // del while
        } //del metodo run
    } //de la clase HiloJuego


    public static int getPasoGiroBici() {
        return PASO_GIRO_BICI;
    }



}

