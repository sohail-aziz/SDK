package com.korvac.liquidpay.sdk.domain;

import com.korvac.liquidpay.sdk.data.response.ResponsePaymentListEligibleMembershipCard;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.LiquidPayConfig;
import com.korvac.liquidpay.sdk.main.LiquidPayPrefs;

import javax.inject.Inject;

/**
 * @author Created by doanvanthanh.it@gmail.com on 11/21/17.
 */

public class RetrievePaymentListEligibleMembershipCardUseCase
        extends AuthenticatedUseCase<RetrievePaymentListEligibleMembershipCardUseCase.Params> {

    @Inject
    public RetrievePaymentListEligibleMembershipCardUseCase(
            LiquidPayPrefs liquidPayPrefs, Repository repository) {

        super(liquidPayPrefs, repository);
    }

    @Override
    void callRealAPI(LiquidPayConfig config, String accessToken, final Params params) {
        repository.retrievePaymentListEligibleMembershipCard(
                config,
                accessToken,
                params.source,
                params.payee,
                params.currencyCode,
                params.amount,
                params.offset,
                params.limit,
                new LiquidPayCallback<ResponsePaymentListEligibleMembershipCard>() {
                    @Override
                    public void onSuccess(ResponsePaymentListEligibleMembershipCard response) {
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
        public final int offset;
        public final int limit;
        public final LiquidPayCallback<ResponsePaymentListEligibleMembershipCard> callback;

        public Params(
                String source,
                String payee,
                String currencyCode,
                double amount,
                int offset,
                int limit,
                LiquidPayCallback<ResponsePaymentListEligibleMembershipCard> callback) {

            this.source = source;
            this.payee = payee;
            this.currencyCode = currencyCode;
            this.amount = amount;
            this.offset = offset;
            this.limit = limit;
            this.callback = callback;
        }
    }
}
