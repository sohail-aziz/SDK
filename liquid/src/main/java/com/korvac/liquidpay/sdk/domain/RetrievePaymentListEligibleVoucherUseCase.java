package com.korvac.liquidpay.sdk.domain;

import com.korvac.liquidpay.sdk.data.response.ResponsePaymentListEligibleVoucher;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.LiquidPayConfig;
import com.korvac.liquidpay.sdk.main.LiquidPayPrefs;

import javax.inject.Inject;

/**
 * @author Created by doanvanthanh.it@gmail.com on 11/21/17.
 */

public class RetrievePaymentListEligibleVoucherUseCase
        extends AuthenticatedUseCase<RetrievePaymentListEligibleVoucherUseCase.Params> {

    @Inject
    public RetrievePaymentListEligibleVoucherUseCase(
            LiquidPayPrefs liquidPayPrefs, Repository repository) {

        super(liquidPayPrefs, repository);
    }

    @Override
    void callRealAPI(LiquidPayConfig config, String accessToken, final Params params) {
        repository.retrievePaymentListEligibleVoucher(
                config,
                accessToken,
                params.source,
                params.payee,
                params.currencyCode,
                params.amount,
                new LiquidPayCallback<ResponsePaymentListEligibleVoucher>() {
                    @Override
                    public void onSuccess(ResponsePaymentListEligibleVoucher response) {
                        params.callback.onSuccess(response);
                    }

                    @Override
                    public void onError(Throwable throwable, String errorMessage) {
                        params.callback.onError(throwable, errorMessage);
                    }
                });
    }

    @Override
    void error(Throwable throwable, String message, Params params) {
        params.callback.onError(throwable, message);
    }

    public static class Params {
        public final String source;
        public final String payee;
        public final String currencyCode;
        public final double amount;
        public final LiquidPayCallback<ResponsePaymentListEligibleVoucher> callback;

        public Params(
                String source,
                String payee,
                String currencyCode,
                double amount,
                LiquidPayCallback<ResponsePaymentListEligibleVoucher> callback) {

            this.source = source;
            this.payee = payee;
            this.currencyCode = currencyCode;
            this.amount = amount;
            this.callback = callback;
        }
    }
}
