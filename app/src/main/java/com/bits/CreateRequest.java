package com.bits;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.widget.Toast;


import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import com.amazonaws.mobile.auth.core.IdentityManager;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;

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
        userId = identityManager.getCachedUserID();

        final RequestsDO request = new RequestsDO();
        request.setUserId(userId);

        //determine transactionId
        Condition rangeKeyCondition = new Condition()
                .withComparisonOperator(ComparisonOperator.EQ)
                .withAttributeValueList(new AttributeValue().withS(userId));

        DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression()
                .withHashKeyValues(request)
                .withRangeKeyCondition("userId", rangeKeyCondition)
                .withConsistentRead(false);

        PaginatedList<RequestsDO> result = dynamoDBMapper.query(RequestsDO.class, queryExpression);

        transactionId = result.size() + 1;
        request.setTransactionId(transactionId);


        // set values from textviews here
        requestAmount = 10;
        title = "dummy_title";
        description = "dummy_description";

        request.setAmountRequested(requestAmount);
        request.setTitle(title);
        request.setDescription(description);

        // push request to db

        new Thread(new Runnable() {
            @Override
            public void run() {
                // Item saved
                dynamoDBMapper.save(request);
                Log.d("description", dynamoDBMapper.load(
                        RequestsDO.class,
                        identityManager.getCachedUserID()).getDescription());
            }
        }).start();

    }
}
