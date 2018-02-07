package com.korvac.liquidpay.sdk.domain;

import com.korvac.liquidpay.sdk.data.response.ResponseAvailableMembershipCard;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.LiquidPayConfig;
import com.korvac.liquidpay.sdk.main.LiquidPayPrefs;

import javax.inject.Inject;

/**
 * Created by Aldi on 11/15/2017.
 */

public class GetAvailableMembershipCardUseCase extends AuthenticatedUseCase<GetAvailableMembershipCardUseCase.Params> {

    private LiquidPayPrefs liquidPayPrefs;
    private Repository repository;

    @Inject
    public GetAvailableMembershipCardUseCase(LiquidPayPrefs liquidPayPrefs, Repository repository) {
        super(liquidPayPrefs, repository);
        this.liquidPayPrefs = liquidPayPrefs;
        this.repository = repository;
    }

    @Override
    void callRealAPI(LiquidPayConfig config, String accessToken, final Params params) {
        repository.getAvailableMembershipCard(config, accessToken, params.offset, params.limit, new LiquidPayCallback<ResponseAvailableMembershipCard>() {
            @Override
            public void onSuccess(ResponseAvailableMembershipCard response) {
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

        public final int offset;
        public final int limit;
        public final LiquidPayCallback<ResponseAvailableMembershipCard> callback;

        public Params(int offset, int limit, LiquidPayCallback<ResponseAvailableMembershipCard> callback) {
            this.offset = offset;
            this.limit = limit;
            this.callback = callback;
        }
    }
}
