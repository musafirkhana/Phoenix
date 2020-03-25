package com.baf.musafir.phoenix.quiz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;

import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.baf.musafir.phoenix.R;
import com.baf.musafir.phoenix.databse.DataBaseUtility;
import com.baf.musafir.phoenix.util.AppConstant;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class QuizMainActivity extends Activity {
    //put question id into list
    List<Question> questionList;
    int quid = 0;
    Question currentQuestion;
    public static String result="highscore";
    public static  int score = 0;
    DataBaseUtility dataBaseUtility;
    Typeface tf ;
    private Context mContext;
    private static CountDownTimer countDownTimer;
    private long timerTime=60000*5;
    private String quizType;

    /**************
     * Init UI
     */
    //private TextView scoreno;
    private TextView txtQuestion;
    private TextView topbar;
    private TextView tv_timer;
    private  RadioButton radio0,radio1,radio2,radio3;
    private Button butNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_quiz_main);
        mContext=this;
        AppConstant.SCORE=0;
        quizType=getIntent().getStringExtra("quizType");
        dataBaseUtility=new DataBaseUtility();
       // scoreno=(TextView)findViewById(R.id.score);
        tf = Typeface.createFromAsset(mContext.getAssets(),
                "fonts/megatron.ttf");
        //get all question from db
        questionList = dataBaseUtility.getQuizData(getApplicationContext(),quizType);

        //random question
        Collections.shuffle(questionList);
        currentQuestion = questionList.get(quid);
        initUI();

        setQuestionView();
        changeFont();
        countTime(timerTime);
        blink();

    }

    private void initUI(){
        txtQuestion = (TextView)findViewById(R.id.question);
        topbar=(TextView)findViewById(R.id.topbar);
        tv_timer=(TextView)findViewById(R.id.tv_timer);
        radio0 = (RadioButton)findViewById(R.id.radio0);
        radio1 = (RadioButton)findViewById(R.id.radio1);
        radio2 = (RadioButton)findViewById(R.id.radio2);
        radio3=(RadioButton)findViewById(R.id.radio3) ;
        butNext = (Button)findViewById(R.id.button1);
    }

    private void setQuestionView(){
        txtQuestion.setText("Q-"+(quid+1)+" "+currentQuestion.getQuestion());
        radio0.setText(currentQuestion.getOptA());
        radio1.setText(currentQuestion.getOptB());
        radio2.setText(currentQuestion.getOptC());
        radio3.setText(currentQuestion.getOptD());
        quid++;
    }

    public void btClick(View view){
        RadioGroup grp = (RadioGroup)findViewById(R.id.radioGroup1);
        RadioButton answer = (RadioButton)findViewById(grp.getCheckedRadioButtonId());
        if(currentQuestion.getAnswer().equals(answer.getText())){
            score++;
            Log.d("Score", "Your score: "+score);
            //scoreno.setText(" "+score);
        }

        if(quid<5){
            currentQuestion = questionList.get(quid);
            setQuestionView();
        }else{


            final SharedPreferences sharedPreferences = getSharedPreferences("Result", Context.MODE_PRIVATE);
            final SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(result,score);
            editor.commit();

            AppConstant.SCORE=score;

            Intent intent = new Intent(QuizMainActivity.this, ResultActivity.class);
            startActivity(intent);
            finish();
        }


    }


    public void highscore(View v){
        Intent intent = new Intent(QuizMainActivity.this, ResultActivity.class);
        startActivity(intent);
        finish();
    }
    private void changeFont(){
        txtQuestion.setTypeface(tf);
        radio0.setTypeface(tf);
        radio1.setTypeface(tf);
        radio2.setTypeface(tf);
        radio3.setTypeface(tf);
        topbar.setTypeface(tf);
        butNext.setTypeface(tf);
        tv_timer.setTypeface(tf);
    }
    /********************************Countdown Timer Methd*******************/

    //Start Countodwn method
    private void startTimer(long noOfMinutes) {
        Log.i("NamazTime Mill TIMER", "" + noOfMinutes);
        countDownTimer = new CountDownTimer(noOfMinutes, 1000) {
            public void onTick(long millisUntilFinished) {

                long millis = millisUntilFinished;
                //Convert milliseconds into hour,minute and seconds
                String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis), TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                Log.i("NamazTime Mill ss", "" + hms);
                tv_timer.setText(hms);//set text

            }

            public void onFinish() {
                countDownTimer = null;//set CountDownTimer to null
                Intent intent = new Intent(QuizMainActivity.this, ResultActivity.class);
                startActivity(intent);
                finish();

            }
        }.start();

    }
    private void countTime(long setTimerTime) {

        if (countDownTimer == null) {
            startTimer(setTimerTime);//start countdown
        } else {
            //Else stop timer and change text
            stopCountdown();
        }
        //  startTimer(noOfMinute);//start countdown

    }
    //Stop Countdown method
    private void stopCountdown() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }
    private void blink(){
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int timeToBlink = 1000;    //in milissegunds
                try{Thread.sleep(timeToBlink);}catch (Exception e) {}
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(tv_timer.getVisibility() == View.VISIBLE){
                            tv_timer.setVisibility(View.INVISIBLE);
                        }else{
                            tv_timer.setVisibility(View.VISIBLE);
                        }
                        blink();
                    }
                });
            }
        }).start();
    }
    @Override
    public void finish()
    {
        stopCountdown();
        super.finish();
    }

    public void BACK(View v) {
        finish();
    }
}
