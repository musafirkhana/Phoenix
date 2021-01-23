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

public class CwdAirframeActivity extends Activity {
 private Context mContext;
 ToastUtil toastUtil;
 private DataBaseUtility dataBaseUtility;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_cwd_afr);
        mContext=this;
        dataBaseUtility=new DataBaseUtility();
        toastUtil=new ToastUtil(this);
        initUI();

    }
    private void initUI(){

    }
    public void AFR1(View v) {
        dataBaseUtility.getCWDPanelData(mContext,"001","afr");

        Intent intent = new Intent(this, CwdWebviewActivity.class);
        intent.putExtra("indicate_state", AppConstant.CWD_STATE);
        intent.putExtra("procedure",AppConstant.CWD_PROCEDURE);
        startActivity(intent);
    }
    public void AFR2(View v) {
        dataBaseUtility.getCWDPanelData(mContext,"002","afr");
        Intent intent = new Intent(this, CwdWebviewActivity.class);
        intent.putExtra("indicate_state", AppConstant.CWD_STATE);
        intent.putExtra("procedure",AppConstant.CWD_PROCEDURE);
        startActivity(intent);
    }
    public void AFR3(View v) {
        dataBaseUtility.getCWDPanelData(mContext,"003","afr");
        Intent intent = new Intent(this, CwdWebviewActivity.class);
        intent.putExtra("indicate_state", AppConstant.CWD_STATE);
        intent.putExtra("procedure",AppConstant.CWD_PROCEDURE);
        startActivity(intent);
    }

    public void AFR4(View v) {
        dataBaseUtility.getCWDPanelData(mContext,"004","afr");
        Intent intent = new Intent(this, CwdWebviewActivity.class);
        intent.putExtra("indicate_state", AppConstant.CWD_STATE);
        intent.putExtra("procedure",AppConstant.CWD_PROCEDURE);
        startActivity(intent);
    }

    public void AFR5(View v) {
        dataBaseUtility.getCWDPanelData(mContext,"005","afr");
        Intent intent = new Intent(this, CwdWebviewActivity.class);
        intent.putExtra("indicate_state", AppConstant.CWD_STATE);
        intent.putExtra("procedure",AppConstant.CWD_PROCEDURE);
        startActivity(intent);
    }

    public void AFR6(View v) {
        dataBaseUtility.getCWDPanelData(mContext,"006","afr");
        Intent intent = new Intent(this, CwdWebviewActivity.class);
        intent.putExtra("indicate_state", AppConstant.CWD_STATE);
        intent.putExtra("procedure",AppConstant.CWD_PROCEDURE);
        startActivity(intent);
    }
    public void AFR7(View v) {
        dataBaseUtility.getCWDPanelData(mContext,"007","afr");
        Intent intent = new Intent(this, CwdWebviewActivity.class);
        intent.putExtra("indicate_state", AppConstant.CWD_STATE);
        intent.putExtra("procedure",AppConstant.CWD_PROCEDURE);
        startActivity(intent);
    }
    public void AFR8(View v) {
        dataBaseUtility.getCWDPanelData(mContext,"008","afr");
        Intent intent = new Intent(this, CwdWebviewActivity.class);
        intent.putExtra("indicate_state", AppConstant.CWD_STATE);
        intent.putExtra("procedure",AppConstant.CWD_PROCEDURE);
        startActivity(intent);
    }
    public void AFR9(View v) {
        dataBaseUtility.getCWDPanelData(mContext,"009","afr");
        Intent intent = new Intent(this, CwdWebviewActivity.class);
        intent.putExtra("indicate_state", AppConstant.CWD_STATE);
        intent.putExtra("procedure",AppConstant.CWD_PROCEDURE);
        startActivity(intent);
    }
    public void AFR10(View v) {
        dataBaseUtility.getCWDPanelData(mContext,"010","afr");
        Intent intent = new Intent(this, CwdWebviewActivity.class);
        intent.putExtra("indicate_state", AppConstant.CWD_STATE);
        intent.putExtra("procedure",AppConstant.CWD_PROCEDURE);
        startActivity(intent);
    }
    public void AFR11(View v) {
        dataBaseUtility.getCWDPanelData(mContext,"011","afr");
        Intent intent = new Intent(this, CwdWebviewActivity.class);
        intent.putExtra("indicate_state", AppConstant.CWD_STATE);
        intent.putExtra("procedure",AppConstant.CWD_PROCEDURE);
        startActivity(intent);
    }
    public void AFR12(View v) {
        dataBaseUtility.getCWDPanelData(mContext,"012","afr");
        Intent intent = new Intent(this, CwdWebviewActivity.class);
        intent.putExtra("indicate_state", AppConstant.CWD_STATE);
        intent.putExtra("procedure",AppConstant.CWD_PROCEDURE);
        startActivity(intent);
    }
    public void AFR13(View v) {
        dataBaseUtility.getCWDPanelData(mContext,"013","afr");
        Intent intent = new Intent(this, CwdWebviewActivity.class);
        intent.putExtra("indicate_state", AppConstant.CWD_STATE);
        intent.putExtra("procedure",AppConstant.CWD_PROCEDURE);
        startActivity(intent);
    }
    public void AFR14(View v) {
        dataBaseUtility.getCWDPanelData(mContext,"014","afr");
        Intent intent = new Intent(this, CwdWebviewActivity.class);
        intent.putExtra("indicate_state", AppConstant.CWD_STATE);
        intent.putExtra("procedure",AppConstant.CWD_PROCEDURE);
        startActivity(intent);
    }
    public void AFR15(View v) {
        dataBaseUtility.getCWDPanelData(mContext,"015","afr");
        Intent intent = new Intent(this, CwdWebviewActivity.class);
        intent.putExtra("indicate_state", AppConstant.CWD_STATE);
        intent.putExtra("procedure",AppConstant.CWD_PROCEDURE);
        startActivity(intent);
    }

    public void BACK(View v){
        finish();
    }
}
