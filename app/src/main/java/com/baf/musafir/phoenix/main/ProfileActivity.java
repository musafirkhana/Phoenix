package com.baf.musafir.phoenix.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import com.baf.musafir.phoenix.R;
import com.baf.musafir.phoenix.util.ToastUtil;

import de.hdodenhof.circleimageview.CircleImageView;
public class ProfileActivity extends Activity {
 private Context mContext;
 ToastUtil toastUtil;
    private TextView tv_name;
    private TextView tv_appoinment;
    private TextView tv_rank;
    private TextView tv_branch;
    private TextView tv_dob;
    private TextView tv_blood;
    private TextView tv_mobile;
    private TextView tv_email;
    private CircleImageView profile;

    private String st_name;
    private String st_appoinment;
    private String st_rank;
    private String st_branch;
    private String st_dob;
    private String st_blood;
    private String st_mobile;
    private String st_email;
    private String profile_string;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_profile);
        mContext=this;
        toastUtil=new ToastUtil(this);
        initUI();

    }
    private void initUI(){
        tv_name=(TextView)findViewById(R.id.tv_name);
        tv_appoinment=(TextView)findViewById(R.id.tv_appoinment);
        tv_rank=(TextView)findViewById(R.id.tv_rank);
        tv_branch=(TextView)findViewById(R.id.tv_branch);
        tv_dob=(TextView)findViewById(R.id.tv_dob);
        tv_blood=(TextView)findViewById(R.id.tv_blood);
        tv_mobile=(TextView)findViewById(R.id.tv_mobile);
        tv_email=(TextView)findViewById(R.id.tv_email);
        profile=(CircleImageView)findViewById(R.id.profile);

        st_name=getIntent().getStringExtra("name");
        st_appoinment=getIntent().getStringExtra("appoinment");
        st_rank=getIntent().getStringExtra("rank");
        st_branch=getIntent().getStringExtra("branch");
        st_dob=getIntent().getStringExtra("dob");
        st_blood=getIntent().getStringExtra("blood");
        st_mobile=getIntent().getStringExtra("mobile");
        st_email=getIntent().getStringExtra("email");
        profile_string=getIntent().getStringExtra("profile");


        tv_name.setText(st_name);
        tv_appoinment.setText(st_appoinment);
        tv_rank.setText(st_rank);
        tv_branch.setText(st_branch);
        tv_dob.setText(st_dob);
        tv_blood.setText(st_blood);
        tv_mobile.setText(st_mobile);
        tv_email.setText(st_email);
//        Uri uri=Uri.parse("R.drawable."+profile_string);
//        if(profile_string!=null){
//            profile.setImageURI(uri);
//
//        }




    }

    public void Call(View v){
        //toastUtil.appSuccessMsg(mContext,st_mobile);
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + st_mobile));
        startActivity(intent);
    }

    public void Email(View v){
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto",st_email, null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }

    public void BACK(View v){
        finish();
    }
}
