package com.korvac.liquidpay.sdk.domain;

import com.korvac.liquidpay.sdk.data.response.ResponseWalletAddBrandVoucher;
import com.korvac.liquidpay.sdk.main.LiquidPayCallback;
import com.korvac.liquidpay.sdk.main.LiquidPayConfig;
import com.korvac.liquidpay.sdk.main.LiquidPayPrefs;

import javax.inject.Inject;

/**
 * @author Created by doanvanthanh.it@gmail.com on 11/22/17.
 */

public class WalletAddBrandVoucherUseCase
        extends AuthenticatedUseCase<WalletAddBrandVoucherUseCase.Params> {

    @Inject
    public WalletAddBrandVoucherUseCase(LiquidPayPrefs liquidPayPrefs, Repository repository) {
        super(liquidPayPrefs, repository);
    }

    @Override
    void callRealAPI(LiquidPayConfig config, String accessToken, final Params params) {
        repository.addBrandVoucherToWallet(config, accessToken, params.id,
                new LiquidPayCallback<ResponseWalletAddBrandVoucher>() {
                    @Override public void onSuccess(ResponseWalletAddBrandVoucher response) {
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
        public final LiquidPayCallback<ResponseWalletAddBrandVoucher> callback;

        public Params(String id, LiquidPayCallback<ResponseWalletAddBrandVoucher> callback) {
            this.id = id;
            this.callback = callback;
        }
    }
}
