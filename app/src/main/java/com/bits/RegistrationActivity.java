package com.bits;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import com.amazonaws.mobile.auth.core.IdentityManager;
import com.amazonaws.mobile.config.AWSConfiguration;

public class RegistrationActivity extends AppCompatActivity {
    DynamoDBMapper dynamoDBMapper;
    String name, location, bio;
    double age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(AWSMobileClient.getInstance().getCredentialsProvider());
        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                .build();

        Context context = getApplicationContext();
        AWSConfiguration awsConfiguration = new AWSConfiguration(context);

        IdentityManager identityManager = new IdentityManager(context, awsConfiguration);

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

        // then, go to another activity
    }


}
