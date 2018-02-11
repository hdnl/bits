package com.bits;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import java.util.List;
import java.util.Locale;


import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import com.amazonaws.mobile.auth.core.IdentityManager;
import com.amazonaws.mobile.config.AWSConfiguration;


public class CreateProfile extends AppCompatActivity {
    DynamoDBMapper dynamoDBMapper;
    String name, location, bio;
    double age;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_createprofile);

        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(AWSMobileClient.getInstance().getCredentialsProvider());
        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                .build();

        final Context context = getApplicationContext();
        AWSConfiguration awsConfiguration = new AWSConfiguration(context);

        final IdentityManager identityManager = new IdentityManager(context, awsConfiguration);

        final ProfileDO profile = new ProfileDO();
        profile.setUserId(identityManager.getCachedUserID());

        // checking if user has profile
        Log.d("age", "'" + profile.getAge() + "'");
        if(profile.getAge() != null) {
            // go to another activity
        }

        // set values
        name = "dummy_name";
        age = 18;
        location = "dummy_location";
        bio = "dummy_bio";

        profile.setName(name);
        profile.setAge(age);
        profile.setLocation(location);
        profile.setBio(bio);

        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.save(profile);
                Log.d("bio", dynamoDBMapper.load(
                        ProfileDO.class,
                        identityManager.getCachedUserID()).getBio());


                // Item saved
            }
        }).start();

        // then, go to another activity
    }
}
