package com.example.jmalberola.preferencias_xml;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.widget.Toast;

/**
 * Created by jmalberola on 30/10/2015.
 */
public class Preferencias extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferencias);

        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(this);

        // Registramos el listener
        SharedPreferences.OnSharedPreferenceChangeListener listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
                if (key.equals("pref1")) {
                    Preference connectionPref = findPreference(key);
                    Toast.makeText(getApplicationContext(), "Cambio la preferencia 1", Toast.LENGTH_SHORT).show();
                }
                if (key.equals("pref2")) {
                    Preference connectionPref = findPreference(key);
                    Toast.makeText(getApplicationContext(), "Cambio la preferencia", Toast.LENGTH_SHORT).show();
                }
            }
        };

        prefs.registerOnSharedPreferenceChangeListener(listener);
    }

}

