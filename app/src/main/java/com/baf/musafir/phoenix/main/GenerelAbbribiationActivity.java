package com.baf.musafir.phoenix.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.baf.musafir.phoenix.R;
import com.baf.musafir.phoenix.adapter.AbbrAdapter;
import com.baf.musafir.phoenix.parser.AbbriviationListParser;

import java.io.IOException;
import java.io.InputStream;


public class GenerelAbbribiationActivity extends Activity {
    private Context mContext;
    private AbbrAdapter abbrAdapter;
    public EditText mobile_no_search;
    private ListView abbr_list;
    private TextView topbar;
    private String Header = "103 ATTU";

    private String text;
    private String respones_results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_geabbr);
        mContext = this;
        loadAssetData();
        initUI();
    }
    public void HOME(View v) {
        this.finish();
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);

    }
    private void initUI() {
        topbar = (TextView) findViewById(R.id.topbar);
        topbar.setText(Header);
        mobile_no_search = (EditText) findViewById(R.id.mobile_no_search);
        abbr_list = (ListView) findViewById(R.id.abbr_list);
        abbrAdapter = new AbbrAdapter(this);
        abbr_list.setAdapter(abbrAdapter);

        // TextFilter
        abbr_list.setTextFilterEnabled(true);

        mobile_no_search.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

                if (count < before) {
                    // We're deleting char so we need to reset the adapter data
                    abbrAdapter.resetData();
                }

                abbrAdapter.getFilter().filter(s.toString());

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    /*******************************
     * Load Data From Asset Folder
     ***************/
    private void loadAssetData() {
        try {
            InputStream is = getAssets().open("abbrfile.txt");

            // We guarantee that the available method returns the total
            // size of the asset... of course, this does mean that a single
            // asset can't be more than 2 gigs.
            int size = is.available();

            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            // Convert the buffer into a string.
            text = new String(buffer);
            Log.i("Hello ", text);

        } catch (IOException e) {
            // Should never happen!
            throw new RuntimeException(e);
        }

        themeList(text);

    }

    private void themeList(final String url_string) {

        final Thread d = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    respones_results = url_string;
                    if (AbbriviationListParser.connect(getApplicationContext(),
                            respones_results))
                        ;

                } catch (final Exception e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        try {


                        } catch (Exception e) {
                        }
                    }

                });

            }
        });

        d.start();

    }
}
