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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import timber.log.Timber;

public class QuizlistActivity extends Activity implements View.OnClickListener {


    private Context mContext;
    private Button btn_normal;
    private Button btn_modetrate;
    private Button btn_critical;
    private TextView topbar;

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


    }

    private void initUI() {
        btn_normal = (Button) findViewById(R.id.btn_normal);
        btn_modetrate = (Button) findViewById(R.id.btn_modetrate);
        btn_critical = (Button) findViewById(R.id.btn_critical);
        topbar = (TextView) findViewById(R.id.topbar);
        btn_normal.setOnClickListener(this);
        btn_modetrate.setOnClickListener(this);
        btn_critical.setOnClickListener(this);


    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_normal:

                Intent intent = new Intent(this, QuizMainActivity.class);
                startActivity(intent);
                this.finish();
                break;
            case R.id.btn_modetrate:
                toastUtil.appSuccessMsg(mContext, "Under construction");

                break;
            case R.id.btn_critical:
                toastUtil.appSuccessMsg(mContext, "Under construction");
                break;
        }
    }

    private void changeFont() {

        btn_normal.setTypeface(tf);
        btn_modetrate.setTypeface(tf);
        btn_critical.setTypeface(tf);
        topbar.setTypeface(tf);


    }
    public void BACK(View v) {
      finish();
    }

}