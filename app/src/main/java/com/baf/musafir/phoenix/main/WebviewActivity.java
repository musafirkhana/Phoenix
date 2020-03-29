package com.baf.musafir.phoenix.main;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.baf.musafir.phoenix.BuildConfig;
import com.baf.musafir.phoenix.R;
import com.baf.musafir.phoenix.databse.AssetDatabaseOpenHelper;
import com.baf.musafir.phoenix.util.ToastUtil;

import timber.log.Timber;


public class WebviewActivity extends Activity {

    private Context mContex;


    String exercise_no="";
    String msn_profile="";
    String idUnique="";

    private TextView header;
    private WebView webView;
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
        setContentView(R.layout.webview);
        mContex = this;
        toastUtil=new ToastUtil(this);
        tf = Typeface.createFromAsset(mContex.getAssets(),
                "fonts/megatron.ttf");

        exercise_no=getIntent().getStringExtra("exercise_no");
        msn_profile=getIntent().getStringExtra("msn_profile");
        idUnique=getIntent().getStringExtra("id");
        header = (TextView) findViewById(R.id.header);
        header.setText(exercise_no);
        Log.i("SSSSS    ", msn_profile);
        webView = (WebView) findViewById(R.id.webview);
        WebSettings settings = webView.getSettings();

        webView.setWebViewClient(new WebViewClient() {

            // This method will be triggered when the Page Started Loading

            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                dialog = ProgressDialog.show(WebviewActivity.this, null,
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
                Toast.makeText(WebviewActivity.this,
                        "The Requested Page Does Not Exist", Toast.LENGTH_LONG).show();
                webView.loadUrl("Musafir Ali");
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });
        settings.setDefaultTextEncodingName("utf-8");
        webView.loadDataWithBaseURL(null, msn_profile, "text/html", "UTF-8", null);

//			webView.loadUrl("file:///android_asset/speach.htm");
//			mPlayer.start();
        changeFont();

    }


    public void BACK(View v) {
        this.finish();
    }

    public void SHARE(View v) {
        updateData();
    }

    private void updateData() {


        Integer idInteger=Integer.parseInt(idUnique);
        AssetDatabaseOpenHelper databaseOpenHelper = new AssetDatabaseOpenHelper(mContex);
        SQLiteDatabase db = databaseOpenHelper.openDatabase();
        db.beginTransaction();

        String strSQL = "UPDATE mission_profile SET msn_status = '1' WHERE id = " +
                idInteger +
                "; ";
        db.execSQL(strSQL);
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        Timber.i("Data Insert Duccessfully");
        toastUtil.appSuccessMsg(mContex,"Update Sucessfull");

    }
    private void changeFont() {

        header.setTypeface(tf);


    }

}
