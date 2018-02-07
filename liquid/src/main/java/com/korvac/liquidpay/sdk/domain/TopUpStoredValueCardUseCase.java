package com.korvac.liquidpay.sdk.domain;

import com.korvac.liquidpay.sdk.data.response.ResponseTopUpStoredValueCard;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.LiquidPayConfig;
import com.korvac.liquidpay.sdk.main.LiquidPayPrefs;

import javax.inject.Inject;

/**
 * Created by aldi on 6/14/2017.
 */

public class TopUpStoredValueCardUseCase extends AuthenticatedUseCase<TopUpStoredValueCardUseCase.Params> {

    Repository repository;
    LiquidPayPrefs liquidPayPrefs;

    @Inject
    public TopUpStoredValueCardUseCase(LiquidPayPrefs liquidPayPrefs, Repository repository) {
        super(liquidPayPrefs, repository);
        this.liquidPayPrefs = liquidPayPrefs;
        this.repository = repository;
    }

    @Override
    void callRealAPI(LiquidPayConfig config,String accessToken, final Params params) {

        repository.topUpStoredValueCard(config, accessToken, params.recipient, params.originator, params.topupAmount, new LiquidPayCallback<ResponseTopUpStoredValueCard>() {
            @Override
            public void onSuccess(ResponseTopUpStoredValueCard response) {
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

    public static final class Params{
        public String recipient;
        public String originator;
        public String topupAmount;
        public LiquidPayCallback<ResponseTopUpStoredValueCard> callback;
        public Params(String recipient, String originator, String topupAmount, LiquidPayCallback<ResponseTopUpStoredValueCard> callback) {
            this.recipient = recipient;
            this.originator = originator;
            this.topupAmount = topupAmount;
            this.callback = callback;
        }

    }
}
