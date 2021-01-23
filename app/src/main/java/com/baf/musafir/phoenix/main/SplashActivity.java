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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import androidx.core.app.ActivityCompat;

import com.baf.musafir.phoenix.R;
import com.baf.musafir.phoenix.adapter.FlhhourAdapter;
import com.baf.musafir.phoenix.databse.DataBaseHelper;
import com.baf.musafir.phoenix.util.AppConstant;
import com.baf.musafir.phoenix.util.ToastUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

import timber.log.Timber;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission_group.CAMERA;

public class SplashActivity extends Activity {
    private String TAG = SplashActivity.class.getSimpleName();
    private Context context;
    private static final int PERMISSION_REQUEST_CODE = 200;
    boolean flag = true;
    private File sdCard = Environment.getExternalStorageDirectory();
    private File edenRef_AppoinmentDir = new File(sdCard.getAbsolutePath() + AppConstant.DB_BASE_URL);
    private boolean mDataBaseExist ;
    private DataBaseHelper myDbHelper;
    private ToastUtil toastUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        context = this;
        toastUtil=new ToastUtil(this);
        myDbHelper = new DataBaseHelper(context);
        mDataBaseExist = myDbHelper.checkDataBase();
        flag = SharedPreferencesHelper.getFirstTime(context);

        if(mDataBaseExist){
//            toastUtil.appSuccessMsg(context,"DB Available");
            Timber.i("Database path    " + myDbHelper.getDbPath());

        }
        Timber.i("Database path    " + flag);
        if (flag) {
            showCustomDialog();
        }else {
            requestPermission();
        }



    }



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

                        //will open after final run
                        if (flag) {
                           Toast.makeText(context, "Export Database Please wait....", Toast.LENGTH_LONG).show();
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
//        DataBaseHelper myDbHelper = new DataBaseHelper(context);
//        myDbHelper = new DataBaseHelper(this);
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


    private void showCustomDialog() {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        final View dialogView = LayoutInflater.from(this).inflate(R.layout.password_dialog, viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        Button btn_cancel = (Button) dialogView.findViewById(R.id.btn_cancel);
        Button btn_apply = (Button) dialogView.findViewById(R.id.btn_apply);
        final EditText filter_head=(EditText)dialogView.findViewById(R.id.et_password);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                alertDialog.dismiss();
            }
        });
        btn_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(filter_head.getText().toString().equalsIgnoreCase(AppConstant.APP_PASSWORD)){
                    alertDialog.dismiss();
                    requestPermission();

                }else if(filter_head.getText().toString().equalsIgnoreCase("")){
                    toastUtil.appSuccessMsg(context,"Please Enter Password");
                } else {
                    toastUtil.appSuccessMsg(context,"Sory Password Does not match Try Again");
                }



            }
        });
        alertDialog.show();
    }
}
