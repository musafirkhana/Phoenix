package com.baf.musafir.phoenix.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.baf.musafir.phoenix.R;


public class CklistMainActivity extends Activity {
    Typeface tf;
    private Context mContext;
    private TextView topbar;
    private TextView cktv_1;
    private TextView cktv_2;
    private TextView cktv_3;
    private TextView cktv_4;
    private TextView excercise_scroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_cklist_main);
        mContext = this;
        tf = Typeface.createFromAsset(mContext.getAssets(),
                "fonts/megatron.ttf");
        changeFont();
    }

    public void CK1(View v) {

        Intent intent = new Intent(this, PdfActivity.class);
        intent.putExtra("pdfName", "CK1.pdf");
        intent.putExtra("head",cktv_1.getText().toString());
        startActivity(intent);
    }

    public void CK2(View v) {

        Intent intent = new Intent(this, PdfActivity.class);
        intent.putExtra("pdfName", "CK2.pdf");
        intent.putExtra("head",cktv_2.getText().toString());
        startActivity(intent);
    }

    public void CK3(View v) {
        Intent intent = new Intent(this, PdfActivity.class);
        intent.putExtra("pdfName", "CK3.pdf");
        intent.putExtra("head",cktv_3.getText().toString());
        startActivity(intent);
    }

    public void CK4(View v) {
        Intent intent = new Intent(this, PdfActivity.class);
        intent.putExtra("pdfName", "CK4.pdf");
        intent.putExtra("head",cktv_4.getText().toString());
        startActivity(intent);
    }


    public void BACK(View v) {
        this.finish();

    }
    private void changeFont() {
        topbar = (TextView) findViewById(R.id.topbar);
        cktv_1=(TextView)findViewById(R.id.cktv_1);
        cktv_2=(TextView)findViewById(R.id.cktv_2);
        cktv_3=(TextView)findViewById(R.id.cktv_3);
        cktv_4=(TextView)findViewById(R.id.cktv_4);
        excercise_scroll=(TextView)findViewById(R.id.excercise_scroll);

        topbar.setTypeface(tf);
        cktv_1.setTypeface(tf);
        cktv_2.setTypeface(tf);
        cktv_3.setTypeface(tf);
        cktv_4.setTypeface(tf);
        excercise_scroll.setTypeface(tf);




    }

}
