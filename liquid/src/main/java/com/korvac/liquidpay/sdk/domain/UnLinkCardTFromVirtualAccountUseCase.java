package com.korvac.liquidpay.sdk.domain;

import com.korvac.liquidpay.sdk.data.response.ResponseLinkCardToVirtualAccount;
import com.korvac.liquidpay.sdk.data.response.ResponseUnLinkCardFromVirtualAccount;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.LiquidPayConfig;
import com.korvac.liquidpay.sdk.main.LiquidPayPrefs;

import javax.inject.Inject;

/**
 * Created by Aldi on 11/14/2017.
 */

public class UnLinkCardTFromVirtualAccountUseCase extends AuthenticatedUseCase<UnLinkCardTFromVirtualAccountUseCase.Params> {

    private LiquidPayPrefs liquidPayPrefs;
    private Repository repository;

    @Inject
    public UnLinkCardTFromVirtualAccountUseCase(LiquidPayPrefs liquidPayPrefs, Repository repository) {
        super(liquidPayPrefs, repository);
        this.liquidPayPrefs = liquidPayPrefs;
        this.repository = repository;
    }

    @Override
    void callRealAPI(LiquidPayConfig config, String accessToken, final Params params) {
        repository.unLinkCardFromVirtualAccount(config, accessToken, params.virtualAccountId, params.paymentCardId, new LiquidPayCallback<ResponseUnLinkCardFromVirtualAccount>() {
            @Override
            public void onSuccess(ResponseUnLinkCardFromVirtualAccount response) {
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
        public final LiquidPayCallback<ResponseUnLinkCardFromVirtualAccount> callback;

        public Params(String virtualAccountId, String paymentCardId, LiquidPayCallback<ResponseUnLinkCardFromVirtualAccount> callback) {
            this.virtualAccountId = virtualAccountId;
            this.paymentCardId = paymentCardId;
            this.callback = callback;
        }
    }

}
