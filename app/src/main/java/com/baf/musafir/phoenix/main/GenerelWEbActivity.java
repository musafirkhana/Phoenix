package com.baf.musafir.phoenix.main;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.baf.musafir.phoenix.R;
import com.baf.musafir.phoenix.util.CustomProgressDialog;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import static com.baf.musafir.phoenix.util.BitmapUtil.TAG;


public class GenerelWEbActivity extends Activity {

    private Context mContex;
    private TextView header;
    private WebView webView;
    private String url;
    ProgressDialog dialog;
    private ProgressDialog customProgressDialog;
    public String StorezipFileLocation = "phonix_pdf";
    Typeface tf;
    String root = Environment.getExternalStorageDirectory().toString();
    File myDir ;

    File filePDD ;
    private long  imid;
    private DownloadManager downloadManager;
    private boolean idDownloadRunning = false;
    String fileName,fileNameWithoutExtn;
    /**
     * Called when the activity is first created.
     */
    @SuppressWarnings("static-access")
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.gen_webview);
        mContex = this;

        myDir = new File(root + "/phonix_pdf");
        myDir.mkdirs();




        customProgressDialog = new CustomProgressDialog(mContex, "Loading Pdf....", true);
        url=getIntent().getStringExtra("url");
        tf = Typeface.createFromAsset(mContex.getAssets(),
                "fonts/megatron.ttf");
        header = (TextView) findViewById(R.id.header);
        webView = (WebView) findViewById(R.id.webview);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true); // allow pinch to zooom
        settings.setDisplayZoomControls(false);
        webView.setWebViewClient(new WebViewClient() {

            // This method will be triggered when the Page Started Loading

            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                dialog = ProgressDialog.show(GenerelWEbActivity.this, null,
                        "PLease wait .....");
                dialog.setCancelable(true);
                super.onPageStarted(view, url, favicon);
            }

            // This method will be triggered when the Page loading is completed

            public void onPageFinished(WebView view, String url) {
                dialog.dismiss();
                super.onPageFinished(view, url);
            }

            // This method will be triggered when error page appear

            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                dialog.dismiss();
                // You can redirect to your own page instead getting the default
                // error page
                Toast.makeText(GenerelWEbActivity.this,
                        "The Requested Page Does Not Exist", Toast.LENGTH_LONG).show();
                webView.loadUrl("Musafir Ali");
                super.onReceivedError(view, errorCode, description, failingUrl);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if(url.contains(".pdf")){
                    Log.i("DownloadManager", url);
                    if (isConnected()) {
                        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                            idDownloadRunning = true;
                            new DownloadFile().execute(url);
                            // playPouse();

                        } else {
                            startDownload(url);
                        }

                    }
                    Toast.makeText(GenerelWEbActivity.this,
                            "PDF PDF", Toast.LENGTH_LONG).show();
                }
                view.loadUrl(url);
                return true;
            }
        });
        settings.setDefaultTextEncodingName("utf-8");

			webView.loadUrl(url);


        changeFont();

    }






    /**
     * Async Task to download file from URL
     */
    private class DownloadFile extends AsyncTask<String, String, String> {

        private ProgressDialog progressDialog;
        private String fileName;
        private String folder;
        private boolean isDownloaded;

        /**
         * Before starting background thread
         * Show Progress Bar Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            customProgressDialog = new ProgressDialog(mContex);
            customProgressDialog.setMessage("Downloading file..");
            customProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            customProgressDialog.setCancelable(false);
            customProgressDialog.show();
        }

        /**
         * Downloading file in background thread
         */
        @Override
        protected String doInBackground(String... f_url) {

             fileName = f_url[0].substring( f_url[0].lastIndexOf('/')+1, f_url[0].length() );
             fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
            filePDD = new File(myDir, fileNameWithoutExtn+".pdf");

            try {
                URL url = new URL(f_url[0]);
                URLConnection connection = url.openConnection();
                connection.setRequestProperty("Accept-Encoding", "identity");
                connection.connect();
                // getting file length
                int lengthOfFile = connection.getContentLength();

                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(), 8192);
                //InputStream input = connection.getInputStream();
                fileName = f_url[0].substring(f_url[0].lastIndexOf('/') + 1, f_url[0].length());
                OutputStream output = new FileOutputStream(filePDD);

                byte data[] = new byte[1024];

                long total = 0;
                int count;
                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress("" + (int) ((total * 100) / lengthOfFile));
                    Log.d(TAG, "Progress: L " + lengthOfFile);
                    Log.d(TAG, "Progress: T " + total);
                    //publishProgress("" + (int) ((total / (float) lengthOfFile) *100 ));

                    //publishProgress((int) ((total * 100) / lengthOfFile));
                    // writing data to file
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();
                //return "Downloaded at: " + unzipLocation;
                return null;

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return "Something went wrong";
        }

        /**
         * Updating progress bar
         */
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            Log.d(TAG, "Progress: " + Integer.parseInt(progress[0]));
            customProgressDialog.setProgress(Integer.parseInt(progress[0]));
        }


        @Override
        protected void onPostExecute(String message) {
            // dismiss the dialog after the file was downloaded
            customProgressDialog.dismiss();
            Log.d(TAG, "Download Complete: " + "sadsadsad");
            filePDD = new File(myDir, fileNameWithoutExtn+".pdf");
            startpdf();
            finish();

        }
    }
    public void startDownload(String url) {


        Uri uri = Uri.parse(url);
        if (uri.toString().endsWith(".jpg")) {
            imid = DownloadData(uri);
            Check_Image_Status(imid);
        } else if (uri.toString().endsWith(".pdf")) {
            imid = DownloadData(uri);
            Check_Image_Status(imid);
        }
        //Toast.makeText(getApplicationContext(), uri.toString(), Toast.LENGTH_LONG).show();
        registerReceiver(onComplete,
                new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

    }

    BroadcastReceiver onComplete = new BroadcastReceiver() {

        public void onReceive(Context ctxt, Intent intent) {
            long referenceId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);

            if (referenceId == imid) {
                Log.i("DownloadManager", "Image Download Complete");

            }

        }
    };
    private long DownloadData(Uri uri) {
        long downloadReference;
        String fileName = url.substring( url.lastIndexOf('/')+1, url.length() );
        String fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
        downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        //Setting title of request
        request.setTitle("sadsadsadsad");
        //Set the local destination for the downloaded file to a path within the application's external files directory
        if (uri.toString().endsWith(".pdf")) {
            request.setDestinationInExternalFilesDir(GenerelWEbActivity.this, Environment.DIRECTORY_DOWNLOADS, "AndroidTutorialPoint.jpg");
        } else if (uri.toString().endsWith(".zip")) {
            //request.setDestinationInExternalPublicDir("/Database","sdsd.zip");
            if (isFileExists(StorezipFileLocation)) {
                deleteFile(StorezipFileLocation);
                request.setDestinationUri(Uri.parse("file://" + fileNameWithoutExtn + "/" + StorezipFileLocation));
            } else {
                request.setDestinationUri(Uri.parse("file://" + fileNameWithoutExtn + "/" + StorezipFileLocation));
            }
        } else if (uri.toString().endsWith(".png")) {
            request.setDestinationInExternalFilesDir(GenerelWEbActivity.this, Environment.DIRECTORY_DOWNLOADS, "AndroidTutorialPoint.png");
        } else if (uri.toString().endsWith(".jpeg")) {
            request.setDestinationInExternalFilesDir(GenerelWEbActivity.this, Environment.DIRECTORY_DOWNLOADS, "AndroidTutorialPoint.jpeg");
        }  //Enqueue download and save the referenceId
        downloadReference = downloadManager.enqueue(request);

        return downloadReference;
    }
    private boolean isFileExists(String filename) {

        File folder1 = new File(filename);
        return folder1.exists();
    }

    public boolean deleteFile(String filename) {
        File folder1 = new File(filename);
        return folder1.delete();

    }
    private void Check_Image_Status(long imid) {
        DownloadManager.Query ImageDownloadQuery = new DownloadManager.Query();
        //set the query filter to our previously Enqueued download
        ImageDownloadQuery.setFilterById(imid);

        //Query the download manager about downloads that have been requested.
        Cursor cursor = downloadManager.query(ImageDownloadQuery);
        if (cursor.moveToFirst()) {
            DownloadStatus(cursor, imid);
        }
    }
    public boolean isConnected() {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }
    private void DownloadStatus(Cursor cursor, long DownloadId) {
        int columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
        int status = cursor.getInt(columnIndex);
        //column for reason code if the download failed or paused
        int columnReason = cursor.getColumnIndex(DownloadManager.COLUMN_REASON);
        int reason = cursor.getInt(columnReason);
        //get the download filename
        int filenameIndex = cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_FILENAME);
        String filename = cursor.getString(filenameIndex);

        String statusText = "";
        String reasonText = "";

        switch (status) {
            case DownloadManager.STATUS_FAILED:
                statusText = "STATUS_FAILED";
                switch (reason) {
                    case DownloadManager.ERROR_CANNOT_RESUME:
                        reasonText = "ERROR_CANNOT_RESUME";
                        break;
                    case DownloadManager.ERROR_DEVICE_NOT_FOUND:
                        reasonText = "ERROR_DEVICE_NOT_FOUND";
                        break;
                    case DownloadManager.ERROR_FILE_ALREADY_EXISTS:
                        reasonText = "ERROR_FILE_ALREADY_EXISTS";
                        break;
                    case DownloadManager.ERROR_FILE_ERROR:
                        reasonText = "ERROR_FILE_ERROR";
                        break;
                    case DownloadManager.ERROR_HTTP_DATA_ERROR:
                        reasonText = "ERROR_HTTP_DATA_ERROR";
                        break;
                    case DownloadManager.ERROR_INSUFFICIENT_SPACE:
                        reasonText = "ERROR_INSUFFICIENT_SPACE";
                        break;
                    case DownloadManager.ERROR_TOO_MANY_REDIRECTS:
                        reasonText = "ERROR_TOO_MANY_REDIRECTS";
                        break;
                    case DownloadManager.ERROR_UNHANDLED_HTTP_CODE:
                        reasonText = "ERROR_UNHANDLED_HTTP_CODE";
                        break;
                    case DownloadManager.ERROR_UNKNOWN:
                        reasonText = "ERROR_UNKNOWN";
                        break;
                }
                break;
            case DownloadManager.STATUS_PAUSED:
                statusText = "STATUS_PAUSED";
                switch (reason) {
                    case DownloadManager.PAUSED_QUEUED_FOR_WIFI:
                        reasonText = "PAUSED_QUEUED_FOR_WIFI";
                        break;
                    case DownloadManager.PAUSED_UNKNOWN:
                        reasonText = "PAUSED_UNKNOWN";
                        break;
                    case DownloadManager.PAUSED_WAITING_FOR_NETWORK:
                        reasonText = "PAUSED_WAITING_FOR_NETWORK";
                        break;
                    case DownloadManager.PAUSED_WAITING_TO_RETRY:
                        reasonText = "PAUSED_WAITING_TO_RETRY";
                        break;
                }
                break;
            case DownloadManager.STATUS_PENDING:
                idDownloadRunning = true;
                //btnPlay.setClickable(false);
                statusText = "STATUS_PENDING";
                break;
            case DownloadManager.STATUS_RUNNING:
                statusText = "STATUS_RUNNING";
                break;
            case DownloadManager.STATUS_SUCCESSFUL:
                statusText = "STATUS_SUCCESSFUL";
                reasonText = "Filename:\n" + filename;
                break;
        }


    }


    private void startpdf() {

        try {

            String pdfFilename = filePDD.getAbsolutePath();
            Intent intentShareFile = new Intent(Intent.ACTION_VIEW);
            File fileWithinMyDir = new File(pdfFilename);
            /*if (fileWithinMyDir.exists()) {
                Uri imageUri = FileProvider.getUriForFile(
                        mContex,
                        "com.baf.musafir.phoenix.provider", new File(pdfFilename));

                intentShareFile.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intentShareFile.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intentShareFile.setDataAndType(imageUri, "application/pdf");
                startActivity(intentShareFile);

            }*/

        } catch (Exception ex) {
            ex.toString();
        }


    }
    public void BACK(View v) {
        this.finish();
    }
    public void RELOAD(View v) {
        webView.reload();
    }


    private void changeFont() {

        header.setTypeface(tf);


    }

}
