package com.baf.musafir.phoenix.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.baf.musafir.phoenix.R;
import com.baf.musafir.phoenix.databse.DataBaseUtility;
import com.baf.musafir.phoenix.util.AppConstant;
import com.baf.musafir.phoenix.util.ToastUtil;

public class CwdElectroActivity extends Activity {
 private Context mContext;
 ToastUtil toastUtil;
 private DataBaseUtility dataBaseUtility;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_cwd_elwctro);
        mContext=this;
        dataBaseUtility=new DataBaseUtility();
        toastUtil=new ToastUtil(this);
        initUI();

    }
    private void initUI(){

    }
    public void ELEC1(View v) {
        dataBaseUtility.getCWDPanelData(mContext,"001","elec");

        Intent intent = new Intent(this, CwdWebviewActivity.class);
        intent.putExtra("indicate_state", AppConstant.CWD_STATE);
        intent.putExtra("procedure",AppConstant.CWD_PROCEDURE);
        startActivity(intent);
    }
    public void ELEC2(View v) {
        dataBaseUtility.getCWDPanelData(mContext,"002","elec");
        Intent intent = new Intent(this, CwdWebviewActivity.class);
        intent.putExtra("indicate_state", AppConstant.CWD_STATE);
        intent.putExtra("procedure",AppConstant.CWD_PROCEDURE);
        startActivity(intent);
    }
    public void ELEC3(View v) {
        dataBaseUtility.getCWDPanelData(mContext,"003","elec");
        Intent intent = new Intent(this, CwdWebviewActivity.class);
        intent.putExtra("indicate_state", AppConstant.CWD_STATE);
        intent.putExtra("procedure",AppConstant.CWD_PROCEDURE);
        startActivity(intent);
    }

    public void ELEC4(View v) {
        dataBaseUtility.getCWDPanelData(mContext,"004","elec");
        Intent intent = new Intent(this, CwdWebviewActivity.class);
        intent.putExtra("indicate_state", AppConstant.CWD_STATE);
        intent.putExtra("procedure",AppConstant.CWD_PROCEDURE);
        startActivity(intent);
    }

    public void ELEC5(View v) {
        dataBaseUtility.getCWDPanelData(mContext,"005","elec");
        Intent intent = new Intent(this, CwdWebviewActivity.class);
        intent.putExtra("indicate_state", AppConstant.CWD_STATE);
        intent.putExtra("procedure",AppConstant.CWD_PROCEDURE);
        startActivity(intent);
    }

    public void ELEC6(View v) {
        dataBaseUtility.getCWDPanelData(mContext,"006","elec");
        Intent intent = new Intent(this, CwdWebviewActivity.class);
        intent.putExtra("indicate_state", AppConstant.CWD_STATE);
        intent.putExtra("procedure",AppConstant.CWD_PROCEDURE);
        startActivity(intent);
    }
    public void ELEC7(View v) {
        dataBaseUtility.getCWDPanelData(mContext,"007","elec");
        Intent intent = new Intent(this, CwdWebviewActivity.class);
        intent.putExtra("indicate_state", AppConstant.CWD_STATE);
        intent.putExtra("procedure",AppConstant.CWD_PROCEDURE);
        startActivity(intent);
    }
    public void ELEC8(View v) {
        dataBaseUtility.getCWDPanelData(mContext,"008","elec");
        Intent intent = new Intent(this, CwdWebviewActivity.class);
        intent.putExtra("indicate_state", AppConstant.CWD_STATE);
        intent.putExtra("procedure",AppConstant.CWD_PROCEDURE);
        startActivity(intent);
    }
    public void ELEC9(View v) {
        dataBaseUtility.getCWDPanelData(mContext,"009","elec");
        Intent intent = new Intent(this, CwdWebviewActivity.class);
        intent.putExtra("indicate_state", AppConstant.CWD_STATE);
        intent.putExtra("procedure",AppConstant.CWD_PROCEDURE);
        startActivity(intent);
    }
    public void ELEC10(View v) {
        dataBaseUtility.getCWDPanelData(mContext,"010","elec");
        Intent intent = new Intent(this, CwdWebviewActivity.class);
        intent.putExtra("indicate_state", AppConstant.CWD_STATE);
        intent.putExtra("procedure",AppConstant.CWD_PROCEDURE);
        startActivity(intent);
    }
    public void ELEC11(View v) {
        dataBaseUtility.getCWDPanelData(mContext,"011","elec");
        Intent intent = new Intent(this, CwdWebviewActivity.class);
        intent.putExtra("indicate_state", AppConstant.CWD_STATE);
        intent.putExtra("procedure",AppConstant.CWD_PROCEDURE);
        startActivity(intent);
    }
    public void ELEC12(View v) {
        dataBaseUtility.getCWDPanelData(mContext,"012","elec");
        Intent intent = new Intent(this, CwdWebviewActivity.class);
        intent.putExtra("indicate_state", AppConstant.CWD_STATE);
        intent.putExtra("procedure",AppConstant.CWD_PROCEDURE);
        startActivity(intent);
    }
    public void ELEC13(View v) {
        dataBaseUtility.getCWDPanelData(mContext,"013","elec");
        Intent intent = new Intent(this, CwdWebviewActivity.class);
        intent.putExtra("indicate_state", AppConstant.CWD_STATE);
        intent.putExtra("procedure",AppConstant.CWD_PROCEDURE);
        startActivity(intent);
    }
    public void ELEC14(View v) {
        dataBaseUtility.getCWDPanelData(mContext,"014","elec");
        Intent intent = new Intent(this, CwdWebviewActivity.class);
        intent.putExtra("indicate_state", AppConstant.CWD_STATE);
        intent.putExtra("procedure",AppConstant.CWD_PROCEDURE);
        startActivity(intent);
    }


    public void BACK(View v){
        finish();
    }
}
