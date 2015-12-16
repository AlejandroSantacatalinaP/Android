package com.example.jmalberola.customcontentprovider;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showAllRecords();
    }

    public void showAllRecords(){

        String URL = "content://com.example.jmalberola.customcontentprovider/discos/2";
        Uri discos = Uri.parse(URL);
        Cursor cursor = getContentResolver().query(discos, null, null, null, null);

        Toast.makeText(getApplicationContext(),
                "Cursor " + cursor.getCount(), Toast.LENGTH_LONG)
                .show();

        if (cursor != null && cursor.moveToFirst()){
            int indice_nombre = cursor.getColumnIndex(CustomContentProvider.TITLE);
            String[] values = new String[cursor.getCount()];
            do{
                //Recuperamos el nombre
                String name = cursor.getString(indice_nombre);
                //Guardamos en el Array
                values[cursor.getPosition()] = cursor.getPosition() + " (" + name +")";
            }while (cursor.moveToNext());

            ListView lv = (ListView)findViewById(R.id.listView);

            ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1, values);

            lv.setAdapter(adapter);

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
