package com.baf.musafir.phoenix.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.baf.musafir.phoenix.R;
import com.baf.musafir.phoenix.adapter.FlhhourAdapter;
import com.baf.musafir.phoenix.databse.DataBaseUtility;
import com.baf.musafir.phoenix.holder.FlgHourVector;

import org.w3c.dom.Text;


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

    private TextView fp_total;
    private TextView total_tv;
    private TextView sp_total;
    private TextView inst_total;
    private TextView inst_sim_total;
    private Button btn_filter;
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
        fp_total=(TextView)findViewById(R.id.fp_total);
        total_tv=(TextView)findViewById(R.id.total_tv);
        sp_total=(TextView)findViewById(R.id.sp_total);
        inst_total=(TextView)findViewById(R.id.inst_total);
        inst_sim_total=(TextView)findViewById(R.id.inst_sim_total);
        btn_filter=(Button)findViewById(R.id.btn_filter);
        flhhourAdapter = new FlhhourAdapter(this);
        hrslistview.setAdapter(flhhourAdapter);

        hrslistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //PabxListModel query = AllPabxListVector.getAllPabxlist().elementAt(position);


            }
        });
        fp_total.setText(getFirstPilotTotal());
        sp_total.setText(getSecondPilotTotal());
        inst_total.setText(getInstActTotal());
        inst_sim_total.setText(getInstSimTotal());
    }
    public void BACK(View v){
        this.finish();

    }

    public void PLUS(View v) {
        this.finish();
        Intent intent=new Intent(this, AddFlghourActivity.class);
        startActivity(intent);

    }

    private String getFirstPilotTotal(){
        double result = 0;
        for(int i = 0; i < FlgHourVector.getAllFlhhourlist().size(); i++){
            result += Double.parseDouble(FlgHourVector.getAllFlhhourlist().get(i).getFirst_pilot().replaceAll("[-+^:,]",""));
        }
        return ""+result;

    }
    private String getSecondPilotTotal(){
        double result = 0;
        for(int i = 0; i < FlgHourVector.getAllFlhhourlist().size(); i++){
            result += Double.parseDouble(FlgHourVector.getAllFlhhourlist().get(i).getSecond_pilot().replaceAll("[-+^:,]",""));
        }
        return ""+String.format("%.2f", result);

    }
    private String getInstActTotal(){
        double result = 0;
        for(int i = 0; i < FlgHourVector.getAllFlhhourlist().size(); i++){
            result += Double.parseDouble(FlgHourVector.getAllFlhhourlist().get(i).getInstr_actual().replaceAll("[-+^:,]",""));
        }
        return ""+result;

    } private String getInstSimTotal(){
        double result = 0;
        for(int i = 0; i < FlgHourVector.getAllFlhhourlist().size(); i++){
            result += Double.parseDouble(FlgHourVector.getAllFlhhourlist().get(i).getInst_simulator().replaceAll("[-+^:,]",""));
        }
        return ""+result;

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
        fhtv_1.setTypeface(tf);
        txt_1.setTypeface(tf);
        txt_2.setTypeface(tf);
        txt_4.setTypeface(tf);
        txt_3.setTypeface(tf);
        txt_5.setTypeface(tf);
        txt_6.setTypeface(tf);
        txt_7.setTypeface(tf);
        fp_total.setTypeface(tf);
        total_tv.setTypeface(tf);
        inst_total.setTypeface(tf);
        inst_sim_total.setTypeface(tf);
        btn_filter.setTypeface(tf);
        sp_total.setTypeface(tf);



    }

}
