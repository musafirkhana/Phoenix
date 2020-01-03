package com.baf.musafir.phoenix.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.baf.musafir.phoenix.R;
import com.baf.musafir.phoenix.adapter.FlhhourAdapter;
import com.baf.musafir.phoenix.databse.DataBaseUtility;


public class FlghourActivity extends Activity {
private Context mContext;
    private ListView hrslistview;
    private TextView fhtv_1;
    private TextView txt_1;
    private TextView txt_2;
    private TextView txt_3;
    private TextView txt_4;
    private TextView txt_5;
    private TextView txt_6;
    private TextView txt_7;
    private TextView excercise_scroll;
    private FlhhourAdapter flhhourAdapter;
    DataBaseUtility dataBaseUtility;

    Typeface tf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_flghrs);
        mContext=this;
        dataBaseUtility=new DataBaseUtility();
        dataBaseUtility.getFlghourData(this);
        tf = Typeface.createFromAsset(mContext.getAssets(),
                "fonts/megatron.ttf");
        initUI();
        changeFont();

    }

    private void initUI(){
        hrslistview = (ListView) findViewById(R.id.hrslistview);
        flhhourAdapter = new FlhhourAdapter(this);
        hrslistview.setAdapter(flhhourAdapter);

        hrslistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //PabxListModel query = AllPabxListVector.getAllPabxlist().elementAt(position);


            }
        });
    }
    public void BACK(View v){
        this.finish();

    }

    public void PLUS(View v) {
        this.finish();
        Intent intent=new Intent(this, AddFlghourActivity.class);
        startActivity(intent);

    }

    private void changeFont() {
        fhtv_1 = (TextView) findViewById(R.id.fhtv_1);
        txt_1=(TextView)findViewById(R.id.txt_1);
        txt_2=(TextView)findViewById(R.id.txt_2);
        txt_3=(TextView)findViewById(R.id.txt_3);
        txt_4=(TextView)findViewById(R.id.txt_4);
        txt_5=(TextView)findViewById(R.id.txt_5);
        txt_6=(TextView)findViewById(R.id.txt_6);
        txt_7=(TextView)findViewById(R.id.txt_7);
        excercise_scroll=(TextView)findViewById(R.id.excercise_scroll);
        fhtv_1.setTypeface(tf);
        txt_1.setTypeface(tf);
        txt_2.setTypeface(tf);
        txt_4.setTypeface(tf);
        txt_3.setTypeface(tf);
        txt_5.setTypeface(tf);
        txt_6.setTypeface(tf);
        txt_7.setTypeface(tf);
        excercise_scroll.setTypeface(tf);





    }

}
