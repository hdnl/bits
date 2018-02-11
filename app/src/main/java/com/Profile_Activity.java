package com;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * Created by solivialeonvitervo on 2/10/18.
 */

public class Profile_Activity extends AppCompatActivity {

    private EditText editText, editText2 , editText3, editText4;
    private Button button;
    private ImageButton imageButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.krittikamanagoli.bits.R.layout.user_profile);

        //initialize the objects

        editText = (EditText) findViewById(com.example.krittikamanagoli.bits.R.id.editText);
        editText2 = (EditText) findViewById(com.example.krittikamanagoli.bits.R.id.editText2);
        editText3 = (EditText) findViewById(com.example.krittikamanagoli.bits.R.id.editText3);
        editText4 = (EditText) findViewById(com.example.krittikamanagoli.bits.R.id.editText4);
        button = (Button) findViewById(com.example.krittikamanagoli.bits.R.id.button);
        imageButton = (ImageButton) findViewById(com.example.krittikamanagoli.bits.R.id.imageButton);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.example.krittikamanagoli.bits.R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == com.example.krittikamanagoli.bits.R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
