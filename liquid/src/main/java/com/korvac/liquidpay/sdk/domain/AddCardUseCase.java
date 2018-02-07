package com.korvac.liquidpay.sdk.domain;

import com.korvac.liquidpay.sdk.data.response.BaseCard;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.LiquidPayConfig;
import com.korvac.liquidpay.sdk.main.LiquidPayPrefs;

import java.util.Date;

import javax.inject.Inject;

/**
 * Created by sohail on 6/16/2017.
 */


public class AddCardUseCase extends AuthenticatedUseCase<AddCardUseCase.Params> {

    LiquidPayPrefs liquidPayPrefs;
    Repository repository;


    @Inject
    public AddCardUseCase(LiquidPayPrefs liquidPayPrefs, Repository repository) {
        super(liquidPayPrefs, repository);
        this.liquidPayPrefs = liquidPayPrefs;
        this.repository = repository;
    }

    @Override
    void callRealAPI(LiquidPayConfig config, String accessToken, final Params params) {

        this.repository.addCard(config, accessToken, params.cardNumber, params.cardHolderName, params.expiryDate, params.cvv, new LiquidPayCallback<BaseCard>() {
            @Override
            public void onSuccess(BaseCard response) {
                params.callback.onSuccess(response);
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                params.callback.onError(throwable, errorMessage);
            }
        });

//        this.repository.addCardLoop(config, accessToken, params.cardNumber, params.cardHolderName, params.expiryDate, params.cvv, new LiquidPayCallback<BaseCard>() {
//            @Override
//            public void onSuccess(BaseCard response) {
//                params.callback.onSuccess(response);
//            }
//
//            @Override
//            public void onError(Throwable throwable, String errorMessage) {
//                params.callback.onError(throwable, errorMessage);
//            }
//        });
    }

    @Override
    void error(Throwable throwable, String message, Params params) {
        params.callback.onError(throwable, message);
    }

    public static final class Params {

        public final String cardNumber;
        public final String cardHolderName;
        public final String cvv;
        public final Date expiryDate;
        public final LiquidPayCallback<BaseCard> callback;

        public Params(String cardNumber, String cardHolderName, String cvv, Date expiryDate, LiquidPayCallback<BaseCard> callback) {
            this.cardNumber = cardNumber;
            this.cardHolderName = cardHolderName;
            this.cvv = cvv;
            this.expiryDate = expiryDate;
            this.callback = callback;
        }
    }
}
