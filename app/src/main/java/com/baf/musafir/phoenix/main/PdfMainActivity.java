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
//    private TextView ptv_1;
//    private TextView ptv_2;
//    private TextView ptv_3;
//    private TextView ptv_4;
//    private TextView ptv_5;
//    private TextView ptv_6;
//    private TextView ptv_7;
//    private TextView ptv_8;
//    private TextView ptv_9;
//    private TextView ptv_10;
//    private TextView ptv_11;
//    private TextView ptv_12;
//    private TextView ptv_13;
//    private TextView ptv_14;
//    private TextView ptv_15;
//    private TextView ptv_16;
//    private TextView ptv_17;
//    private TextView ptv_18;
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
        intent.putExtra("pdfName", "SOPCH1.pdf");
        intent.putExtra("head","SOP");
        startActivity(intent);
    }

    public void PDF2(View v) {

        Intent intent = new Intent(this, PdfActivity.class);
        intent.putExtra("pdfName", "SOPCH2.pdf");
        intent.putExtra("head","SOP");
        startActivity(intent);
    }

    public void PDF3(View v) {
        Intent intent = new Intent(this, PdfActivity.class);
        intent.putExtra("pdfName", "SOPCH3.pdf");
        intent.putExtra("head","SOP");
        startActivity(intent);
    }

    public void PDF4(View v) {
        Intent intent = new Intent(this, PdfActivity.class);
        intent.putExtra("pdfName", "SOPCH4.pdf");
        intent.putExtra("head","SOP");
        startActivity(intent);
    }




    public void PDF5(View v) {
        Intent intent = new Intent(this, PdfActivity.class);
        intent.putExtra("pdfName", "SOPCH5.pdf");
        intent.putExtra("head","SOP");
        startActivity(intent);
    }

    public void PDF6(View v) {
        Intent intent = new Intent(this, PdfActivity.class);
        intent.putExtra("pdfName", "SOPCH6.pdf");
        intent.putExtra("head","SOP");
        startActivity(intent);
    }
    public void PDF7(View v) {
        Intent intent = new Intent(this, PdfActivity.class);
        intent.putExtra("pdfName", "SOPCH7.pdf");
        intent.putExtra("head","SOP");
        startActivity(intent);
    }

    public void PDF8(View v) {
        Intent intent = new Intent(this, PdfActivity.class);
        intent.putExtra("pdfName", "SOPCH8.pdf");
        intent.putExtra("head","SOP");
        startActivity(intent);
    }
    public void PDF9(View v) {
        Intent intent = new Intent(this, PdfActivity.class);
        intent.putExtra("pdfName", "SOPCH9.pdf");
        intent.putExtra("head","SOP");
        startActivity(intent);
    }

    public void PDF10(View v) {
        Intent intent = new Intent(this, PdfActivity.class);
        intent.putExtra("pdfName", "SOPCH10.pdf");
        intent.putExtra("head","SOP");
        startActivity(intent);
    }
    public void PDF11(View v) {
        Intent intent = new Intent(this, PdfActivity.class);
        intent.putExtra("pdfName", "SOPCH11.pdf");
        intent.putExtra("head","SOP");
        startActivity(intent);
    }

    public void PDF12(View v) {
        Intent intent = new Intent(this, PdfActivity.class);
        intent.putExtra("pdfName", "SOPCH12.pdf");
        intent.putExtra("head","SOP");
        startActivity(intent);
    }
    public void PDF13(View v) {
        Intent intent = new Intent(this, PdfActivity.class);
        intent.putExtra("pdfName", "SOPCH13.pdf");
        intent.putExtra("head","SOP");
        startActivity(intent);
    }

    public void PDF14(View v) {
        Intent intent = new Intent(this, PdfActivity.class);
        intent.putExtra("pdfName", "SOPCH14.pdf");
        intent.putExtra("head","SOP");
        startActivity(intent);
    }
    public void PDF15(View v) {
        Intent intent = new Intent(this, PdfActivity.class);
        intent.putExtra("pdfName", "SOPCH15.pdf");
        intent.putExtra("head","SOP");
        startActivity(intent);
    }

    public void PDF16(View v) {
        Intent intent = new Intent(this, PdfActivity.class);
        intent.putExtra("pdfName", "SOPCH16.pdf");
        intent.putExtra("head","SOP");
        startActivity(intent);
    }

    public void PDF17(View v) {
        Intent intent = new Intent(this, PdfActivity.class);
        intent.putExtra("pdfName", "SOPCH17.pdf");
        intent.putExtra("head","SOP");
        startActivity(intent);
    }
    public void PDF18(View v) {
        Intent intent = new Intent(this, PdfActivity.class);
        intent.putExtra("pdfName", "SOPCH18.pdf");
        intent.putExtra("head","SOP");
        startActivity(intent);
    }

    public void BACK(View v) {
        this.finish();

    }
    private void changeFont() {
        topbar = (TextView) findViewById(R.id.topbar);
//        ptv_1=(TextView)findViewById(R.id.ptv_1);
//        ptv_2=(TextView)findViewById(R.id.ptv_2);
//        ptv_3=(TextView)findViewById(R.id.ptv_3);
//        ptv_4=(TextView)findViewById(R.id.ptv_4);
//        ptv_5=(TextView)findViewById(R.id.ptv_5);
//        ptv_6=(TextView)findViewById(R.id.ptv_6);
//        ptv_7=(TextView)findViewById(R.id.ptv_7);
//        ptv_8=(TextView)findViewById(R.id.ptv_8);
//        ptv_9=(TextView)findViewById(R.id.ptv_9);
//        ptv_10=(TextView)findViewById(R.id.ptv_10);
//        ptv_11=(TextView)findViewById(R.id.ptv_11);
//        ptv_12=(TextView)findViewById(R.id.ptv_12);
//        ptv_13=(TextView)findViewById(R.id.ptv_13);
//        ptv_14=(TextView)findViewById(R.id.ptv_14);
//        ptv_15=(TextView)findViewById(R.id.ptv_15);
//        ptv_16=(TextView)findViewById(R.id.ptv_16);
//        ptv_17=(TextView)findViewById(R.id.ptv_17);
//        ptv_18=(TextView)findViewById(R.id.ptv_18);
        excercise_scroll=(TextView)findViewById(R.id.excercise_scroll);

        topbar.setTypeface(tf);
//        ptv_1.setTypeface(tf);
//        ptv_2.setTypeface(tf);
//        ptv_3.setTypeface(tf);
//        ptv_4.setTypeface(tf);
//        ptv_5.setTypeface(tf);
//        ptv_6.setTypeface(tf);
//        ptv_7.setTypeface(tf);
//        ptv_8.setTypeface(tf);
//        ptv_9.setTypeface(tf);
//        ptv_10.setTypeface(tf);
//        ptv_11.setTypeface(tf);
//        ptv_12.setTypeface(tf);
//        ptv_13.setTypeface(tf);
//        ptv_14.setTypeface(tf);
//        ptv_15.setTypeface(tf);
//        ptv_16.setTypeface(tf);
//        ptv_17.setTypeface(tf);
//        ptv_18.setTypeface(tf);
        excercise_scroll.setTypeface(tf);




    }

}
