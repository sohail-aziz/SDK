package com.korvac.liquidpay.sdk.domain;

import com.korvac.liquidpay.sdk.data.response.ResponseVoucherGiftBox;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.LiquidPayConfig;
import com.korvac.liquidpay.sdk.main.LiquidPayPrefs;

import javax.inject.Inject;

/**
 * @author Created by doanvanthanh.it@gmail.com on 11/21/17.
 */

public class GetVoucherGiftBoxListingUseCase
        extends AuthenticatedUseCase<GetVoucherGiftBoxListingUseCase.Params> {

    @Inject
    public GetVoucherGiftBoxListingUseCase(
            LiquidPayPrefs liquidPayPrefs, Repository repository) {

        super(liquidPayPrefs, repository);
    }

    @Override
    void callRealAPI(LiquidPayConfig config, String accessToken, final Params params) {
        repository.getVoucherGiftBoxListing(
                config,
                accessToken,
                new LiquidPayCallback<ResponseVoucherGiftBox>() {
                    @Override
                    public void onSuccess(ResponseVoucherGiftBox response) {
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
        public final LiquidPayCallback<ResponseVoucherGiftBox> callback;

        public Params(LiquidPayCallback<ResponseVoucherGiftBox> callback) {
            this.callback = callback;
        }
    }
}
