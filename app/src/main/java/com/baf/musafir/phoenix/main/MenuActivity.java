package com.baf.musafir.phoenix.main;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.baf.musafir.phoenix.R;
import com.baf.musafir.phoenix.quiz.QuizlistActivity;
import com.baf.musafir.phoenix.util.ToastUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;


public class MenuActivity extends Activity {
    Typeface tf;
    private Context mContext;
    private TextView topbar;
    private TextView cktv_1;
    private TextView cktv_2;
    private TextView excercise_scroll;
    private ToastUtil toastUtil;
    final private int REQUEST_CODE_ASK_PERMISSIONS_STORGE = 10000;
    private List<String> permissions = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_menu);
        mContext = this;
        tf = Typeface.createFromAsset(mContext.getAssets(),
                "fonts/megatron.ttf");
        changeFont();
        toastUtil = new ToastUtil(this);
    }
    public void CK0(View v) {

        Intent intent = new Intent(this, QuizlistActivity.class);
        startActivity(intent);
    }
    public void CK1(View v) {

        getPermission();
    }

    public void CK2(View v) {

        Intent intent = new Intent(this, InfoActivity.class);
        startActivity(intent);
    }

    public void MANUALWEB(View v) {

        Intent intent = new Intent(this, GenerelWEbActivity.class);
        intent.putExtra("url","http://clientzone.let.cz/?lang=en");
        startActivity(intent);
    }
    public void BACK(View v) {
        this.finish();

    }

    private void changeFont() {
        topbar = (TextView) findViewById(R.id.topbar);
        cktv_1 = (TextView) findViewById(R.id.cktv_1);
        cktv_2 = (TextView) findViewById(R.id.cktv_2);
        excercise_scroll = (TextView) findViewById(R.id.excercise_scroll);

        topbar.setTypeface(tf);
//        cktv_1.setTypeface(tf);
//        cktv_2.setTypeface(tf);
//        cktv_3.setTypeface(tf);
//        cktv_4.setTypeface(tf);
        excercise_scroll.setTypeface(tf);

    }

    private void startpdf() {

        try {
            String root = Environment.getExternalStorageDirectory().toString();
            File myDir = new File(root + "/phonix_pdf");
            String fname = "flightmannual" + ".pdf";
            File filePDD = new File(myDir, fname);
            if (!filePDD.exists()) {
                copyAssets(fname);
            }
            String pdfFilename = filePDD.getAbsolutePath();
            Intent intentShareFile = new Intent(Intent.ACTION_VIEW);
            File fileWithinMyDir = new File(pdfFilename);
            if (fileWithinMyDir.exists()) {
                Uri imageUri = FileProvider.getUriForFile(
                        mContext,
                        "com.baf.musafir.phoenix.provider", new File(pdfFilename));

                intentShareFile.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intentShareFile.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intentShareFile.setDataAndType(imageUri, "application/pdf");
                startActivity(intentShareFile);

            }

        } catch (Exception ex) {
            ex.toString();
        }


    }

    private void getPermission(){
        if (Build.VERSION.SDK_INT > 22) {
            String storagePermission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
            String storageReadPermission = Manifest.permission.READ_EXTERNAL_STORAGE;
            int hasstoragePermission = checkSelfPermission(storagePermission);
            if (hasstoragePermission != PackageManager.PERMISSION_GRANTED) {
                permissions.add(storagePermission);
                permissions.add(storageReadPermission);
            }
            if (!permissions.isEmpty()) {
                String[] params = permissions.toArray(new String[permissions.size()]);
                requestPermissions(params, REQUEST_CODE_ASK_PERMISSIONS_STORGE);
            } else {
                startpdf();
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS_STORGE:
                if (grantResults.length > 0) {
                    boolean writePermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean readPermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if (writePermission && readPermission) {
                        startpdf();

                    } else {
                    }
                }
                break;

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    private void copyAssets(String fileName) {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/phonix_pdf");
        myDir.mkdirs();

        File filePDD = new File(myDir, fileName);
        if (!filePDD.exists()) {
            copyDocToSdCard(filePDD, fileName);
        }

    }




    private void copyDocToSdCard(final File testImageOnSdCard,
                                 String fileName) {

        final String FILE = fileName;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    InputStream is = getAssets().open(FILE);
                    FileOutputStream fos = new FileOutputStream(
                            testImageOnSdCard);
                    byte[] buffer = new byte[8192];
                    int read;
                    try {
                        while ((read = is.read(buffer)) != -1) {
                            fos.write(buffer, 0, read);
                        }
                    } finally {
                        fos.flush();
                        fos.close();
                        is.close();
                    }
                } catch (IOException e) {

                }
            }
        }).start();
    }
}