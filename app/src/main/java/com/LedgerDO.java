package com;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.Set;

@DynamoDBTable(tableName = "bits-mobilehub-1731603336-ledger")

public class LedgerDO {
    private String _transactionId;
    private Double _requestTotal;
    private Set<String> _contributingUsers;
    private String _requestFilled;
    private String _userId;

    @DynamoDBHashKey(attributeName = "transactionId")
    @DynamoDBAttribute(attributeName = "transactionId")
    public String getTransactionId() {
        return _transactionId;
    }

    public void setTransactionId(final String _transactionId) {
        this._transactionId = _transactionId;
    }
    @DynamoDBRangeKey(attributeName = "requestTotal")
    @DynamoDBAttribute(attributeName = "requestTotal")
    public Double getRequestTotal() {
        return _requestTotal;
    }

    public void setRequestTotal(final Double _requestTotal) {
        this._requestTotal = _requestTotal;
    }
    @DynamoDBAttribute(attributeName = "contributingUsers")
    public Set<String> getContributingUsers() {
        return _contributingUsers;
    }

    public void setContributingUsers(final Set<String> _contributingUsers) {
        this._contributingUsers = _contributingUsers;
    }
    @DynamoDBAttribute(attributeName = "requestFilled")
    public String getRequestFilled() {
        return _requestFilled;
    }

    public void setRequestFilled(final String _requestFilled) {
        this._requestFilled = _requestFilled;
    }
    @DynamoDBIndexHashKey(attributeName = "userId", globalSecondaryIndexName = "userRequests")
    public String getUserId() {
        return _userId;
    }

    public void setUserId(final String _userId) {
        this._userId = _userId;
    }

}
