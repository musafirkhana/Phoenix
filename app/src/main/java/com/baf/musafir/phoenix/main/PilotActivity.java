package com.baf.musafir.phoenix.main;

import android.app.Activity;
import android.content.Context;

import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

import com.baf.musafir.phoenix.R;
import com.baf.musafir.phoenix.util.ToastUtil;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;

import java.io.InputStream;


public class PilotActivity extends Activity {
    private Context mContext;
    private ImageView chk_img;
    private ImageView imag_circle;
    private ToastUtil toastUtil;
    private VrPanoramaView mVRPanoramaView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_ipilot);
        mContext = this;
        mVRPanoramaView = (VrPanoramaView) findViewById(R.id.vrPanoramaView);

        loadPhotoSphere();
    }

    @Override
    protected void onResume() {
        mVRPanoramaView.resumeRendering();
        super.onResume();
    }
    @Override
    protected void onPause() {
        mVRPanoramaView.pauseRendering();
        super.onPause();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVRPanoramaView.shutdown();
    }

    private void loadPhotoSphere() {
        VrPanoramaView.Options options = new VrPanoramaView.Options();
        InputStream inputStream = null;

        AssetManager assetManager = getAssets();
        try {
            inputStream = assetManager.open("test.jpg");
            options.inputType = VrPanoramaView.Options.TYPE_MONO;
            mVRPanoramaView.loadImageFromBitmap(BitmapFactory.decodeStream(inputStream), options);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
