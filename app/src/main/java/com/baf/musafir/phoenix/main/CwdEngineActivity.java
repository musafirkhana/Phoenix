package com.baf.musafir.phoenix.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.baf.musafir.phoenix.R;
import com.baf.musafir.phoenix.databse.DataBaseUtility;
import com.baf.musafir.phoenix.util.AppConstant;
import com.baf.musafir.phoenix.util.ToastUtil;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.baf.musafir.phoenix.util.SystemUtil.TAG;

public class CwdEngineActivity extends Activity {
 private Context mContext;
 ToastUtil toastUtil;
 private DataBaseUtility dataBaseUtility;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_cwd_eng);
        mContext=this;
        dataBaseUtility=new DataBaseUtility();
        toastUtil=new ToastUtil(this);
        initUI();

    }
    private void initUI(){

    }
    public void ENG1(View v) {
        dataBaseUtility.getCWDPanelData(mContext,"001","eng");

        Intent intent = new Intent(this, CwdWebviewActivity.class);
        intent.putExtra("indicate_state", AppConstant.CWD_STATE);
        intent.putExtra("procedure",AppConstant.CWD_PROCEDURE);
        startActivity(intent);
    }
    public void ENG2(View v) {
        dataBaseUtility.getCWDPanelData(mContext,"002","eng");

        Intent intent = new Intent(this, CwdWebviewActivity.class);
        intent.putExtra("indicate_state", AppConstant.CWD_STATE);
        intent.putExtra("procedure",AppConstant.CWD_PROCEDURE);
        startActivity(intent);
    }
    public void ENG3(View v) {
        dataBaseUtility.getCWDPanelData(mContext,"003","eng");
        Intent intent = new Intent(this, CwdWebviewActivity.class);
        intent.putExtra("indicate_state", AppConstant.CWD_STATE);
        intent.putExtra("procedure",AppConstant.CWD_PROCEDURE);
        startActivity(intent);
    }

    public void ENG4(View v) {
        dataBaseUtility.getCWDPanelData(mContext,"004","eng");
        Intent intent = new Intent(this, CwdWebviewActivity.class);
        intent.putExtra("indicate_state", AppConstant.CWD_STATE);
        intent.putExtra("procedure",AppConstant.CWD_PROCEDURE);
        startActivity(intent);
    }

    public void ENG5(View v) {
        dataBaseUtility.getCWDPanelData(mContext,"005","eng");
        Intent intent = new Intent(this, CwdWebviewActivity.class);
        intent.putExtra("indicate_state", AppConstant.CWD_STATE);
        intent.putExtra("procedure",AppConstant.CWD_PROCEDURE);
        startActivity(intent);
    }

    public void ENG6(View v) {
        dataBaseUtility.getCWDPanelData(mContext,"006","eng");
        Intent intent = new Intent(this, CwdWebviewActivity.class);
        intent.putExtra("indicate_state", AppConstant.CWD_STATE);
        intent.putExtra("procedure",AppConstant.CWD_PROCEDURE);
        startActivity(intent);
    }
    public void ENG7(View v) {
        dataBaseUtility.getCWDPanelData(mContext,"007","eng");
        Intent intent = new Intent(this, CwdWebviewActivity.class);
        intent.putExtra("indicate_state", AppConstant.CWD_STATE);
        intent.putExtra("procedure",AppConstant.CWD_PROCEDURE);
        startActivity(intent);
    }
    public void ENG8(View v) {
        dataBaseUtility.getCWDPanelData(mContext,"008","eng");
        Intent intent = new Intent(this, CwdWebviewActivity.class);
        intent.putExtra("indicate_state", AppConstant.CWD_STATE);
        intent.putExtra("procedure",AppConstant.CWD_PROCEDURE);
        startActivity(intent);
    }
    public void ENG9(View v) {
        dataBaseUtility.getCWDPanelData(mContext,"009","eng");
        Intent intent = new Intent(this, CwdWebviewActivity.class);
        intent.putExtra("indicate_state", AppConstant.CWD_STATE);
        intent.putExtra("procedure",AppConstant.CWD_PROCEDURE);
        startActivity(intent);
    }
    public void ENG10(View v) {
        dataBaseUtility.getCWDPanelData(mContext,"010","eng");
        Intent intent = new Intent(this, CwdWebviewActivity.class);
        intent.putExtra("indicate_state", AppConstant.CWD_STATE);
        intent.putExtra("procedure",AppConstant.CWD_PROCEDURE);
        startActivity(intent);
    }
    public void ENG11(View v) {
        dataBaseUtility.getCWDPanelData(mContext,"011","eng");
        Intent intent = new Intent(this, CwdWebviewActivity.class);
        intent.putExtra("indicate_state", AppConstant.CWD_STATE);
        intent.putExtra("procedure",AppConstant.CWD_PROCEDURE);
        startActivity(intent);
    }
    public void ENG12(View v) {
        dataBaseUtility.getCWDPanelData(mContext,"012","eng");
        Intent intent = new Intent(this, CwdWebviewActivity.class);
        intent.putExtra("indicate_state", AppConstant.CWD_STATE);
        intent.putExtra("procedure",AppConstant.CWD_PROCEDURE);
        startActivity(intent);
    }
    public void ENG13(View v) {
        dataBaseUtility.getCWDPanelData(mContext,"013","eng");
        Intent intent = new Intent(this, CwdWebviewActivity.class);
        intent.putExtra("indicate_state", AppConstant.CWD_STATE);
        intent.putExtra("procedure",AppConstant.CWD_PROCEDURE);
        startActivity(intent);
    }
    public void ENG14(View v) {
        dataBaseUtility.getCWDPanelData(mContext,"014","eng");
        Intent intent = new Intent(this, CwdWebviewActivity.class);
        intent.putExtra("indicate_state", AppConstant.CWD_STATE);
        intent.putExtra("procedure",AppConstant.CWD_PROCEDURE);
        startActivity(intent);
    }
    public void ENG15(View v) {
        dataBaseUtility.getCWDPanelData(mContext,"015","eng");
        Intent intent = new Intent(this, CwdWebviewActivity.class);
        intent.putExtra("indicate_state", AppConstant.CWD_STATE);
        intent.putExtra("procedure",AppConstant.CWD_PROCEDURE);
        startActivity(intent);
    }
    public void ENG16(View v) {
        dataBaseUtility.getCWDPanelData(mContext,"016","eng");
        Intent intent = new Intent(this, CwdWebviewActivity.class);
        intent.putExtra("indicate_state", AppConstant.CWD_STATE);
        intent.putExtra("procedure",AppConstant.CWD_PROCEDURE);
        startActivity(intent);
    }
    public void BACK(View v){
        finish();
    }
}
