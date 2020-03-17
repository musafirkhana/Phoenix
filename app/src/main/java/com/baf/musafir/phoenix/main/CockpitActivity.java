package com.baf.musafir.phoenix.main;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.baf.musafir.phoenix.R;

import timber.log.Timber;


public class CockpitActivity extends Activity {
private Context mContext;


    Bitmap mBitmap;
    Paint paint;
    float x = 0;
    float y = 0;
    float centerX = 0 ;
    float centerY = 0;
    float xTouch  = 0;
    float yTouch  = 0;

    double distanceX  = 0 ;
    double distanceY  = 0;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_cockpit);
        mContext=this;

        mBitmap = Bitmap.createBitmap(400, 800, Bitmap.Config.ARGB_8888);
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);



        final RelativeLayout img_cockpi = (RelativeLayout) findViewById(R.id.img_cockpi);
        x=img_cockpi.getX();
        y=img_cockpi.getY();
        img_cockpi.addView(new CustomView(this));



    }
    public class CustomView extends View {

        Bitmap mBitmap;
        Paint paint;

        public CustomView(Context context) {
            super(context);
            mBitmap = Bitmap.createBitmap(400, 800, Bitmap.Config.ARGB_8888);
            paint = new Paint();
            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.FILL);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
//            if(centerX>= 838.0 && centerY<=662.0){
//                canvas.drawCircle(centerX, centerY, 100, paint);
//            }

            if(isInside()){
                canvas.drawCircle(centerX, centerY, 100, paint);
            }

        }

        public boolean onTouchEvent(MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                centerX = event.getX();
                centerY = event.getY();
                centerX = x + 100;
                centerY = y + 100;
                distanceX = xTouch - centerX;
                distanceY = yTouch - centerY;
                Timber.i(""+distanceX);
                Timber.i(""+distanceX);


                invalidate();
            }
            return false;
        }

        boolean isInside() {
            return (distanceX * distanceX) + (distanceY * distanceY) <= 100 * 100;
        }
    }
}
