package com.baf.musafir.phoenix.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.location.LocationManager;
import android.net.Uri;
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


import java.util.Dictionary;
import java.util.HashMap;

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

    private ToastUtil toastUtil;

    /*******************
     * For Font Change
     * @param savedInstanceState
     */
    private TextView tvm_1;
    private TextView tvm_2;
    private TextView tvm_3;
    private TextView tvm_4;
    private TextView tvm_5;
    private TextView tvm_6;
    private TextView tvm_7;
    private TextView tvm_8;
    private TextView tvm_9;
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




//    public void AddImageUrlFormLocalRes() {
//
//        HashMapForLocalRes = new HashMap<String, Integer>();
//        HashMapForLocalRes.put("Banner 1", R.drawable.banner_image);
//        HashMapForLocalRes.put("Banner 2", R.drawable.banner_image2);
//        HashMapForLocalRes.put("Banner 3", R.drawable.banner_image3);
//
//
//    }


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
        Intent intent = new Intent(this, CockpitActivity.class);
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
    public void INFO(View v) {
        Intent intent = new Intent(this, InfoActivity.class);
        startActivity(intent);
    }

    public void PDFVIEW(View v) {
        toastUtil.appSuccessMsg(mContext,"Under Construction");
    }

    public void WEATHER(View v) {
        Intent intent = new Intent(this, WeatherActivity.class);
        startActivity(intent);
    }


    public void DICTIONARY(View v) {
        Intent intent = new Intent(this, GenerelAbbribiationActivity.class);
        startActivity(intent);

    }





    private void changeFont(){
        tf = Typeface.createFromAsset(mContext.getAssets(),
                "fonts/megatron.ttf");
        tvm_1=(TextView)findViewById(R.id.tvm_1);
        tvm_2=(TextView)findViewById(R.id.tvm_2);
        tvm_3=(TextView)findViewById(R.id.tvm_3);
        tvm_4=(TextView)findViewById(R.id.tvm_4);
        tvm_5=(TextView)findViewById(R.id.tvm_5);
        tvm_6=(TextView)findViewById(R.id.tvm_6);
        tvm_7=(TextView)findViewById(R.id.tvm_7);
        tvm_8=(TextView)findViewById(R.id.tvm_8);
        tvm_9=(TextView)findViewById(R.id.tvm_9);
        tv_topbar=(TextView)findViewById(R.id.tv_topbar);
        tvm_1.setTypeface(tf);
        tvm_2.setTypeface(tf);
        tvm_3.setTypeface(tf);
        tvm_4.setTypeface(tf);
        tvm_5.setTypeface(tf);
        tvm_6.setTypeface(tf);
        tvm_7.setTypeface(tf);
        tvm_8.setTypeface(tf);
        tvm_9.setTypeface(tf);
        tv_topbar.setTypeface(tf);
    }
}
