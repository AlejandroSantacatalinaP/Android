package com.example.jmalberola.test;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by jmalberola on 27/01/2016.
 */
public class MainTests extends ActivityInstrumentationTestCase2<MainActivity> {

    MainActivity activity;

    public MainTests() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception{
        activity = getActivity();
    }

    public void testPreconditions() {
        assertNotNull(activity);
    }

    public void testApp() {

        final EditText edit = (EditText) activity.findViewById(R.id.editText);

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                edit.requestFocus();
            }
        });

        getInstrumentation().sendStringSync("Hola");

        Button boton = (Button) activity.findViewById(R.id.button);

        TouchUtils.clickView(this, boton);

        TextView mensaje = (TextView) activity.findViewById(R.id.textView2);

        String actualText = mensaje.getText().toString();
        assertEquals("Hola", actualText);


    }

}
