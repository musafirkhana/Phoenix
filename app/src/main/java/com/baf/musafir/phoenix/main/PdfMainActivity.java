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

import org.w3c.dom.Text;


public class PdfMainActivity extends Activity {
    Typeface tf;
    private Context mContext;
    private TextView topbar;
    private TextView ptv_1;
    private TextView ptv_2;
    private TextView ptv_3;
    private TextView ptv_4;
    private TextView ptv_5;
    private TextView excercise_scroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_pdf_main);
        mContext = this;
        tf = Typeface.createFromAsset(mContext.getAssets(),
                "fonts/megatron.ttf");
        changeFont();
    }

    public void PDF1(View v) {

        Intent intent = new Intent(this, PdfActivity.class);
        intent.putExtra("pdfName", "SOP1.pdf");
        startActivity(intent);
    }

    public void PDF2(View v) {

        Intent intent = new Intent(this, PdfActivity.class);
        intent.putExtra("pdfName", "SOP1.pdf");
        startActivity(intent);
    }

    public void PDF3(View v) {
        Intent intent = new Intent(this, PdfActivity.class);
        intent.putExtra("pdfName", "SOP1.pdf");
        startActivity(intent);
    }

    public void PDF4(View v) {
        Intent intent = new Intent(this, PdfActivity.class);
        intent.putExtra("pdfName", "SOP1.pdf");
        startActivity(intent);
    }

    public void PDF5(View v) {
        Intent intent = new Intent(this, PdfActivity.class);
        intent.putExtra("pdfName", "SOP1.pdf");
        startActivity(intent);
    }

    public void BACK(View v) {
        this.finish();

    }
    private void changeFont() {
        topbar = (TextView) findViewById(R.id.topbar);
        ptv_1=(TextView)findViewById(R.id.ptv_1);
        ptv_2=(TextView)findViewById(R.id.ptv_2);
        ptv_3=(TextView)findViewById(R.id.ptv_3);
        ptv_4=(TextView)findViewById(R.id.ptv_4);
        ptv_5=(TextView)findViewById(R.id.ptv_4);
        excercise_scroll=(TextView)findViewById(R.id.excercise_scroll);

        topbar.setTypeface(tf);
        ptv_1.setTypeface(tf);
        ptv_2.setTypeface(tf);
        ptv_3.setTypeface(tf);
        ptv_4.setTypeface(tf);
        ptv_5.setTypeface(tf);
        excercise_scroll.setTypeface(tf);




    }

}
