package com.example.jmalberola.contentprovider;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends Activity {

    static final String PROVIDER_NAME = "com.android.contacts";
    static final String URL = "content://" + PROVIDER_NAME + "/contacts";
    static final Uri CONTENT_URI = Uri.parse(URL);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(CONTENT_URI, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()){
            int indice_nombre = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
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
