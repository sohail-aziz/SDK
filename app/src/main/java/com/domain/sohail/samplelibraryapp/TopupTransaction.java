package com.domain.sohail.samplelibraryapp;

/**
 * Created by sohail on 5/15/2017.
 */

public class TopupTransaction extends BaseTransaction {

    public String transaction_id;
    public Double topup_amount;

    public String getTransaction_id() {
        return transaction_id;
    }

    public Double getTopup_amount() {
        return topup_amount;
    }

    @Override
    public String toString() {
        return "TopupTransaction{" +
                "type="+type+'\''+
                "transaction_id='" + transaction_id + '\'' +
                ", topup_amount=" + topup_amount +
                '}';
    }
}
