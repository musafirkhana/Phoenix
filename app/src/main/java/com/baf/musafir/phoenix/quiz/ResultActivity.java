package com.baf.musafir.phoenix.quiz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.baf.musafir.phoenix.R;
import com.baf.musafir.phoenix.util.AppConstant;



public class ResultActivity extends Activity implements View.OnClickListener {

    private Context mContext;
    private TextView scoreTxtView;
    private TextView disposal_tv;
    private TextView topbar;
    private  TextView scoreText;
    private Button btn_home;
    private Button btn_playagain;
    private RatingBar ratingBar;
    private ImageView img;
    Typeface tf ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_quiz_result);
        mContext = this;

        initUI();
        changeFont();

    }

    private void initUI() {
        SharedPreferences sharedPreferences = getSharedPreferences("Result", Context.MODE_PRIVATE);
        //int score1 = sharedPreferences.getInt(result, score);
        int score1 = AppConstant.SCORE;
        scoreTxtView = (TextView) findViewById(R.id.scoreTxtView);
        scoreText=(TextView)findViewById(R.id.scoreText);
        btn_home = (Button) findViewById(R.id.btn_home);
        btn_playagain = (Button) findViewById(R.id.btn_playagain);
        disposal_tv = (TextView) findViewById(R.id.disposal_tv);
        topbar=(TextView)findViewById(R.id.topbar);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        img = (ImageView) findViewById(R.id.img1);
        btn_home.setOnClickListener(this);
        btn_playagain.setOnClickListener(this);

        ratingBar.setRating(score1);
        scoreTxtView.setText(String.valueOf(score1));

        if (score1 == 0) {
            img.setImageResource(R.drawable.score_0);
            disposal_tv.setText(AppConstant.DISP_1);
        } else if (score1 == 1) {
            img.setImageResource(R.drawable.score_1);
            disposal_tv.setText(AppConstant.DISP_2);
        } else if (score1 == 2) {
            img.setImageResource(R.drawable.score_2);
            disposal_tv.setText(AppConstant.DISP_3);
        } else if (score1 == 3) {
            img.setImageResource(R.drawable.score_3);
            disposal_tv.setText(AppConstant.DISP_4);
        } else if (score1 == 4) {
            img.setImageResource(R.drawable.score_4);
            disposal_tv.setText(AppConstant.DISP_5);
        } else if (score1 == 5) {
            img.setImageResource(R.drawable.score_5);
            disposal_tv.setText(AppConstant.DISP_5);
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_home:
                this.finish();
                break;
            case R.id.btn_playagain:
                //Stop MediaPlayer
                Intent intent = new Intent(ResultActivity.this, QuizlistActivity.class);
                startActivity(intent);
                this.finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();


    }

    private void changeFont(){
        tf = Typeface.createFromAsset(mContext.getAssets(),
                "fonts/megatron.ttf");

        scoreTxtView.setTypeface(tf);
        btn_home.setTypeface(tf);
        btn_playagain.setTypeface(tf);
        disposal_tv.setTypeface(tf);
        topbar.setTypeface(tf);
        scoreText.setTypeface(tf);

    }

}
