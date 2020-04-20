package com.baf.musafir.phoenix.main;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baf.musafir.phoenix.R;
import com.baf.musafir.phoenix.adapter.MainSliderAdapter;
import com.baf.musafir.phoenix.databse.DataBaseUtility;
import com.baf.musafir.phoenix.quiz.QuizMainActivity;
import com.baf.musafir.phoenix.quiz.QuizlistActivity;
import com.baf.musafir.phoenix.util.AppConstant;
import com.baf.musafir.phoenix.util.PicassoImageLoadingService;
import com.baf.musafir.phoenix.util.ToastUtil;


import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;

import ss.com.bannerslider.Slider;
import ss.com.bannerslider.event.OnSlideClickListener;

public class MainActivity extends Activity {
    private Context mContext;
    private DataBaseUtility dataBaseUtility;
    HashMap<String, Integer> HashMapForLocalRes;
    private Slider slider;
    private LinearLayout main_menu_li;
    private static final int GPS_ENABLE_REQUEST = 0x1001;
    private AlertDialog mGPSDialog;
    final private int REQUEST_CODE_ASK_PERMISSIONS_STORGE = 2000;
    private List<String> permissions = new ArrayList<String>();
    private ToastUtil toastUtil;

    /*******************
     * For Font Change
     * @param savedInstanceState
     */
//    private TextView tvm_1;
//    private TextView tvm_2;
//    private TextView tvm_3;
//    private TextView tvm_4;
//    private TextView tvm_5;
//    private TextView tvm_6;
//    private TextView tvm_7;
//    private TextView tvm_8;
//    private TextView tvm_9;
    private TextView tv_topbar;
    Typeface tf ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        mContext = this;
        toastUtil = new ToastUtil(this);
        dataBaseUtility = new DataBaseUtility();
        dataBaseUtility.getCoordinateData(mContext);
        Slider.init(new PicassoImageLoadingService(this));
        initUI();
        changeFont();
    }


    private void initUI() {
        // main_menu_li = (LinearLayout) findViewById(R.id.main_menu_li);
        slider = findViewById(R.id.slider);

        //Call this method to add images from local drawable folder .
        //AddImageUrlFormLocalRes();

        slider.postDelayed(new Runnable() {
            @Override
            public void run() {
                slider.setAdapter(new MainSliderAdapter());
                slider.setSelectedSlide(0);
            }
        }, 1500);
        slider.setOnSlideClickListener(new OnSlideClickListener() {
            @Override
            public void onSlideClick(int position) {
                //Do what you want
                toastUtil.appSuccessMsg(mContext,"dsadsadasd");
            }
        });

    }





    /**********************Main Menu**********************/
    public void SYLLABUS(View v) {
        Intent intent = new Intent(this, SyllabusActivity.class);
        startActivity(intent);

    }

    public void SOP(View v) {
        Intent intent = new Intent(this, PdfMainActivity.class);
        startActivity(intent);
    }

    public void FOB(View v) {
        toastUtil.appSuccessMsg(mContext,"Under Construction");
    }

    public void CKLIST(View v) {
        Intent intent = new Intent(this, CklistMainActivity.class);
        startActivity(intent);
    }

    public void COCKPIT(View v) {
        Intent intent = new Intent(this, PilotActivity.class);
        startActivity(intent);
//        toastUtil.appSuccessMsg(mContext,"Under Construction");
    }

    public void MAP(View v) {
        Intent intent = new Intent(this, MaplistviewActivity.class);
        startActivity(intent);
    }

    public void FLGHRS(View v) {
        Intent intent = new Intent(this, FlghourActivity.class);
        startActivity(intent);
    }

    public void COORDINATES(View v) {
        Intent intent = new Intent(this, CoordinateActivity.class);
        startActivity(intent);
    }

    public void OTHERS(View v) {
        Intent intent = new Intent(this, QuizlistActivity.class);
        startActivity(intent);
    }

    /********************************More Menu****************/
    public void MENU(View v) {
        getPermission();
    }

    public void CONTACT(View v) {
        //dataBaseUtility.getProfileList(mContext);
        Intent intent = new Intent(this, PdfActivity.class);
        intent.putExtra("pdfName","ROUTES.pdf");
        intent.putExtra("head","Routes Of Bangladesh");
        startActivity(intent);
    }

    public void WEATHER(View v) {
        Intent intent = new Intent(this, WeatherActivity.class);
        startActivity(intent);
    }


    public void DICTIONARY(View v) {
        Intent intent = new Intent(this, GenerelAbbribiationActivity.class);
        startActivity(intent);

    }
    public void Approach(View v) {
        Intent intent = new Intent(this, ApproachlistMainActivity.class);
        startActivity(intent);

    }


    private void getPermission(){
        if (Build.VERSION.SDK_INT > 22) {
            String storagePermission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
            String storageReadPermission = Manifest.permission.READ_EXTERNAL_STORAGE;
            int hasstoragePermission = checkSelfPermission(storagePermission);
            if (hasstoragePermission != PackageManager.PERMISSION_GRANTED) {
                permissions.add(storagePermission);
                permissions.add(storageReadPermission);
            }
            if (!permissions.isEmpty()) {
                String[] params = permissions.toArray(new String[permissions.size()]);
                requestPermissions(params, REQUEST_CODE_ASK_PERMISSIONS_STORGE);
            } else {
                Intent intent = new Intent(this, MenuActivity.class);
                startActivity(intent);
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS_STORGE:
                if (grantResults.length > 0) {
                    boolean writePermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean readPermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if (writePermission && readPermission) {
                        Intent intent = new Intent(this, MenuActivity.class);
                        startActivity(intent);
                    } else {
                    }
                }
                break;

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    private void changeFont(){
        tf = Typeface.createFromAsset(mContext.getAssets(),
                "fonts/megatron.ttf");
//        tvm_1=(TextView)findViewById(R.id.tvm_1);
//        tvm_2=(TextView)findViewById(R.id.tvm_2);
//        tvm_3=(TextView)findViewById(R.id.tvm_3);
//        tvm_4=(TextView)findViewById(R.id.tvm_4);
//        tvm_5=(TextView)findViewById(R.id.tvm_5);
//        tvm_6=(TextView)findViewById(R.id.tvm_6);
//        tvm_7=(TextView)findViewById(R.id.tvm_7);
//        tvm_8=(TextView)findViewById(R.id.tvm_8);
//        tvm_9=(TextView)findViewById(R.id.tvm_9);
        tv_topbar=(TextView)findViewById(R.id.tv_topbar);
//        tvm_1.setTypeface(tf);
//        tvm_2.setTypeface(tf);
//        tvm_3.setTypeface(tf);
//        tvm_4.setTypeface(tf);
//        tvm_5.setTypeface(tf);
//        tvm_6.setTypeface(tf);
//        tvm_7.setTypeface(tf);
//        tvm_8.setTypeface(tf);
//        tvm_9.setTypeface(tf);
        tv_topbar.setTypeface(tf);
    }
}
