package com.korvac.liquidpay.sdk.domain;

import com.korvac.liquidpay.sdk.data.response.ResponseGetStoredValueCardRequiredDetails;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.LiquidPayConfig;
import com.korvac.liquidpay.sdk.main.LiquidPayPrefs;

import javax.inject.Inject;

/**
 * Created by aldi on 6/13/2017.
 */

public class GetStoredValueCardRequiredDetailsUseCase extends AuthenticatedUseCase<GetStoredValueCardRequiredDetailsUseCase.Params>{


    private final Repository repository;
    private final LiquidPayPrefs liquidPayPrefs;

    @Inject
    public GetStoredValueCardRequiredDetailsUseCase(Repository repository, LiquidPayPrefs liquidPayPrefs) {
        super(liquidPayPrefs, repository);
        this.repository = repository;
        this.liquidPayPrefs = liquidPayPrefs;
    }

    @Override
    void callRealAPI(LiquidPayConfig config,String accessToken,final Params params) {

        repository.getStoredValueCardRequiredDetails(config, accessToken, params.id, new LiquidPayCallback<ResponseGetStoredValueCardRequiredDetails>() {
            @Override
            public void onSuccess(ResponseGetStoredValueCardRequiredDetails response) {
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
        public final String id;
        public final LiquidPayCallback<ResponseGetStoredValueCardRequiredDetails> callback;

        public Params(String id, LiquidPayCallback<ResponseGetStoredValueCardRequiredDetails> callback) {
            this.id = id;
            this.callback = callback;
        }
    }
}
