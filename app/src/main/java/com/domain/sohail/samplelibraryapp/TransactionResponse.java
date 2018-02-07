package com.domain.sohail.samplelibraryapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sohail on 5/15/2017.
 */

public class TransactionResponse {


    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("transaction_id")
    @Expose
    private String transactionId;
    @SerializedName("topup_amount")
    @Expose
    private Float topupAmount;
    @SerializedName("payment_src")
    @Expose
    private String paymentSrc;
    @SerializedName("payment_amount")
    @Expose
    private Float paymentAmount;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Float getTopupAmount() {
        return topupAmount;
    }

    public void setTopupAmount(Float topupAmount) {
        this.topupAmount = topupAmount;
    }

    public String getPaymentSrc() {
        return paymentSrc;
    }

    public void setPaymentSrc(String paymentSrc) {
        this.paymentSrc = paymentSrc;
    }

    public Float getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Float paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

}




