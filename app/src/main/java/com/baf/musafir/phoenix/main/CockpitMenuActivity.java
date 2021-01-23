package com.baf.musafir.phoenix.main;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.baf.musafir.phoenix.R;
import com.baf.musafir.phoenix.util.ToastUtil;




public class CockpitMenuActivity extends Activity {
    private Context mContext;

    private ToastUtil toastUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_cockpit_menu);
        mContext = this;

        toastUtil = new ToastUtil(this);
    }

    public void Cock1(View v) {
        Intent intent = new Intent(this, PilotActivity.class);
        startActivity(intent);
    }

    public void Cock2(View v) {

        Intent intent = new Intent(this, PdfActivity.class);
        intent.putExtra("pdfName","MAIN_COCKPIT.pdf");
        intent.putExtra("head","Cockpit");
        startActivity(intent);
    }

    public void Cock3(View v) {

        Intent intent = new Intent(this, PdfActivity.class);
        intent.putExtra("pdfName","BOTTOM_COCKPIT.pdf");
        intent.putExtra("head","Cockpit");
        startActivity(intent);
    }
    public void BACK(View v) {
        this.finish();

    }



}