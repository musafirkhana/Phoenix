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
        dataBaseUtility.getCWDPanelData(mContext,"eng001");

        Intent intent = new Intent(this, CwdWebviewActivity.class);
        intent.putExtra("indicate_state", AppConstant.CWD_STATE);
        intent.putExtra("procedure",AppConstant.CWD_PROCEDURE);
        startActivity(intent);
    }
    public void BACK(View v){
        finish();
    }
}
