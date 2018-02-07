package com.korvac.liquidpay.sdk.domain;

import com.korvac.liquidpay.sdk.data.response.ResponseRetrieveMembershipCardRequiredInfo;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.LiquidPayConfig;
import com.korvac.liquidpay.sdk.main.LiquidPayPrefs;

import javax.inject.Inject;

/**
 * Created by Aldi on 11/15/2017.
 */

public class RetrieveMembershipCardRequiredInfoUseCase extends AuthenticatedUseCase<RetrieveMembershipCardRequiredInfoUseCase.Params> {

    private LiquidPayPrefs liquidPayPrefs;
    private Repository repository;

    @Inject
    public RetrieveMembershipCardRequiredInfoUseCase(LiquidPayPrefs liquidPayPrefs, Repository repository) {
        super(liquidPayPrefs, repository);
        this.liquidPayPrefs = liquidPayPrefs;
        this.repository = repository;
    }

    @Override
    void callRealAPI(LiquidPayConfig config, String accessToken, final Params params) {
        repository.retrieveMembershipCardRequiredInfo(config, accessToken, params.membershipCardId, new LiquidPayCallback<ResponseRetrieveMembershipCardRequiredInfo>() {
            @Override
            public void onSuccess(ResponseRetrieveMembershipCardRequiredInfo response) {
                params.callback.onSuccess(response);
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                params.callback.onError(throwable,errorMessage);
            }
        });
    }

    @Override
    void error(Throwable throwable, String message, Params params) {
        params.callback.onError(throwable, message);
    }

    public static class Params {
        public final String membershipCardId;
        public final LiquidPayCallback<ResponseRetrieveMembershipCardRequiredInfo> callback;

        public Params(String membershipCardId, LiquidPayCallback<ResponseRetrieveMembershipCardRequiredInfo> callback) {
            this.membershipCardId = membershipCardId;
            this.callback = callback;
        }
    }
}
