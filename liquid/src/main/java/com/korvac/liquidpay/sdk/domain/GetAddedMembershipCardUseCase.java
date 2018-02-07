package com.korvac.liquidpay.sdk.domain;

import com.korvac.liquidpay.sdk.data.response.ResponseGetAddedMembershipCard;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.LiquidPayConfig;
import com.korvac.liquidpay.sdk.main.LiquidPayPrefs;

import javax.inject.Inject;

/**
 * Created by Aldi on 11/15/2017.
 */

public class GetAddedMembershipCardUseCase extends AuthenticatedUseCase<GetAddedMembershipCardUseCase.Params> {

    private LiquidPayPrefs liquidPayPrefs;
    private Repository repository;

    @Inject
    public GetAddedMembershipCardUseCase(LiquidPayPrefs liquidPayPrefs, Repository repository) {
        super(liquidPayPrefs, repository);
        this.liquidPayPrefs = liquidPayPrefs;
        this.repository = repository;
    }

    @Override
    void callRealAPI(LiquidPayConfig config, String accessToken, final Params params) {
        repository.getAddedMembershipCard(config, accessToken, params.offset, params.limit, new LiquidPayCallback<ResponseGetAddedMembershipCard>() {
            @Override
            public void onSuccess(ResponseGetAddedMembershipCard response) {
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

    public static class Params{

        public final int offset, limit;
        public final LiquidPayCallback<ResponseGetAddedMembershipCard> callback;

        public Params(int offset, int limit, LiquidPayCallback<ResponseGetAddedMembershipCard> callback) {
            this.offset = offset;
            this.limit = limit;
            this.callback = callback;
        }
    }
}
