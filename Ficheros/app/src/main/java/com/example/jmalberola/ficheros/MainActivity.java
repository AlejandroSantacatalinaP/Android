package com.example.jmalberola.ficheros;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String FILE_NAME = "fich.txt";

        //Creamos un fichero de salida que es privado a esta aplicación
        try {
            FileOutputStream fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            String cadenaOutput = new String("Esto es una prueba\n");
            DataOutputStream dos = new DataOutputStream(fos);
            dos.writeBytes(cadenaOutput);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Creamos un nuevo fichero de entrada
        try {
            Resources myResources = getResources();
            InputStream myFile = myResources.openRawResource(R.raw.fichero);

            //FileInputStream fin = openFileInput(FILE_NAME);
            //DataInputStream dis = new DataInputStream(fin);
            byte[] buff = new byte[1000];
            myFile.read(buff);
            //dis.read(buff);
            Toast.makeText(getApplicationContext(), "He leído: " + new String(buff), Toast.LENGTH_LONG).show();
            myFile.close();
            //fin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


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
