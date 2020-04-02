package com.baf.musafir.phoenix.main;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.baf.musafir.phoenix.R;


public class WeatherActivity extends Activity {

    private Context mContex;
    private TextView header;
    private WebView webView;
    ProgressDialog dialog;
    Typeface tf;

    /**
     * Called when the activity is first created.
     */
    @SuppressWarnings("static-access")
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.weather_webview);
        mContex = this;
        tf = Typeface.createFromAsset(mContex.getAssets(),
                "fonts/megatron.ttf");
        header = (TextView) findViewById(R.id.header);
        webView = (WebView) findViewById(R.id.webview);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true); // allow pinch to zooom
        settings.setDisplayZoomControls(false);
        webView.setWebViewClient(new WebViewClient() {

            // This method will be triggered when the Page Started Loading

            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                dialog = ProgressDialog.show(WeatherActivity.this, null,
                        "PLease wait .....");
                dialog.setCancelable(true);
                super.onPageStarted(view, url, favicon);
            }

            // This method will be triggered when the Page loading is completed

            public void onPageFinished(WebView view, String url) {
                dialog.dismiss();
                super.onPageFinished(view, url);
            }

            // This method will be triggered when error page appear

            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                dialog.dismiss();
                // You can redirect to your own page instead getting the default
                // error page
                Toast.makeText(WeatherActivity.this,
                        "The Requested Page Does Not Exist", Toast.LENGTH_LONG).show();
                webView.loadUrl("Musafir Ali");
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });
        settings.setDefaultTextEncodingName("utf-8");

			webView.loadUrl("https://www.mtrwf.com/");
//        webView.loadUrl("https://met.baf.mil.bd/");

//        webView.loadUrl("https://ccntservice.airbus.com/apps/cockpits/a380/?_ga=2.242988802.1499998843.1585851919-533739161.1585851919");

        changeFont();

    }


    public void BACK(View v) {
        this.finish();
    }
    public void RELOAD(View v) {
        webView.reload();
    }
    public void mtrRadar(View v) {
        Intent intent = new Intent(this, RadarViewActivity.class);
        intent.putExtra("imgUrl","https://wx.baf.mil.bd/METBSR/images/omar/RadarSingle/mtr.jpg");
        startActivity(intent);
    }
    public void zhrRadar(View v) {
        Intent intent = new Intent(this, RadarViewActivity.class);
        intent.putExtra("imgUrl","http://wx.baf.mil.bd/METBSR/images/omar/ZHR/zhrradar.jpg");
        startActivity(intent);
    }

    private void changeFont() {

        header.setTypeface(tf);


    }

}
