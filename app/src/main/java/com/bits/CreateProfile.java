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
import android.widget.EditText;
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
import com.reimaginebanking.api.nessieandroidsdk.NessieError;
import com.reimaginebanking.api.nessieandroidsdk.NessieResultsListener;
import com.reimaginebanking.api.nessieandroidsdk.constants.AccountType;
import com.reimaginebanking.api.nessieandroidsdk.models.Account;
import com.reimaginebanking.api.nessieandroidsdk.models.Customer;
import com.reimaginebanking.api.nessieandroidsdk.requestclients.NessieClient;


public class CreateProfile extends AppCompatActivity {
    DynamoDBMapper dynamoDBMapper;
    EditText name, location, bio, age;
    Button bank_button;


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
        //name = "dummy_name";
        //age = 18;
        //location = "dummy_location";
        //bio = "dummy_bio";

        name = (EditText) findViewById(R.id.EditTextName);
        age = (EditText) findViewById(R.id.EditTextAge);
        location = (EditText) findViewById(R.id.EditTextLocation);
        bio = (EditText) findViewById(R.id.EditTextBio);
        bank_button = (Button) findViewById(R.id.ButtonInitBank);

        profile.setName(name.getText().toString());
        profile.setAge(Double.parseDouble(age.getText().toString()));
        profile.setLocation(location.getText().toString());
        profile.setBio(bio.getText().toString());

        bank_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NessieClient client = NessieClientWrapper.getNessieClient();
                com.reimaginebanking.api.nessieandroidsdk.models.Address address = new com.reimaginebanking.api.nessieandroidsdk.models.Address.Builder()
                        .state("VA")
                        .city("Williamsburg")
                        .streetName("Main")
                        .streetNumber("123")
                        .zip("22043")
                        .build();

                Customer customer = new Customer.Builder()
                        .firstName(name.getText().toString())
                        .lastName("last-name")
                        .address(address)
                        .build();

                client.CUSTOMER.createCustomer(customer, new NessieResultsListener() {
                    @Override
                    public void onSuccess(Object result) {
                        Log.d("addCustomerSuccess", "addCustomer success!");
                    }

                    @Override
                    public void onFailure(NessieError error) {
                        Log.d("addCustomerFailure", "addCustomer failed!");
                        Log.d("errorCulprit", error.getCulprit().toString());
                        Log.d("errorMessage", error.getMessage());
                    }
                });

                Account account = new Account.Builder()
                        .balance(500)
                        .accountNumber("1")
                        .nickname("nickname")
                        .rewards(0)
                        .type(AccountType.CHECKING)
                        .build();

                client.ACCOUNT.createAccount(customer.getId(), account, new NessieResultsListener() {
                    @Override
                    public void onSuccess(Object result) {
                        Log.d("createAccountSuccess", "addAccount success!");
                    }

                    @Override
                    public void onFailure(NessieError error) {
                        Log.d("addAccountFailure", "addAccount failed!");
                        Log.d("errorCulprit", error.getCulprit().toString());
                        Log.d("errorMessage", error.getMessage());

                    }
                });

            }
        });

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
