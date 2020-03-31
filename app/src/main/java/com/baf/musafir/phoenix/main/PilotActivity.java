package com.baf.musafir.phoenix.main;

import android.app.Activity;
import android.content.Context;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.baf.musafir.phoenix.R;
import com.baf.musafir.phoenix.util.ToastUtil;

import java.util.AbstractList;
import java.util.Timer;

import timber.log.Timber;

import static com.baf.musafir.phoenix.util.SecurityUtil.TAG;


public class PilotActivity extends Activity {
    private Context mContext;
    private ImageView chk_img;
    private ImageView imag_circle;
    private ToastUtil toastUtil;
    private double radius=30;
    double distanceX;
    double distanceY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_ipilot);
        mContext = this;
        toastUtil=new ToastUtil(this);
        chk_img=(ImageView)findViewById(R.id.chk_img);
        imag_circle=(ImageView)findViewById(R.id.imag_circle);


        chk_img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                if(event.getAction() == MotionEvent.ACTION_DOWN){
//                    //Check between +- 10px jsu tto have some are to hit
//                    int centerX = (v.getWidth() /2);
//                    if(event.getX()>(centerX-10)&& event.getX() < (centerX + 10)){
//                        toastUtil.appSuccessMsg(mContext,"Clicked");
//                    }
//
//                }

                int xTouch  = (int) event.getX();
                int yTouch  = (int) event.getY();

                int x  = (int) v.getX();
                int y = (int) v.getY();


//                if (173 <= x && x <= 671) {
//                    toastUtil.appSuccessMsg(mContext,"Clicked");
//                }

               double centerX = v.getWidth() / 2;
                double centerY = v.getHeight() / 2;
                radius = 70;
                 distanceX = (xTouch-150) - centerX;
                 distanceY = (yTouch-50) - centerY;
                Timber.i("Coordinate xTouch    " + xTouch);
                Timber.i("Coordinate centerX    " + centerX);
                Timber.d("Coordinate yTouch    " + yTouch);
                Timber.i("Coordinate centerY    " + centerY);
                Timber.d("Coordinate radius    " + radius);
                Timber.d("Coordinate distanceX    " + distanceX);
                Timber.d("Coordinate distanceY    " + distanceY);
                if((Math.pow(distanceX, 2)
                        + Math.pow(distanceY, 2) < Math.pow(radius, 2))){
                    toastUtil.appSuccessMsg(mContext,"Clicked");
                    imag_circle.setVisibility(View.VISIBLE);
                }


                return true;
            }
        });
    }

    boolean isInside() {
        return (distanceX * distanceX) + (distanceY * distanceY) <= radius * radius;
    }


}
