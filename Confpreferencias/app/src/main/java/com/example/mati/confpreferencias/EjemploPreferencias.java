package com.example.mati.confpreferencias;

import android.preference.PreferenceActivity;
import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.app.Activity;

public class EjemploPreferencias extends PreferenceActivity
{
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.opciones);
    }
}