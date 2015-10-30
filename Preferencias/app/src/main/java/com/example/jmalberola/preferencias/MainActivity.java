package com.example.jmalberola.preferencias;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    public static final String PREFS = "My preferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Creamos o recuperamos el objeto de preferencias compartidas
                SharedPreferences mySharedPreferences = getSharedPreferences(PREFS, Activity.MODE_PRIVATE);

                // Obtenemos un editor para modificar las preferencias
                SharedPreferences.Editor editor = mySharedPreferences.edit();

                // Guardamos nuevos valores
                editor.putBoolean("isTrue",true);
                editor.putFloat("lastFloat", (float) 1.5);
                editor.putInt("wholeNumber",12);
                editor.putLong("aNumber",2);
                editor.putString("textEntryValue","mensaje");

                // Guardamos los cambios
                editor.commit();

            }
        });

        final Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Recuperamos el objeto de preferencias compartidas
                SharedPreferences mySharedPreferences = getSharedPreferences(PREFS, Activity.MODE_PRIVATE);

                // Recuperamos los valores guardados
                boolean isTrue = mySharedPreferences.getBoolean("isTrue", false);
                String text = mySharedPreferences.getString("textEntryValue","");

                Toast.makeText(MainActivity.this, "He recuperado " + isTrue + " y " + text, Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
