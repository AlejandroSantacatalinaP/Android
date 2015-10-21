package com.example.jmalberola.popupmenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends Activity {

    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Creamos un Popupmenu y le asociamos el xml
                PopupMenu popup = new PopupMenu(MainActivity.this, b);
                popup.getMenuInflater().inflate(R.menu.actions, popup.getMenu());

                //Registramos el Popup
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.item1:
                                Toast.makeText(getApplicationContext(), "Primera opción pulsada", Toast.LENGTH_LONG).show();
                                return true;
                            case R.id.item2:
                                Toast.makeText(getApplicationContext(), "Segunda opción pulsada", Toast.LENGTH_LONG).show();
                                return true;
                            default:
                                return false;
                        }
                    }
                });

                popup.show();//showing popup menu
            }
        });//closing the setOnClickListener method
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
