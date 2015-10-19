package com.example.jmalberola.optionsmenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.top_menu, menu);
        super.onCreateOptionsMenu(menu);
        //MenuItem menuItem1 = menu.add(Menu.NONE,Menu.FIRST,Menu.NONE,"Settings");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.help:
                Toast.makeText(getApplicationContext(), "Te ayudo",
                        Toast.LENGTH_SHORT).show();
                return true;
            case R.id.more_help:
                Toast.makeText(getApplicationContext(), "Te ayudo más",
                        Toast.LENGTH_SHORT).show();
                return true;
            case R.id.even_more_help:
                return true;
            default:
                return false;
        }
    }
}
