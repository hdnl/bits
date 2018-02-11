package com.bits;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by solivialeonvitervo on 2/11/18.
 */

public class CreateProfile extends AppCompatActivity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       getSupportActionBar().hide();
       setContentView(R.layout.activity_createprofile);
        }
}
