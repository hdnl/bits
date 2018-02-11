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

public class CreateRequest extends AppCompatActivity {
    DynamoDBMapper dynamoDBMapper;
    String userId, title, description;
    double transactionId, requestAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_request);
    }

    private void submitRequest(double rAmount, String t, String d){
        requestAmount = rAmount;
        title = t;
        description = d;

        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(AWSMobileClient.getInstance().getCredentialsProvider());
        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                .build();

        final Context context = getApplicationContext();
        AWSConfiguration awsConfiguration = new AWSConfiguration(context);

        final IdentityManager identityManager = new IdentityManager(context, awsConfiguration);

        final RequestsDO request = new RequestsDO();
        request.setUserId(identityManager.getCachedUserID());
        request.setTransactionId(determineTransactionId());


    }

    private double determineTransactionId(){
        return -1;
    }
}
