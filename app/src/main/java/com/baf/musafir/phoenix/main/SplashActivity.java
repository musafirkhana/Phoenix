package com.baf.musafir.phoenix.main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.SQLException;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;


import com.baf.musafir.phoenix.R;
import com.baf.musafir.phoenix.databse.DataBaseHelper;
import com.baf.musafir.phoenix.util.AppConstant;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission_group.CAMERA;

public class SplashActivity extends Activity {
    private String TAG = SplashActivity.class.getSimpleName();
    private TimerTask sostt;
    private final long period = 5000;
    private final int delay = 1000;
    private Timer sostimer;
    private Context context;
    private static final int PERMISSION_REQUEST_CODE = 200;
    boolean flag = true;
    private File sdCard = Environment.getExternalStorageDirectory();
    private File edenRef_AppoinmentDir = new File(sdCard.getAbsolutePath() + AppConstant.DB_BASE_URL);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        context = this;

        startTimer();

    }


    /*****************
     * Requesting Required Permission
     */
    private void GO() {
        requestPermission();
    }

    /***********************************************
     * This block is use for initial delay at splash screen
     */
    void stopTimer() {
        try {
            if (sostimer != null) {
                sostimer.cancel();
                sostimer = null;
            }
            if (sostt != null) {
                sostt.cancel();
                sostt = null;
            }
        } catch (final Exception e) {
        }
    }

    void startTimer() {
        try {
            sostimer = new Timer();
            sostt = new TimerTask() {

                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            stopTimer();
                            GO();

                        }
                    });

                }
            };
            sostimer.schedule(sostt, delay, period);

        } catch (final Exception e) {
        }

    }
/**
 * This block is use for initial delay at splash screen
 *****************************************************/




    protected void onResume() {

        super.onResume();
        overridePendingTransition(0, 0);
    }

    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {

                    boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean internet = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean callphone = grantResults[2] == PackageManager.PERMISSION_GRANTED;

                    if (locationAccepted && internet && callphone){

                        flag = SharedPreferencesHelper.getFirstTime(context);
                        //will open after final run
                        if (flag) {
                           Toast.makeText(context, "Export Database Starting....", Toast.LENGTH_LONG).show();
                            //exportDatabse();
                            SharedPreferencesHelper.setFirstTime(context, false);
                        }
                       // createFolderStructure();
                        new LoaddbAsyncTask().execute();

                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        SplashActivity.this.finish();
                    }

                    else {
                        Toast.makeText(context,"Permission Denied.",Toast.LENGTH_LONG).show();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)) {
                                showMessageOKCancel("You need to allow access to both the permissions",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{ACCESS_FINE_LOCATION, CAMERA,CALL_PHONE},
                                                            PERMISSION_REQUEST_CODE);
                                                }
                                            }
                                        });
                                return;
                            }
                        }

                    }
                }


                break;
        }


    }


    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION,INTERNET,CALL_PHONE}, PERMISSION_REQUEST_CODE);

    }

    /******************************
     * Load Data From Asset Folder
     ********************************/

    public class LoaddbAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... aurl) {
            loadDataBase();
            return null;
        }

        protected void onProgressUpdate(String... progress) {
            Log.d("ANDRO_ASYNC", progress[0]);
        }
        @SuppressLint("NewApi")
        protected void onPostExecute(String getResult) {
            //progDialogConfirm.dismiss();
        }
    }

    public void loadDataBase() {
        DataBaseHelper myDbHelper = new DataBaseHelper(context);
        myDbHelper = new DataBaseHelper(this);
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);

        }

        try {

            try {
                myDbHelper.openDataBase();
            } catch (java.sql.SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (flag) {
                myDbHelper.copyDataBase();
            }

        } catch (SQLException sqle) {

            throw new Error(sqle);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    /***************************************************
     * Database Work Done Here(Export Database First Time)
     */


// Use only for storage
    public void exportDatabse() {
        try {
            // File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();

            if (edenRef_AppoinmentDir.canWrite()) {
                String currentDBPath = "//data//" + getPackageName() + "//databases//" + AppConstant.DB_NAME + "";

                Log.i("Path Name",""+currentDBPath);
                String backupDBPath = AppConstant.DB_NAME;
                File currentDB = new File(currentDBPath);
                File backupDB = new File(edenRef_AppoinmentDir, backupDBPath);
                if (currentDB.exists()) {

                    FileInputStream fileInputStream = new FileInputStream(currentDB);
                    FileOutputStream fileOutputStream = new FileOutputStream(backupDB);
                    int bufferSize;
                    byte[] bufffer = new byte[8];
                    while ((bufferSize = fileInputStream.read(bufffer)) > 0) {
                        fileOutputStream.write(bufffer, 0, bufferSize);
                    }
                    fileInputStream.close();
                    fileOutputStream.close();
                }
            }
            SharedPreferencesHelper.setFirstTime(context, false);
        } catch (Exception e) {

        }
    }
    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(SplashActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }
}
