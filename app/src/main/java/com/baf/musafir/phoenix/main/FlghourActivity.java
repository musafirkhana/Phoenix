package com.baf.musafir.phoenix.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import com.baf.musafir.phoenix.R;
import com.baf.musafir.phoenix.adapter.FlhhourAdapter;
import com.baf.musafir.phoenix.databse.DataBaseUtility;


public class FlghourActivity extends Activity {
private Context mContext;
    private ListView hrslistview;
    private FlhhourAdapter flhhourAdapter;
    DataBaseUtility dataBaseUtility;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_flghrs);
        mContext=this;
        dataBaseUtility=new DataBaseUtility();
        dataBaseUtility.getFlghourData(this);
        initUI();

    }

    private void initUI(){
        hrslistview = (ListView) findViewById(R.id.hrslistview);
        flhhourAdapter = new FlhhourAdapter(this);
        hrslistview.setAdapter(flhhourAdapter);

        hrslistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //PabxListModel query = AllPabxListVector.getAllPabxlist().elementAt(position);


            }
        });
    }
    public void BACK(View v){
        this.finish();

    }

    public void PLUS(View v) {
        this.finish();
        Intent intent=new Intent(this, AddFlghourActivity.class);
        startActivity(intent);

    }

}
