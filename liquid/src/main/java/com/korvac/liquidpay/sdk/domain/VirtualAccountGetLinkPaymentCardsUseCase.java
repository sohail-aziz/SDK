package com.korvac.liquidpay.sdk.domain;

import com.korvac.liquidpay.sdk.data.response.ResponseVirtualAccountPaymentCards;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.LiquidPayConfig;
import com.korvac.liquidpay.sdk.main.LiquidPayPrefs;

import javax.inject.Inject;

/**
 * @author Created by doanvanthanh.it@gmail.com on 11/22/17.
 */

public class VirtualAccountGetLinkPaymentCardsUseCase
        extends AuthenticatedUseCase<VirtualAccountGetLinkPaymentCardsUseCase.Params> {

    @Inject
    public VirtualAccountGetLinkPaymentCardsUseCase(
            LiquidPayPrefs liquidPayPrefs, Repository repository) {

        super(liquidPayPrefs, repository);
    }

    @Override
    void callRealAPI(LiquidPayConfig config, String accessToken, final Params params) {
        repository.getPaymentCardsLinkToVirtualAccount(
                config, accessToken, params.virtualAccountId, new LiquidPayCallback<ResponseVirtualAccountPaymentCards>() {
                    @Override public void onSuccess(ResponseVirtualAccountPaymentCards response) {
                        params.callback.onSuccess(response);
                    }

                    @Override public void onError(Throwable throwable, String errorMessage) {
                        params.callback.onError(throwable, errorMessage);
                    }
                });
    }

    @Override
    void error(Throwable throwable, String message, Params params) {
        params.callback.onError(throwable, message);
    }

    public static class Params {
        public final String virtualAccountId;
        public final LiquidPayCallback<ResponseVirtualAccountPaymentCards> callback;

        public Params(
                String virtualAccountId, LiquidPayCallback<ResponseVirtualAccountPaymentCards> callback) {

            this.virtualAccountId = virtualAccountId;
            this.callback = callback;
        }
    }
}
