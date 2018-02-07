package com.korvac.liquidpay.sdk.domain;

import com.korvac.liquidpay.sdk.data.response.ResponseGetStoredValueCardTopupOptions;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.LiquidPayConfig;
import com.korvac.liquidpay.sdk.main.LiquidPayPrefs;

import javax.inject.Inject;

/**
 * Created by aldi on 6/13/2017.
 */

public class GetStoredValueCardTopUpOptionsUseCase extends AuthenticatedUseCase<GetStoredValueCardTopUpOptionsUseCase.Params> {


    private final Repository repository;
    private final LiquidPayPrefs liquidPayPrefs;

    @Inject
    public GetStoredValueCardTopUpOptionsUseCase(Repository repository, LiquidPayPrefs liquidPayPrefs) {
        super(liquidPayPrefs, repository);
        this.repository = repository;
        this.liquidPayPrefs = liquidPayPrefs;
    }

    @Override
    void callRealAPI(LiquidPayConfig config,String accessToken, final Params params) {

        repository.getStoredValueCardTopUpOptions(config, accessToken, params.recipient, new LiquidPayCallback<ResponseGetStoredValueCardTopupOptions>() {
            @Override
            public void onSuccess(ResponseGetStoredValueCardTopupOptions response) {
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
        params.callback.onError(throwable,message);
    }

    public static final class Params{
        public final String recipient;
        public final LiquidPayCallback<ResponseGetStoredValueCardTopupOptions> callback;

        public Params(String recipient, LiquidPayCallback<ResponseGetStoredValueCardTopupOptions> callback) {
            this.recipient = recipient;
            this.callback = callback;
        }
    }
}
