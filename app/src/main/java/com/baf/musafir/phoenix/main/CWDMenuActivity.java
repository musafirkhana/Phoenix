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
import com.baf.musafir.phoenix.quiz.QuizlistActivity;
import com.baf.musafir.phoenix.util.ToastUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class CWDMenuActivity extends Activity {
    private Context mContext;

    private ToastUtil toastUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_cwd_menu);
        mContext = this;

        toastUtil = new ToastUtil(this);
    }
    public void CK0(View v) {

        Intent intent = new Intent(this, CwdEngineActivity.class);
        startActivity(intent);
    }
    public void CK1(View v) {
        Intent intent = new Intent(this, CwdAirframeActivity.class);
        startActivity(intent);
    }

    public void CK2(View v) {

        Intent intent = new Intent(this, CwdElectroActivity.class);
        startActivity(intent);
    }


    public void BACK(View v) {
        this.finish();

    }




}