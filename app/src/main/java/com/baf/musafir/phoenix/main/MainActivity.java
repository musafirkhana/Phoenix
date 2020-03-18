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
import com.baf.musafir.phoenix.databse.DataBaseUtility;
import com.baf.musafir.phoenix.quiz.QuizMainActivity;
import com.baf.musafir.phoenix.quiz.QuizlistActivity;
import com.baf.musafir.phoenix.util.AppConstant;
import com.baf.musafir.phoenix.util.ToastUtil;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.HashMap;

public class MainActivity extends Activity implements BaseSliderView.OnSliderClickListener,
        ViewPagerEx.OnPageChangeListener {
    private Context mContext;
    private DataBaseUtility dataBaseUtility;
    HashMap<String, Integer> HashMapForLocalRes;
    SliderLayout sliderLayout;
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
        initUI();
        changeFont();
    }


    private void initUI() {
        // main_menu_li = (LinearLayout) findViewById(R.id.main_menu_li);
        sliderLayout = (SliderLayout) findViewById(R.id.slider);


        //Call this method to add images from local drawable folder .
        AddImageUrlFormLocalRes();

        //Call this method to stop automatic sliding.
        //sliderLayout.stopAutoCycle();

        for (String name : HashMapForLocalRes.keySet()) {

            TextSliderView textSliderView = new TextSliderView(MainActivity.this);

            textSliderView
                    .description(name)
                    .image(HashMapForLocalRes.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            textSliderView.bundle(new Bundle());

            textSliderView.getBundle()
                    .putString("extra", name);

            sliderLayout.addSlider(textSliderView);
        }
    }


    @Override
    protected void onStop() {

        sliderLayout.stopAutoCycle();

        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

        Toast.makeText(this, slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {

        Log.d("Slider Demo", "Page Changed: " + position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void AddImageUrlFormLocalRes() {

        HashMapForLocalRes = new HashMap<String, Integer>();
        HashMapForLocalRes.put("Banner 1", R.drawable.banner1);
        HashMapForLocalRes.put("Banner 2", R.drawable.banner2);
        HashMapForLocalRes.put("Banner 3", R.drawable.banner3);


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
//        Intent intent = new Intent(this, CockpitActivity.class);
//        startActivity(intent);
        toastUtil.appSuccessMsg(mContext,"Under Construction");
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
    public void PDF(View v) {
        Intent intent = new Intent(this, PdfMainActivity.class);
        startActivity(intent);
    }

    public void VIDEO(View v) {
        Intent intent = new Intent(this, PdfMainActivity.class);
        startActivity(intent);
    }

    public void WEB(View v) {
        Intent intent = new Intent(this, PdfMainActivity.class);
        startActivity(intent);
    }


    public void LOCATION(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.youtube.com/channel/UC7L-FsmHK9MNDZhNdEDq1LQ"));
        startActivity(intent);
    }

    public void MENU(View v) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }


    public void showGPSDiabledDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("GPS Disabled");
        builder.setMessage("Gps is disabled, in order to use the application properly you need to enable GPS of your device");
        builder.setPositiveButton("Enable GPS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivityForResult(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS), GPS_ENABLE_REQUEST);
            }
        }).setNegativeButton("No, Just Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        mGPSDialog = builder.create();
        mGPSDialog.show();
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
