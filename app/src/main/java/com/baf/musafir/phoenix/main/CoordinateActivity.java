package com.baf.musafir.phoenix.main;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.baf.musafir.phoenix.R;
import com.baf.musafir.phoenix.adapter.CoordinateAdapter;
import com.baf.musafir.phoenix.databse.AssetDatabaseOpenHelper;
import com.baf.musafir.phoenix.databse.DataBaseUtility;
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
public class CoordinateActivity extends Activity {
    private String TAG = "CoordinateActivity";
    private Context mContext;
    private ToastUtil toastUtil;
    private DataBaseUtility dataBaseUtility;
    private CoordinateAdapter coordinateAdapter;

    Typeface tf;


    /********************
     * UI Decleration
     *******************/
    private Button btn_save;
    private ListView lv_coordinate;
    private TextView map_tv;

    private EditText et_latitude;
    private EditText et_longitude;
    private EditText et_place;


    private TextView tv_1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_coordinate);
        mContext = this;
        toastUtil = new ToastUtil(this);
        dataBaseUtility = new DataBaseUtility();
        dataBaseUtility.getCoordinateData(mContext);
        tf = Typeface.createFromAsset(mContext.getAssets(),
                "fonts/megatron.ttf");
        initUI();
        changeFont();
    }

    private void changeFont() {
        map_tv = (TextView) findViewById(R.id.map_tv);
        btn_save.setTypeface(tf);
        map_tv.setTypeface(tf);


    }

    private void initUI() {
        btn_save = (Button) findViewById(R.id.btn_save);
        lv_coordinate = (ListView) findViewById(R.id.lv_coordinate);
        coordinateAdapter=new CoordinateAdapter(mContext);
        lv_coordinate.setAdapter(coordinateAdapter);

        et_latitude=(EditText)findViewById(R.id.et_latitude);
        et_longitude=(EditText)findViewById(R.id.et_longitude);
        et_place=(EditText)findViewById(R.id.et_place);


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_latitude.getText().toString().equalsIgnoreCase("")) {
                    et_latitude.requestFocus();
                    et_latitude.setError(" * Required ");
                } else if (et_longitude.getText().toString().equalsIgnoreCase("")) {
                    et_longitude.requestFocus();
                    et_longitude.setError(" * Required ");
                } else if (et_place.getText().toString().equalsIgnoreCase("")) {
                    et_place.requestFocus();
                    et_place.setError(" * Required ");
                }  else {
                    insertData( et_latitude.getText().toString(), et_longitude.getText().toString()
                            , et_place.getText().toString());
                    dataBaseUtility.getCoordinateData(mContext);
                    coordinateAdapter.notifyDataSetChanged();
                }
            }
        });


    }


    /************************************
     * Insert Data into new Raw
     */
    private void insertData(String latitude, String longiude, String place) {
        AssetDatabaseOpenHelper databaseOpenHelper = new AssetDatabaseOpenHelper(mContext);
        SQLiteDatabase db = databaseOpenHelper.openDatabase();
        db.beginTransaction();

        String strSQL = "INSERT INTO `cordinate` (`latitude`, `longitude`, `place`) \n" +
                "VALUES ('" +
                latitude +
                "', '" +
                longiude +
                "', '" +
                place +
                "');";
        db.execSQL(strSQL);
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();

        toastUtil.appSuccessMsg(mContext, "Data Insert Duccessfully");

    }


    public void BACK(View v) {
        this.finish();

    }
}
