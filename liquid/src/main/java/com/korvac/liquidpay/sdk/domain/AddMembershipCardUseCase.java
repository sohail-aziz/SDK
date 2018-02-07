package com.korvac.liquidpay.sdk.domain;

import com.korvac.liquidpay.sdk.data.response.ResponseAddMembershipCard;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.LiquidPayConfig;
import com.korvac.liquidpay.sdk.main.LiquidPayPrefs;

import javax.inject.Inject;

/**
 * Created by Aldi on 11/15/2017.
 */

public class AddMembershipCardUseCase extends AuthenticatedUseCase<AddMembershipCardUseCase.Params> {

    private LiquidPayPrefs liquidPayPrefs;
    private Repository repository;

    @Inject
    public AddMembershipCardUseCase(LiquidPayPrefs liquidPayPrefs, Repository repository) {
        super(liquidPayPrefs, repository);
        this.liquidPayPrefs = liquidPayPrefs;
        this.repository = repository;
    }

    @Override
    void callRealAPI(LiquidPayConfig config, String accessToken, final Params params) {
        repository.addMembershipCard(config, accessToken, params.membershipCardId, params.gender, params.dateOfBirth, new LiquidPayCallback<ResponseAddMembershipCard>() {
            @Override
            public void onSuccess(ResponseAddMembershipCard response) {
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

    public static class Params{

        public final String membershipCardId, gender, dateOfBirth;
        public final LiquidPayCallback<ResponseAddMembershipCard> callback;

        public Params(String membershipCardId, String gender, String dateOfBirth, LiquidPayCallback<ResponseAddMembershipCard> callback) {
            this.membershipCardId = membershipCardId;
            this.gender = gender;
            this.dateOfBirth = dateOfBirth;
            this.callback = callback;
        }
    }
}
