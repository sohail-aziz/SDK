package com.korvac.liquidpay.sdk.domain;

import com.korvac.liquidpay.sdk.data.response.ResponseVoucherSendToFriend;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.LiquidPayConfig;
import com.korvac.liquidpay.sdk.main.LiquidPayPrefs;

import javax.inject.Inject;

/**
 * @author Created by doanvanthanh.it@gmail.com on 11/22/17.
 */

public class VoucherSendToFriendUseCase
        extends AuthenticatedUseCase<VoucherSendToFriendUseCase.Params> {

    @Inject
    public VoucherSendToFriendUseCase(LiquidPayPrefs liquidPayPrefs, Repository repository) {
        super(liquidPayPrefs, repository);
    }

    @Override
    void callRealAPI(LiquidPayConfig config, String accessToken, final Params params) {
        repository.sendVoucherToFriend(config, accessToken, params.id, params.mobile,
                new LiquidPayCallback<ResponseVoucherSendToFriend>() {
                    @Override public void onSuccess(ResponseVoucherSendToFriend response) {
                        params.callback.onSuccess(response);
                    }

                    @Override public void onError(Throwable throwable, String errorMessage) {
                        params.callback.onError(throwable, errorMessage);
                    }
                });
    }

    @Override
    void error(Throwable throwable, String message, Params params) {
        params.callback.onError(throwable, message);
    }

    public static class Params {
        public final String id;
        public final String mobile;
        public final LiquidPayCallback<ResponseVoucherSendToFriend> callback;

        public Params(String id, String mobile, LiquidPayCallback<ResponseVoucherSendToFriend> callback) {
            this.id = id;
            this.mobile = mobile;
            this.callback = callback;
        }
    }
}
