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


public class ApproachlistMainActivity extends Activity {
    Typeface tf;
    private Context mContext;
    private TextView topbar;
    private TextView excercise_scroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_apprlist_main);
        mContext = this;
        tf = Typeface.createFromAsset(mContext.getAssets(),
                "fonts/megatron.ttf");
        changeFont();
    }

    public void APP1(View v) {

        Intent intent = new Intent(this, PdfActivity.class);
        intent.putExtra("pdfName", "VGBR.pdf");
        intent.putExtra("head","VGBR");
        startActivity(intent);
    }

    public void APP2(View v) {

        Intent intent = new Intent(this, PdfActivity.class);
        intent.putExtra("pdfName", "VGCB.pdf");
        intent.putExtra("head","VGCB");
        startActivity(intent);
    }

    public void APP3(View v) {
        Intent intent = new Intent(this, PdfActivity.class);
        intent.putExtra("pdfName", "VGEG.pdf");
        intent.putExtra("head","VGEG");
        startActivity(intent);
    }

    public void APP4(View v) {
        Intent intent = new Intent(this, PdfActivity.class);
        intent.putExtra("pdfName", "VGHS.pdf");
        intent.putExtra("head","VGHS");
        startActivity(intent);
    }
    public void APP5(View v) {

        Intent intent = new Intent(this, PdfActivity.class);
        intent.putExtra("pdfName", "VGIS.pdf");
        intent.putExtra("head","VGIS");
        startActivity(intent);
    }

    public void APP6(View v) {

        Intent intent = new Intent(this, PdfActivity.class);
        intent.putExtra("pdfName", "VGJR.pdf");
        intent.putExtra("head","VGJR");
        startActivity(intent);
    }

    public void APP7(View v) {
        Intent intent = new Intent(this, PdfActivity.class);
        intent.putExtra("pdfName", "VGRJ.pdf");
        intent.putExtra("head","VGRJ");
        startActivity(intent);
    }

    public void APP8(View v) {
        Intent intent = new Intent(this, PdfActivity.class);
        intent.putExtra("pdfName", "VGSD.pdf");
        intent.putExtra("head","VGSD");
        startActivity(intent);
    }
    public void APP9(View v) {
        Intent intent = new Intent(this, PdfActivity.class);
        intent.putExtra("pdfName", "VGSY.pdf");
        intent.putExtra("head","VGSY");
        startActivity(intent);
    }

    public void BACK(View v) {
        this.finish();

    }
    private void changeFont() {
        topbar = (TextView) findViewById(R.id.topbar);
        excercise_scroll=(TextView)findViewById(R.id.excercise_scroll);

        topbar.setTypeface(tf);
        excercise_scroll.setTypeface(tf);




    }

}
