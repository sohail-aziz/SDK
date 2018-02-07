package com.korvac.liquidpay.sdk.domain;

import com.korvac.liquidpay.sdk.data.response.ResponseLinkCardToVirtualAccount;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.LiquidPayConfig;
import com.korvac.liquidpay.sdk.main.LiquidPayPrefs;

import javax.inject.Inject;

/**
 * Created by Aldi on 11/14/2017.
 */

public class LinkCardToVirtualAccountUseCase extends AuthenticatedUseCase<LinkCardToVirtualAccountUseCase.Params> {

    private LiquidPayPrefs liquidPayPrefs;
    private Repository repository;

    @Inject
    public LinkCardToVirtualAccountUseCase(LiquidPayPrefs liquidPayPrefs, Repository repository) {
        super(liquidPayPrefs, repository);
        this.liquidPayPrefs = liquidPayPrefs;
        this.repository = repository;
    }

    @Override
    void callRealAPI(LiquidPayConfig config, String accessToken, final Params params) {
        repository.linkCardToVirtualAccount(config, accessToken, params.virtualAccountId, params.paymentCardId, new LiquidPayCallback<ResponseLinkCardToVirtualAccount>() {
            @Override
            public void onSuccess(ResponseLinkCardToVirtualAccount response) {
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

        public final String virtualAccountId;
        public final String paymentCardId;
        public final LiquidPayCallback<ResponseLinkCardToVirtualAccount> callback;

        public Params(String virtualAccountId, String paymentCardId, LiquidPayCallback<ResponseLinkCardToVirtualAccount> callback) {
            this.virtualAccountId = virtualAccountId;
            this.paymentCardId = paymentCardId;
            this.callback = callback;
        }
    }

}
