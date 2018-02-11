package com.bits;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.amazonaws.mobile.auth.core.IdentityManager;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;

public class CreateDonation extends AppCompatActivity {
    DynamoDBMapper dynamoDBMapper;
    String userId, userReceivingDonation;
    double transactionId, donationAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_donation);
        // CURRENTLY FILLER VALUES
        // set values from textviews here
        donationAmount = 10;
        userReceivingDonation = "dummy_user";

        // somehow

        submitDonation();
    }

    private void submitDonation(){
        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(AWSMobileClient.getInstance().getCredentialsProvider());
        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                .build();

        final Context context = getApplicationContext();
        AWSConfiguration awsConfiguration = new AWSConfiguration(context);

        final IdentityManager identityManager = new IdentityManager(context, awsConfiguration);
        userId = identityManager.getCachedUserID();

        final DonationsDO request = new DonationsDO();
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

    }
}
