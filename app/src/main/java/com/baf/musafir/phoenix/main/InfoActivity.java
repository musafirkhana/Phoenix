package com.baf.musafir.phoenix.main;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.baf.musafir.phoenix.R;


public class InfoActivity extends Activity {
    private Context mContext;
    private TextView info_topbar;
    private TextView cp_head;
    private TextView cp_txt;
    private TextView version_head;
    private TextView version_txt;
    private TextView thanks_head;
    private TextView thanks_txt;
    private TextView concept_head;
    private TextView concept_txt;
    private TextView design_head;
    private TextView design_txt;
    private TextView terms_txt;
    private TextView terms_head;
    private TextView about_head;
    private TextView about_txt;



    Typeface tf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_info);
        mContext = this;
        changeFont();
    }

    public void BACK(View v) {
        this.finish();

    }

    private void changeFont() {
        tf = Typeface.createFromAsset(mContext.getAssets(),
                "fonts/megatron.ttf");

        info_topbar = (TextView) findViewById(R.id.info_topbar);
        cp_head = (TextView) findViewById(R.id.cp_head);
        cp_txt = (TextView) findViewById(R.id.cp_txt);
        version_head = (TextView) findViewById(R.id.version_head);
        version_txt = (TextView) findViewById(R.id.version_txt);
        thanks_head = (TextView) findViewById(R.id.thanks_head);
        thanks_txt = (TextView) findViewById(R.id.thanks_txt);
        concept_head = (TextView) findViewById(R.id.concept_head);
        concept_txt = (TextView) findViewById(R.id.concept_txt);
        design_head = (TextView) findViewById(R.id.design_head);
        design_txt = (TextView) findViewById(R.id.design_txt);
        terms_txt = (TextView) findViewById(R.id.terms_txt);
        terms_head = (TextView) findViewById(R.id.terms_head);
        about_head = (TextView) findViewById(R.id.about_head);
        about_txt = (TextView) findViewById(R.id.about_txt);

        info_topbar.setTypeface(tf);
        cp_head.setTypeface(tf);
        cp_txt.setTypeface(tf);
        version_head.setTypeface(tf);
        version_txt.setTypeface(tf);
        thanks_head.setTypeface(tf);
        thanks_txt.setTypeface(tf);
        concept_head.setTypeface(tf);
        concept_txt.setTypeface(tf);
        design_head.setTypeface(tf);
        design_txt.setTypeface(tf);
        terms_txt.setTypeface(tf);
        terms_head.setTypeface(tf);
        about_head.setTypeface(tf);
        about_txt.setTypeface(tf);


    }
}
