package com.baf.musafir.phoenix.quiz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.baf.musafir.phoenix.R;
import com.baf.musafir.phoenix.databse.DataBaseUtility;
import com.baf.musafir.phoenix.holder.CoordinateVector;
import com.baf.musafir.phoenix.main.MapActivity;
import com.baf.musafir.phoenix.main.PdfMainActivity;
import com.baf.musafir.phoenix.model.CoordinateModel;
import com.baf.musafir.phoenix.util.StringUtility;
import com.baf.musafir.phoenix.util.ToastUtil;
import com.skyfishjy.library.RippleBackground;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import timber.log.Timber;

public class QuizlistActivity extends Activity implements View.OnClickListener {


    private Context mContext;
    private LinearLayout normal_li;
    private LinearLayout moderate_li;
    private LinearLayout critical_li;
    private TextView topbar;
    private TextView txt_easy;
    private TextView txt_moderate;
    private TextView txt_intermediate;
    private TextView txt_hard;

    Typeface tf;
    private ToastUtil toastUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_quiz_list);
        mContext = this;
        toastUtil = new ToastUtil(this);

        tf = Typeface.createFromAsset(mContext.getAssets(),
                "fonts/megatron.ttf");
        initUI();
        changeFont();
        final RippleBackground rippleBackground=(RippleBackground)findViewById(R.id.content);
        ImageView imageView=(ImageView)findViewById(R.id.centerImage);
        rippleBackground.startRippleAnimation();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rippleBackground.startRippleAnimation();
            }
        });



    }

    private void initUI() {
        normal_li = (LinearLayout) findViewById(R.id.normal_li);
        moderate_li = (LinearLayout) findViewById(R.id.moderate_li);
        critical_li = (LinearLayout) findViewById(R.id.critical_li);
        topbar = (TextView) findViewById(R.id.topbar);
        normal_li.setOnClickListener(this);
        moderate_li.setOnClickListener(this);
        critical_li.setOnClickListener(this);


    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.normal_li:

                Intent intent = new Intent(this, QuizMainActivity.class);
                startActivity(intent);
                this.finish();
                break;
            case R.id.moderate_li:
                toastUtil.appSuccessMsg(mContext, "Under construction");

                break;
            case R.id.critical_li:
                toastUtil.appSuccessMsg(mContext, "Under construction");
                break;
        }
    }

    private void changeFont() {
        txt_easy = (TextView) findViewById(R.id.txt_easy);
        txt_moderate = (TextView) findViewById(R.id.txt_moderate);
        txt_intermediate = (TextView) findViewById(R.id.txt_intermediate);
        txt_hard = (TextView) findViewById(R.id.txt_hard);
        txt_easy.setTypeface(tf);
        txt_moderate.setTypeface(tf);
        txt_intermediate.setTypeface(tf);
        txt_hard.setTypeface(tf);
        topbar.setTypeface(tf);


    }
    public void BACK(View v) {
      finish();
    }

}