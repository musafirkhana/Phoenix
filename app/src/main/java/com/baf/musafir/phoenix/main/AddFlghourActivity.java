package com.baf.musafir.phoenix.main;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.speech.tts.Voice;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


import com.baf.musafir.phoenix.R;
import com.baf.musafir.phoenix.databse.AssetDatabaseOpenHelper;
import com.baf.musafir.phoenix.util.StringUtility;
import com.baf.musafir.phoenix.util.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import timber.log.Timber;

/***************************
 * copyright@ Musafir Ali
 * Bangladesh Airforce (103 ATTU)
 **********************/
public class AddFlghourActivity extends Activity implements View.OnClickListener,
        AdapterView.OnItemSelectedListener {
    private String TAG = "AddFlghourActivity";
    private Context mContext;
    private ToastUtil toastUtil;
    List<String> list = new ArrayList<String>();
    List<String> dnlist = new ArrayList<String>();
    Typeface tf ;


    /********************
     * UI Decleration
     *******************/
    private Spinner acnumber_spinner;
    private Spinner daynight_spinner;


    private EditText year_edittext;
    private EditText actype_edittext;
    private EditText firstpilot_edittext;
    private EditText secondpilot_edittext;
    private EditText flghour_edittext;
    private EditText inst_ac_edittext;
    private EditText instsimulation_edittext;


    private TextView tv_1;
    private TextView tv_2;
    private TextView tv_3;
    private TextView tv_4;
    private TextView tv_5;
    private TextView tv_6;
    private TextView tv_7;
    private TextView tv_8;
    private TextView tv_9;
    private TextView tv_10;


    private String ac_serno = "";
    private String day_hours = "";
    private String night_hour = "";
    private String d_n_flag = "";

    private String str_year = "";
    private String str_month = "";
    private String str_date = "";


    private Button btn_save;
    final Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_addflghrs);
        mContext = this;
        toastUtil = new ToastUtil(this);
        tf = Typeface.createFromAsset(mContext.getAssets(),
                "fonts/megatron.ttf");
        initUI();
        changeFont();
    }

    private void changeFont() {
        tv_1 = (TextView) findViewById(R.id.tv_1);
        tv_2 = (TextView) findViewById(R.id.tv_2);
        tv_3 = (TextView) findViewById(R.id.tv_3);
        tv_4 = (TextView) findViewById(R.id.tv_4);
        tv_5 = (TextView) findViewById(R.id.tv_5);
        tv_6 = (TextView) findViewById(R.id.tv_6);
        tv_7 = (TextView) findViewById(R.id.tv_7);
        tv_8 = (TextView) findViewById(R.id.tv_8);
        tv_9 = (TextView) findViewById(R.id.tv_9);
        tv_10 = (TextView) findViewById(R.id.tv_10);
//        tv_1.setTypeface(tf);
//        tv_2.setTypeface(tf);
//        tv_3.setTypeface(tf);
//        tv_4.setTypeface(tf);
//        tv_5.setTypeface(tf);
//        tv_6.setTypeface(tf);
//        tv_7.setTypeface(tf);
//        tv_8.setTypeface(tf);
//        tv_9.setTypeface(tf);
//        tv_10.setTypeface(tf);
        btn_save.setTypeface(tf);


    }

    private void initUI() {

        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("d MMM yyyy", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        acnumber_spinner = (Spinner) findViewById(R.id.acnumber_spinner);
        daynight_spinner = (Spinner) findViewById(R.id.daynight_spinner);
        year_edittext = (EditText) findViewById(R.id.year_edittext);
        actype_edittext = (EditText) findViewById(R.id.actype_edittext);
        firstpilot_edittext = (EditText) findViewById(R.id.firstpilot_edittext);
        secondpilot_edittext = (EditText) findViewById(R.id.secondpilot_edittext);
        flghour_edittext = (EditText) findViewById(R.id.flghour_edittext);
        inst_ac_edittext = (EditText) findViewById(R.id.inst_ac_edittext);
        instsimulation_edittext = (EditText) findViewById(R.id.instsimulation_edittext);
        btn_save = (Button) findViewById(R.id.btn_save);

        btn_save.setOnClickListener(this);

        year_edittext.setText(currentDateandTime);
        datePick();


        list.add("* SELECT AC");
        list.add("3011");
        list.add("3014");
        list.add("3015");

        dnlist.add("DAY");
        dnlist.add("NIGHT");


        ArrayAdapter dataAdapter = new ArrayAdapter(this, R.layout.salutation, list);
        dataAdapter.setDropDownViewResource(R.layout.salutation);
        acnumber_spinner.setAdapter(dataAdapter);


        ArrayAdapter daynightspinner = new ArrayAdapter(this, R.layout.salutation, dnlist);
        daynightspinner.setDropDownViewResource(R.layout.salutation);
        daynight_spinner.setAdapter(daynightspinner);

        acnumber_spinner.setOnItemSelectedListener(this);
        daynight_spinner.setOnItemSelectedListener(this);

    }

    private void datePick() {
        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        year_edittext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AddFlghourActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


    }

    private void updateLabel() {
        String myFormat = "d MMM yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        year_edittext.setText(sdf.format(myCalendar.getTime()));
        getYMD();
    }

    private void getYMD() {
        Timber.i(year_edittext.getText().toString());
        str_year = StringUtility.getYear(year_edittext.getText().toString());
        Timber.i(str_year);
        str_month = StringUtility.getMonth(year_edittext.getText().toString());
        Timber.i(str_month);
        str_date = StringUtility.getDay(year_edittext.getText().toString());

        Timber.i(str_date);


    }

    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,
                               long arg3) {
        // TODO Auto-generated method stub
        switch (arg0.getId()) {
            case R.id.acnumber_spinner:
                acnumber_spinner.clearFocus();
                ac_serno = acnumber_spinner.getSelectedItem().toString();
                toastUtil.appSuccessMsg(mContext, acnumber_spinner.getSelectedItem().toString());
                break;
            case R.id.daynight_spinner:

                toastUtil.appSuccessMsg(mContext, daynight_spinner.getSelectedItem().toString());
                getdayNighthour(daynight_spinner.getSelectedItem().toString());
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    public void HOME(View v) {
        this.finish();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_save:

                if (acnumber_spinner.getSelectedItem().toString().equalsIgnoreCase("* SELECT AC")) {
                    toastUtil.appSuccessMsg(mContext, "Please Select a base");
                } else if (year_edittext.getText().toString().equalsIgnoreCase("")) {
                    year_edittext.requestFocus();
                    year_edittext.setError(" * Required ");
                } else if (actype_edittext.getText().toString().equalsIgnoreCase("")) {
                    actype_edittext.requestFocus();
                    actype_edittext.setError(" * Required ");
                } else if (firstpilot_edittext.getText().toString().equalsIgnoreCase("")) {
                    firstpilot_edittext.requestFocus();
                    firstpilot_edittext.setError(" * Required ");
                } else if (secondpilot_edittext.getText().toString().equalsIgnoreCase("")) {
                    secondpilot_edittext.requestFocus();
                    secondpilot_edittext.setError(" * Required ");
                } else if (flghour_edittext.getText().toString().equalsIgnoreCase("")) {
                    flghour_edittext.requestFocus();
                    flghour_edittext.setError(" * Required ");
                } else if (inst_ac_edittext.getText().toString().equalsIgnoreCase("")) {
                    inst_ac_edittext.requestFocus();
                    inst_ac_edittext.setError(" * Required ");
                } else if (instsimulation_edittext.getText().toString().equalsIgnoreCase("")) {
                    instsimulation_edittext.requestFocus();
                    instsimulation_edittext.setError(" * Required ");
                } else {
                    insertData(str_year, str_month, str_date, actype_edittext.getText().toString(), ac_serno, firstpilot_edittext.getText().toString()
                            , secondpilot_edittext.getText().toString(), day_hours, night_hour,
                            inst_ac_edittext.getText().toString(), instsimulation_edittext.getText().toString(), d_n_flag);
                    this.finish();
                }


                break;
        }
    }

    /************************************
     * Insert Data into new Raw
     */
    private void insertData(String year, String month, String date, String ac_type, String ac_serno, String first_pilot, String second_pilot,
                            String day_hour, String night_hour, String instr_actual, String inst_simulator, String d_n_flag) {
        AssetDatabaseOpenHelper databaseOpenHelper = new AssetDatabaseOpenHelper(mContext);
        SQLiteDatabase db = databaseOpenHelper.openDatabase();
        db.beginTransaction();

        String strSQL = "INSERT INTO `flg_hour` (`year`, `month`, `date`, `ac_type`, `ac_serno`, `first_pilot`, `second_pilot`, `day_hour`, `night_hour`, `instr_actual`, `inst_simulator`, `remarks`, `d_n_flag`) \n" +
                "VALUES ('" + year +
                "','" +
                month +
                "','" +
                date +
                "','" +
                ac_type +
                "','" +
                ac_serno +
                "','" +
                first_pilot +
                "','" +
                second_pilot +
                "','" +
                day_hour +
                "','" +
                night_hour +
                "','" +
                instr_actual +
                "','" +
                inst_simulator +
                "','" +
                "n/a" +
                "','" + d_n_flag + "');";
        db.execSQL(strSQL);
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();

        toastUtil.appSuccessMsg(mContext, "Data Insert Duccessfully");

    }

    private void getdayNighthour(String flgType) {
        if (flgType.equalsIgnoreCase("DAY")) {
            day_hours = flghour_edittext.getText().toString();
            night_hour = "0.00";
            d_n_flag = "1";
        } else if (flgType.equalsIgnoreCase("NIGHT")) {
            night_hour = flghour_edittext.getText().toString();
            day_hours = "0.00";
            d_n_flag = "2";
        }


    }

    public void BACK(View v){
        this.finish();

    }
}
