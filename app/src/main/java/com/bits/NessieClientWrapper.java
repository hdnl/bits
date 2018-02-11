/*
package com.bits;


import com.reimaginebanking.api.nessieandroidsdk.NessieError;
import com.reimaginebanking.api.nessieandroidsdk.NessieResultsListener;
import com.reimaginebanking.api.nessieandroidsdk.models.Customer;
import com.reimaginebanking.api.nessieandroidsdk.requestclients.NessieClient;

import java.util.List;

*/
/**
 * NessieClientWrapper
 *
 * NessieClient class grants access to Nessie's API endpoints, this wrapper is intended to
 * simplify and protect access to the client.
 *
 *//*

public class NessieClientWrapper {
    private NessieClient client;

    */
/**
     * Constructor
     *//*

    public NessieClientWrapper() {
        this.client = NessieClient.getInstance("7107cab1a4111154c6e289ad419371c4");
    }

    public NessieClient getNessieClient(){
        return client;
    }

    // for testing purposes
   */
/*public static void main(String [ ] args){
        NessieClientWrapper wrapper = new NessieClientWrapper();

        wrapper.getNessieClient().CUSTOMER.getCustomers(new NessieResultsListener() {
            @Override
            public void onSuccess(Object result) {
                List<Customer> customers = (List<Customer>) result;
                // do something with the list of customers here
                System.out.println(customers);
            }

            @Override
            public void onFailure(NessieError error) {
                // handle error
            }
        });
    }*//*

}
*/
