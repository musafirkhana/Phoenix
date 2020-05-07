package com.baf.musafir.phoenix.main;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.baf.musafir.phoenix.R;
import com.baf.musafir.phoenix.databse.AssetDatabaseOpenHelper;
import com.baf.musafir.phoenix.util.AppConstant;
import com.baf.musafir.phoenix.util.ToastUtil;

import org.w3c.dom.Text;

import timber.log.Timber;

import static com.baf.musafir.phoenix.util.SystemUtil.TAG;


public class CwdWebviewActivity extends Activity implements View.OnClickListener {

    private Context mContex;


    String procedure = "";
    String indicate_state = "";

    private TextView header;
    private TextView txt_indicatestate;
    private WebView webView;
    private Button btn_procedure;
    ProgressDialog dialog;
    Typeface tf;
    private ToastUtil toastUtil;

    /**
     * Called when the activity is first created.
     */
    @SuppressWarnings("static-access")
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.cwd_webview);
        mContex = this;
        toastUtil = new ToastUtil(this);
        tf = Typeface.createFromAsset(mContex.getAssets(),
                "fonts/megatron.ttf");

        procedure = getIntent().getStringExtra("procedure");
        indicate_state= getIntent().getStringExtra("indicate_state");
        header = (TextView) findViewById(R.id.header);
        txt_indicatestate=(TextView)findViewById(R.id.txt_indicatestate);
        txt_indicatestate.setText(indicate_state);
        btn_procedure = (Button) findViewById(R.id.btn_procedure);
        btn_procedure.setOnClickListener(this);


        webView = (WebView) findViewById(R.id.cwd_webview);


    }


    public void BACK(View v) {
        this.finish();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_procedure:
                webView.setVisibility(View.VISIBLE);
                webviewSetting();
                break;
        }

    }



    private void webviewSetting() {
        WebSettings settings = webView.getSettings();

        webView.setWebViewClient(new WebViewClient() {

            // This method will be triggered when the Page Started Loading
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                dialog = ProgressDialog.show(CwdWebviewActivity.this, null,
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
                Toast.makeText(CwdWebviewActivity.this,
                        "The Requested Page Does Not Exist", Toast.LENGTH_LONG).show();
                webView.loadUrl("Musafir Ali");
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });
        settings.setDefaultTextEncodingName("utf-8");
        webView.loadDataWithBaseURL(null, procedure, "text/html", "UTF-8", null);

//			webView.loadUrl("file:///android_asset/speach.htm");
//			mPlayer.start();


    }



}
