package com.baf.musafir.phoenix.main;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.baf.musafir.phoenix.R;
import com.baf.musafir.phoenix.util.TouchImageView;
import com.squareup.picasso.Picasso;

public class RadarViewActivity extends Activity {
private Context mContext;
private TouchImageView radar_image;
private String imageUrl="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_radar_view);
        mContext=this;
        imageUrl=getIntent().getStringExtra("imgUrl");
        initUI();
    }
    private void initUI(){
        radar_image=(TouchImageView)findViewById(R.id.radar_image);
       // Picasso.with(mContext).load("https://wx.baf.mil.bd/METBSR/images/omar/RadarSingle/mtr.jpg").into(radar_image);
        Picasso.with( mContext )
                .load( imageUrl )
                .error( R.drawable.ic_launcher )
                .placeholder( R.drawable.progress_animation )
                .into( radar_image );
        //radar_image.setZoom(100);
    }

    public void RELOAD(View v) {
        Picasso.with(mContext).invalidate(imageUrl);
        Picasso.with( mContext )
                .load( imageUrl )
                .error( R.drawable.ic_launcher )
                .placeholder( R.drawable.progress_animation )
                .into( radar_image );


    }
    public void BACK(View v) {
      this.finish();

    }




}
