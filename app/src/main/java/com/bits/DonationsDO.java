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

@DynamoDBTable(tableName = "bits-mobilehub-1731603336-donations")

public class DonationsDO {
    private String _userId;
    private Double _amount;
    private Double _amountReceivedBack;
    private Double _remainingTime;
    private String _transactionId;
    private String _userGiftee;

    @DynamoDBHashKey(attributeName = "userId")
    @DynamoDBAttribute(attributeName = "userId")
    public String getUserId() {
        return _userId;
    }

    public void setUserId(final String _userId) {
        this._userId = _userId;
    }
    @DynamoDBAttribute(attributeName = "amount")
    public Double getAmount() {
        return _amount;
    }

    public void setAmount(final Double _amount) {
        this._amount = _amount;
    }
    @DynamoDBAttribute(attributeName = "amountReceivedBack")
    public Double getAmountReceivedBack() {
        return _amountReceivedBack;
    }

    public void setAmountReceivedBack(final Double _amountReceivedBack) {
        this._amountReceivedBack = _amountReceivedBack;
    }
    @DynamoDBAttribute(attributeName = "remainingTime")
    public Double getRemainingTime() {
        return _remainingTime;
    }

    public void setRemainingTime(final Double _remainingTime) {
        this._remainingTime = _remainingTime;
    }
    @DynamoDBAttribute(attributeName = "transactionId")
    public String getTransactionId() {
        return _transactionId;
    }

    public void setTransactionId(final String _transactionId) {
        this._transactionId = _transactionId;
    }
    @DynamoDBAttribute(attributeName = "userGiftee")
    public String getUserGiftee() {
        return _userGiftee;
    }

    public void setUserGiftee(final String _userGiftee) {
        this._userGiftee = _userGiftee;
    }

}
