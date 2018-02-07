package com.korvac.liquidpay.sdk.main;

/**
 * Defines LiquidPay Scopes for accessing different SDK features
 * <p>
 * Added in SDK v2.0
 * <p>
 * Created by sohail on 10/24/2017.
 */

public class LiquidPayScope {

    /**
     * {@value SCOPE_CONSUMER_ACCOUNT} Grants User account's functions access
     */
    public static final String SCOPE_CONSUMER_ACCOUNT = "consumer_account";
    /**
     * {@value SCOPE_CONSUMER_WALLET} Grants User wallet's functions access
     */
    public static final String SCOPE_CONSUMER_WALLET = "consumer_wallet";
    /**
     * {@value SCOPE_CONSUMER_PAYMENT} Grants Payment related functions access
     */
    public static final String SCOPE_CONSUMER_PAYMENT = "consumer_payment";
    /**
     * {@value SCOPE_CONSUMER_VOUCHER } Grants Vouchers related functions access
     */
    public static final String SCOPE_CONSUMER_VOUCHER = "consumer_voucher";
    /**
     * {@value SCOPE_CONSUMER_TRANSACTION} Grants access to consumer functions history
     */
    public static final String SCOPE_CONSUMER_TRANSACTION = "consumer_transaction";

    
    public static final String[] SCOPE_ALL = new String[]{SCOPE_CONSUMER_ACCOUNT, SCOPE_CONSUMER_WALLET, SCOPE_CONSUMER_PAYMENT, SCOPE_CONSUMER_VOUCHER, SCOPE_CONSUMER_TRANSACTION};


    public static String getConcatScopes(String[] scopes) {

        StringBuilder stringBuilder = new StringBuilder();
        for (String s : scopes) {
            stringBuilder.append(s).append(" ");
        }

        return stringBuilder.toString().trim();
    }
}
