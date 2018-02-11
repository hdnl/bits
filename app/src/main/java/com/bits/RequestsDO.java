package com.bits;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.List;
import java.util.Map;
import java.util.Set;

@DynamoDBTable(tableName = "bits-mobilehub-1731603336-requests")

public class RequestsDO {
    private String _userId;
    private Double _transactionId;
    private Double _amountPaidback;
    private Double _amountReceived;
    private Double _amountRequested;
    private String _description;
    private String _title;

    @DynamoDBHashKey(attributeName = "userId")
    @DynamoDBAttribute(attributeName = "userId")
    public String getUserId() {
        return _userId;
    }

    public void setUserId(final String _userId) {
        this._userId = _userId;
    }
    @DynamoDBRangeKey(attributeName = "transactionId")
    @DynamoDBAttribute(attributeName = "transactionId")
    public Double getTransactionId() {
        return _transactionId;
    }

    public void setTransactionId(final Double _transactionId) {
        this._transactionId = _transactionId;
    }
    @DynamoDBAttribute(attributeName = "amountPaidback")
    public Double getAmountPaidback() {
        return _amountPaidback;
    }

    public void setAmountPaidback(final Double _amountPaidback) {
        this._amountPaidback = _amountPaidback;
    }
    @DynamoDBAttribute(attributeName = "amountReceived")
    public Double getAmountReceived() {
        return _amountReceived;
    }

    public void setAmountReceived(final Double _amountReceived) {
        this._amountReceived = _amountReceived;
    }
    @DynamoDBAttribute(attributeName = "amountRequested")
    public Double getAmountRequested() {
        return _amountRequested;
    }

    public void setAmountRequested(final Double _amountRequested) {
        this._amountRequested = _amountRequested;
    }
    @DynamoDBAttribute(attributeName = "description")
    public String getDescription() {
        return _description;
    }

    public void setDescription(final String _description) {
        this._description = _description;
    }
    @DynamoDBAttribute(attributeName = "title")
    public String getTitle() {
        return _title;
    }

    public void setTitle(final String _title) {
        this._title = _title;
    }

}
