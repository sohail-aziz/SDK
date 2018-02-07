package com.domain.sohail.samplelibraryapp;

/**
 * Created by sohail on 5/15/2017.
 */

public class PaymentTransaction extends BaseTransaction {

    public String payment_src;
    public String payment_amount;

    public String getPayment_src() {
        return payment_src;
    }

    public void setPayment_src(String payment_src) {
        this.payment_src = payment_src;
    }

    public String getPayment_amount() {
        return payment_amount;
    }

    public void setPayment_amount(String payment_amount) {
        this.payment_amount = payment_amount;
    }

    @Override
    public String toString() {
        return "PaymentTransaction{" +
                "type="+type+'\''+
                "payment_src='" + payment_src + '\'' +
                ", payment_amount='" + payment_amount + '\'' +
                '}';
    }
}
