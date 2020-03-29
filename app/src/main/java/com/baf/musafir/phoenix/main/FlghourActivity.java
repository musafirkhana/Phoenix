package com.baf.musafir.phoenix.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.baf.musafir.phoenix.R;
import com.baf.musafir.phoenix.adapter.FlhhourAdapter;
import com.baf.musafir.phoenix.databse.DataBaseUtility;
import com.baf.musafir.phoenix.holder.FlgHourVector;
import com.baf.musafir.phoenix.util.AppConstant;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


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
    private Button btn_all_data;
    List<String> yearlist = new ArrayList<String>();
    List<String> monthList = new ArrayList<String>();

    private FlhhourAdapter flhhourAdapter;
    DataBaseUtility dataBaseUtility;

    Typeface tf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_flghrs);
        mContext = this;
        dataBaseUtility = new DataBaseUtility();
        dataBaseUtility.getFlghourData(this);
        tf = Typeface.createFromAsset(mContext.getAssets(),
                "fonts/megatron.ttf");
        yearlist.add("2019");
        yearlist.add("2020");
        yearlist.add("2021");
        yearlist.add("2022");


        monthList.add("Jan");
        monthList.add("Feb");
        monthList.add("Mar");
        monthList.add("Apr");
        monthList.add("May");
        monthList.add("Jun");
        monthList.add("Jul");
        monthList.add("Aug");
        monthList.add("Sep");
        monthList.add("Oct");
        monthList.add("Nov");
        monthList.add("Dec");
        initUI();
        changeFont();

    }

    private void initUI() {
        hrslistview = (ListView) findViewById(R.id.hrslistview);
        fp_total = (TextView) findViewById(R.id.fp_total);
        total_tv = (TextView) findViewById(R.id.total_tv);
        sp_total = (TextView) findViewById(R.id.sp_total);
        inst_total = (TextView) findViewById(R.id.inst_total);
        inst_sim_total = (TextView) findViewById(R.id.inst_sim_total);
        btn_filter = (Button) findViewById(R.id.btn_filter);
        btn_all_data=(Button)findViewById(R.id.btn_all_data);
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

        btn_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog();
            }
        });
        btn_all_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataBaseUtility.getFlghourData(mContext);
                flhhourAdapter = new FlhhourAdapter(mContext);
                hrslistview.setAdapter(flhhourAdapter);
                flhhourAdapter.notifyDataSetChanged();
                fp_total.setText(getFirstPilotTotal());
                sp_total.setText(getSecondPilotTotal());
                inst_total.setText(getInstActTotal());
                inst_sim_total.setText(getInstSimTotal());
            }
        });
    }

    public void BACK(View v) {
        this.finish();

    }

    public void PLUS(View v) {
        this.finish();
        Intent intent = new Intent(this, AddFlghourActivity.class);
        startActivity(intent);

    }

    private String getFirstPilotTotal() {
        double result = 0;
        for (int i = 0; i < FlgHourVector.getAllFlhhourlist().size(); i++) {
            result += Double.parseDouble(FlgHourVector.getAllFlhhourlist().get(i).getFirst_pilot().replaceAll("[-+^:,]", ""));
        }
        return "" + String.format("%.2f", result);

    }

    private String getSecondPilotTotal() {
        double result = 0;
        for (int i = 0; i < FlgHourVector.getAllFlhhourlist().size(); i++) {
            result += Double.parseDouble(FlgHourVector.getAllFlhhourlist().get(i).getSecond_pilot().replaceAll("[-+^:,]", ""));
        }
        return "" + String.format("%.2f", result);

    }

    private String getInstActTotal() {
        double result = 0;
        for (int i = 0; i < FlgHourVector.getAllFlhhourlist().size(); i++) {
            result += Double.parseDouble(FlgHourVector.getAllFlhhourlist().get(i).getInstr_actual().replaceAll("[-+^:,]", ""));
        }
        return "" + String.format("%.2f", result);

    }

    private String getInstSimTotal() {
        double result = 0;
        for (int i = 0; i < FlgHourVector.getAllFlhhourlist().size(); i++) {
            result += Double.parseDouble(FlgHourVector.getAllFlhhourlist().get(i).getInst_simulator().replaceAll("[-+^:,]", ""));
        }
        return "" + String.format("%.2f", result);

    }

    private void changeFont() {
        fhtv_1 = (TextView) findViewById(R.id.fhtv_1);
        txt_1 = (TextView) findViewById(R.id.txt_1);
        txt_2 = (TextView) findViewById(R.id.txt_2);
        txt_3 = (TextView) findViewById(R.id.txt_3);
        txt_4 = (TextView) findViewById(R.id.txt_4);
        txt_5 = (TextView) findViewById(R.id.txt_5);
        txt_6 = (TextView) findViewById(R.id.txt_6);
        txt_7 = (TextView) findViewById(R.id.txt_7);
        fhtv_1.setTypeface(tf);
//        txt_1.setTypeface(tf);
//        txt_2.setTypeface(tf);
//        txt_4.setTypeface(tf);
//        txt_3.setTypeface(tf);
//        txt_5.setTypeface(tf);
//        txt_6.setTypeface(tf);
        btn_all_data.setTypeface(tf);
//        txt_7.setTypeface(tf);
//        fp_total.setTypeface(tf);
//        total_tv.setTypeface(tf);
//        inst_total.setTypeface(tf);
//        inst_sim_total.setTypeface(tf);
        btn_filter.setTypeface(tf);
//        sp_total.setTypeface(tf);

    }

    private void showCustomDialog() {
        // String downloadFileSize= FileUtils.getFileSize(downloadUrl);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        final View dialogView = LayoutInflater.from(this).inflate(R.layout.my_dialog, viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        Button btn_cancel = (Button) dialogView.findViewById(R.id.btn_cancel);
        Button btn_apply = (Button) dialogView.findViewById(R.id.btn_apply);
        TextView tv_year=(TextView)dialogView.findViewById(R.id.tv_year);
        TextView tv_month=(TextView)dialogView.findViewById(R.id.tv_month);
        TextView filter_head=(TextView)dialogView.findViewById(R.id.filter_head);
        final Spinner year_spinner = (Spinner) dialogView.findViewById(R.id.year_spinner);
        final Spinner month_spinner = (Spinner) dialogView.findViewById(R.id.month_spinner);

        btn_cancel.setTypeface(tf);
        btn_apply.setTypeface(tf);
        tv_month.setTypeface(tf);
        tv_year.setTypeface(tf);
        filter_head.setTypeface(tf);


        ArrayAdapter daynightspinner = new ArrayAdapter(this, R.layout.salutation, yearlist);
        daynightspinner.setDropDownViewResource(R.layout.salutation);
        year_spinner.setAdapter(daynightspinner);

        ArrayAdapter monthSpinner = new ArrayAdapter(this, R.layout.salutation, monthList);
        monthSpinner.setDropDownViewResource(R.layout.salutation);
        month_spinner.setAdapter(monthSpinner);

        year_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                AppConstant.YEAR=year_spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        month_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                AppConstant.MONTH=month_spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //text_download.setText(Appconstant.SURA_NAME + " " + getResources().getText(R.string.download_text));
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.dismiss();
            }
        });
        btn_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBaseUtility.getFilterFlghourData(mContext,AppConstant.YEAR,AppConstant.MONTH);
                flhhourAdapter = new FlhhourAdapter(mContext);
                hrslistview.setAdapter(flhhourAdapter);
                flhhourAdapter.notifyDataSetChanged();
                fp_total.setText(getFirstPilotTotal());
                sp_total.setText(getSecondPilotTotal());
                inst_total.setText(getInstActTotal());
                inst_sim_total.setText(getInstSimTotal());
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
}
