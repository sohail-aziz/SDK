package com.korvac.liquidpay.sdk.domain;

import com.korvac.liquidpay.sdk.data.response.ResponseDeleteCard;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.LiquidPayConfig;
import com.korvac.liquidpay.sdk.main.LiquidPayPrefs;

import javax.inject.Inject;

/**
 * Created by sohail on 6/16/2017.
 */


public class DeleteCardUseCase extends AuthenticatedUseCase<DeleteCardUseCase.Params> {

    LiquidPayPrefs liquidPayPrefs;
    Repository repository;


    @Inject
    public DeleteCardUseCase(LiquidPayPrefs liquidPayPrefs, Repository repository) {
        super(liquidPayPrefs, repository);
        this.liquidPayPrefs = liquidPayPrefs;
        this.repository = repository;
    }

    @Override
    void callRealAPI(LiquidPayConfig config, String accessToken, final Params params) {

        this.repository.deleteCard(config, accessToken, params.cardId, new LiquidPayCallback<ResponseDeleteCard>() {
            @Override
            public void onSuccess(ResponseDeleteCard response) {
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

    public static final class Params {

        public final String cardId;
        public final LiquidPayCallback<ResponseDeleteCard> callback;

        public Params(String cardId, LiquidPayCallback<ResponseDeleteCard> callback) {
            this.cardId = cardId;
            this.callback = callback;
        }
    }
}
