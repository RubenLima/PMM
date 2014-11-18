package com.maria.menuinicial;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MenuInicial extends Activity {
private TextView lblMensaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicial);
        lblMensaje = (TextView)findViewById(R.id.lblMensaje);
        registerForContextMenu(lblMensaje);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inicial, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

          switch (item.getItemId()) {
            case R.id.MnuOpc1:
                lblMensaje.setText("Opcion 1 pulsada!");
                return true;
            case R.id.MnuOpc2:
                lblMensaje.setText("Opcion 2 pulsada!");
                return true;
            case R.id.MnuOpc3:
                lblMensaje.setText("Opcion 3 pulsada!");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu ,View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_ctx_etiqueta, menu);
    }

    public boolean onContextItemSelected(MenuItem itemMnuContex) {
        switch (itemMnuContex.getItemId()) {
            case R.id.CtxLblOpc1:
                lblMensaje.setText("Etiqueta: Opcion (submenu)1 pulsada!");
                return true;
            case R.id.CtxLblOpc2:
                lblMensaje.setText("Etiqueta: Opcion (submenu) 2 pulsada!");
                return true;
            default:
                return super.onContextItemSelected(itemMnuContex);
        }
    }

}
