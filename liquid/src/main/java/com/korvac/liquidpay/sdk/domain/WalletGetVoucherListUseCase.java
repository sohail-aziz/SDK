package com.korvac.liquidpay.sdk.domain;

import com.korvac.liquidpay.sdk.data.response.ResponseWalletVoucherList;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.LiquidPayConfig;
import com.korvac.liquidpay.sdk.main.LiquidPayPrefs;

import javax.inject.Inject;

/**
 * @author Created by doanvanthanh.it@gmail.com on 11/22/17.
 */

public class WalletGetVoucherListUseCase
        extends AuthenticatedUseCase<WalletGetVoucherListUseCase.Params> {

    @Inject
    public WalletGetVoucherListUseCase(LiquidPayPrefs liquidPayPrefs, Repository repository) {
        super(liquidPayPrefs, repository);
    }

    @Override
    void callRealAPI(LiquidPayConfig config, String accessToken, final Params params) {
        repository.getVoucherListOfWallet(
                config, accessToken, new LiquidPayCallback<ResponseWalletVoucherList>() {
                    @Override public void onSuccess(ResponseWalletVoucherList response) {
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
        public final LiquidPayCallback<ResponseWalletVoucherList> callback;

        public Params(LiquidPayCallback<ResponseWalletVoucherList> callback) {
            this.callback = callback;
        }
    }
}
