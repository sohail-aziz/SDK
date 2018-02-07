package com.korvac.liquidpay.sdk.main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.korvac.liquidpay.sdk.data.R;
import com.korvac.liquidpay.sdk.data.response.ResponseLogin;
import com.korvac.liquidpay.sdk.domain.GetTokenUseCase;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity {


    private static final String OAUTH_URL = "PUT_OAUTH_URL_HERE";

    public static final String KEY_SCOPE_ARRAY = "LoginActivity.KEY_SCOPE_ARRAY";
    public static final String STATUS_SUCCESS = "success";
    public static final String STATUS_ERROR = "error";
    public static final String STATUS_CANCELLED = "cancelled";

    private LiquidPayConfig config;
    private WebView webView;
    private FrameLayout frameLayoutProgress;


    @Inject
    GetTokenUseCase getTokenUseCase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //inject dagger deps
        injectDependencies();

        //setup toolbar
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //init views
        frameLayoutProgress = (FrameLayout) findViewById(R.id.frame_layout_progress);
        webView = (WebView) findViewById(R.id.web_view_login);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(myWebViewClient);


        //build URI
        Uri.Builder builder = Uri.parse(OAUTH_URL).buildUpon();
        builder.appendQueryParameter("response_type", config.responseType);
        builder.appendQueryParameter("client_id", config.apiKey);
        builder.appendQueryParameter("redirect_uri", config.redirectUri);

        String[] providedScopes = getIntent().getStringArrayExtra(KEY_SCOPE_ARRAY);
        String concatScopes = LiquidPayScope.getConcatScopes(providedScopes);

        builder.appendQueryParameter("scope", concatScopes);
        builder.appendQueryParameter("state", config.state);


        //load URI
        Log.d(LoginActivity.class.getSimpleName(), "oAuth Url=" + builder.build().toString());
        webView.loadUrl(builder.build().toString());

    }


    private void injectDependencies() {
        DaggerWrapper.getComponent(this).inject(this);
        config = LiquidPay.getInstance().getConfig();
    }

    private void showProgress() {
        frameLayoutProgress.setVisibility(View.VISIBLE);
    }

    private void hideProgress() {
        frameLayoutProgress.setVisibility(View.GONE);
    }

    private final WebViewClient myWebViewClient = new WebViewClient() {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            showProgress();
            Log.d(LoginActivity.class.getSimpleName(), "onPageFinished: url=" + url);

            final Uri uri = Uri.parse(url);

            String scheme = uri.getScheme();
            String authority = uri.getAuthority();
            String host = uri.getHost();

            String path = uri.getPath();
            String status = uri.getQueryParameter("status");
            String state = uri.getQueryParameter("state");

            Log.d(LoginActivity.class.getSimpleName(), "onPageFinished: scheme=" + scheme);
            Log.d(LoginActivity.class.getSimpleName(), "onPageFinished: authority=" + authority);
            Log.d(LoginActivity.class.getSimpleName(), "onPageFinished: host=" + host);

            Log.d(LoginActivity.class.getSimpleName(), "onPageFinished: path=" + path);
            Log.d(LoginActivity.class.getSimpleName(), "onPageFinished: status=" + status);
            Log.d(LoginActivity.class.getSimpleName(), "onPageFinished: state=" + state);


            if (url.contains(config.redirectUri) && url.contains(state) && status != null) {

                switch (status) {
                    case STATUS_SUCCESS:
                        handleSuccess(uri);
                        break;
                    case STATUS_ERROR:
                        handleError(uri);
                        break;
                    case STATUS_CANCELLED:
                        handleCancelled(uri);
                        break;
                    default:
                        break;

                }
            }
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            hideProgress();
        }
    };

    private void closeActivity() {
        Intent result = new Intent();
        result.putExtra("code", getString(R.string.error_code_cancelled));
        result.putExtra("error", getString(R.string.error_message_cancelled));
        setResult(RESULT_CANCELED, result);
        finish();
    }

    @Override
    public void onBackPressed() {

        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            Intent result = new Intent();
            result.putExtra("code", getString(R.string.error_code_cancelled));
            result.putExtra("error", getString(R.string.error_message_cancelled));
            setResult(RESULT_CANCELED, result);
            super.onBackPressed();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (android.R.id.home == item.getItemId()) {
            onBackPressed();
            return true;
        }

        if (R.id.menu_item_close == item.getItemId()) {
            closeActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void handleError(Uri uri) {

        String errorCode = uri.getQueryParameter("error_code");
        String errorDescription = uri.getQueryParameter("error_description");

        Intent result = new Intent();
        result.putExtra("code", errorCode);
        result.putExtra("error", errorDescription);
        setResult(RESULT_CANCELED, result);
        finish();


    }

    private void handleCancelled(Uri uri) {

        String status = uri.getQueryParameter("status");
        String state = uri.getQueryParameter("state");

        Intent result = new Intent();
        result.putExtra("code", getString(R.string.error_code_cancelled));
        result.putExtra("error", getString(R.string.error_message_cancelled));
        setResult(RESULT_CANCELED, result);
        finish();
    }

    private void handleSuccess(Uri uri) {
        Log.d(LoginActivity.class.getSimpleName(), "handleSuccess: uri=" + uri.toString());

        showProgress();

        String status = uri.getQueryParameter("status");
        String code = uri.getQueryParameter("code");
        String expiresIn = uri.getQueryParameter("expires_in");
        String scope = uri.getQueryParameter("scope");
        String state = uri.getQueryParameter("state");

        String redirectURI = uri.getScheme() + uri.getHost() + uri.getPath();
        Log.d(LoginActivity.class.getSimpleName(), "handleSuccess: constructed redirectURI=" + redirectURI);
        Log.d(LoginActivity.class.getSimpleName(), "handleSuccess: status=" + status + " code=" + code + " expiresIn=" + expiresIn + " scope=" + scope + " state=" + state);


        final String redirectUri = config.redirectUri;
        getTokenUseCase.execute(config, new GetTokenUseCase.Params(code, redirectUri, new LiquidPayCallback<ResponseLogin>() {
            @Override
            public void onSuccess(ResponseLogin response) {
                Log.d(LoginActivity.class.getSimpleName(), "handleSuccess: onSuccess: response=" + response.toString());
                hideProgress();

                Intent result = new Intent();
                result.putExtra("response", response);
                setResult(RESULT_OK, result);
                finish();
            }

            @Override
            public void onError(Throwable throwable, String errorMessage) {
                Log.d(LoginActivity.class.getSimpleName(), "handleSuccess: onError: error=" + errorMessage);
                hideProgress();

                Intent result = new Intent();
                result.putExtra("code", getString(R.string.error_code_api));
                result.putExtra("error", errorMessage);
                setResult(RESULT_CANCELED, result);
                finish();
            }
        }));


    }
}
