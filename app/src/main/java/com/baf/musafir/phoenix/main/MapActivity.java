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
import android.graphics.Point;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.baf.musafir.phoenix.R;
import com.baf.musafir.phoenix.databse.DataBaseHelper;
import com.baf.musafir.phoenix.databse.DataBaseUtility;
import com.baf.musafir.phoenix.holder.CoordinateVector;
import com.baf.musafir.phoenix.holder.NavaidVector;
import com.baf.musafir.phoenix.model.CoordinateModel;
import com.baf.musafir.phoenix.model.FlgHourModel;
import com.baf.musafir.phoenix.model.NavAidModel;
import com.baf.musafir.phoenix.util.AppConstant;
import com.baf.musafir.phoenix.util.CoordinateConvert;
import com.baf.musafir.phoenix.util.StringUtility;
import com.baf.musafir.phoenix.util.ToastUtil;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import timber.log.Timber;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission_group.CAMERA;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback , View.OnClickListener {

    private GoogleMap mMap;
    private Context mContext;
    ArrayList<String> myList;
    private double cameraZoomLat=23.8434;
    private double cameraZoomLong=90.4029;
    List<String> mapTypeList = new ArrayList<String>();
    private Spinner type_spinner;
    private DataBaseUtility dataBaseUtility;
    List<Marker> allMarkerOptions = new ArrayList<>();
    List<Marker> liveMarker = new ArrayList<>();
    List<Marker> dvorMarker = new ArrayList<>();
    List<Marker> dmeMarker = new ArrayList<>();
    List<Marker> ndbMarker = new ArrayList<>();
    List<Marker> ilsMarker = new ArrayList<>();
    List<Marker> mmMarker = new ArrayList<>();
    List<Marker> vorMarker = new ArrayList<>();
    List<Marker> olMarker = new ArrayList<>();
    List<Marker> omMarker = new ArrayList<>();


    private ToastUtil toastUtil;
    static LatLng LAT_LONG = null;

    private CheckBox chk_dvor;
    private CheckBox chk_all;
    private CheckBox chk_live;
    private CheckBox chk_dme;
    private CheckBox chk_ndb;
    private CheckBox chk_ilss;
    private CheckBox chk_mm;
    private CheckBox chk_vor;
    private CheckBox chk_ol;
    private CheckBox chk_om;

    private ImageView navaid_menu;
    private LinearLayout li_nav_bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_map);
        mContext=this;
        toastUtil=new ToastUtil(this);
        dataBaseUtility = new DataBaseUtility();
        initUI();
        mapTypeList.add("HYBRID");
        mapTypeList.add("NONE");
        mapTypeList.add("NORMAL");
        mapTypeList.add("SATELLITE");
        mapTypeList.add("TERRAIN");
        type_spinner=(Spinner)findViewById(R.id.type_spinner);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
      myList = (ArrayList<String>) getIntent().getSerializableExtra("mylist");
      Log.i("List Value",""+myList.get(0));
      Log.i("getlatitude",""+ StringUtility.getlatitude(myList.get(0)));
      Log.i("getlongitude",""+ StringUtility.getlongitude(myList.get(0)));


    }
    /**
     Manipulates the map once available.
     This callback is triggered when the map is ready to be used.This is where we can add markers or lines, add listeners or move the camera.
     If Google Play services is not installed on the device,
     the user will be prompted to install it inside the SupportMapFragment.
     This method will only be triggered once the user has installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        ArrayAdapter monthSpinner = new ArrayAdapter(this, R.layout.map_style, mapTypeList);
        monthSpinner.setDropDownViewResource(R.layout.map_style);
        type_spinner.setAdapter(monthSpinner);
        type_spinner.setSelection(2);

        type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(type_spinner.getSelectedItem().toString().equalsIgnoreCase("NORMAL")){
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }else if(type_spinner.getSelectedItem().toString().equalsIgnoreCase("HYBRID")){
                    mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                }else if(type_spinner.getSelectedItem().toString().equalsIgnoreCase("NONE")){
                    mMap.setMapType(GoogleMap.MAP_TYPE_NONE);
                }else if(type_spinner.getSelectedItem().toString().equalsIgnoreCase("SATELLITE")){
                    mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                }else if(type_spinner.getSelectedItem().toString().equalsIgnoreCase("TERRAIN")){
                    mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        drawPolygon();


    }

    private void initUI(){

        navaid_menu=(ImageView)findViewById(R.id.navaid_menu);
        li_nav_bar=(LinearLayout)findViewById(R.id.li_nav_bar);

        chk_all=(CheckBox)findViewById(R.id.chk_all);
        chk_live=(CheckBox) findViewById(R.id.chk_live);
        chk_dvor=(CheckBox)findViewById(R.id.chk_dvor);
        chk_dme=(CheckBox)findViewById(R.id.chk_dme);
        chk_ndb=(CheckBox)findViewById(R.id.chk_ndb);
        chk_ilss=(CheckBox)findViewById(R.id.chk_ilss);
        chk_mm=(CheckBox)findViewById(R.id.chk_mm);
        chk_vor=(CheckBox) findViewById(R.id.chk_vor);
        chk_ol=(CheckBox)findViewById(R.id.chk_ol);
        chk_om=(CheckBox)findViewById(R.id.chk_om);

        chk_live.setOnClickListener(this);
        navaid_menu.setOnClickListener(this);
        chk_dvor.setOnClickListener(this);
        chk_dme.setOnClickListener(this);
        chk_mm.setOnClickListener(this);
        chk_dvor.setOnClickListener(this);
        chk_all.setOnClickListener(this);
        chk_ndb.setOnClickListener(this);
        chk_ilss.setOnClickListener(this);
        chk_vor.setOnClickListener(this);
        chk_ol.setOnClickListener(this);
        chk_om.setOnClickListener(this);


    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.chk_live:
                if (chk_live.isChecked()) {
                    drawliveMarker();
                    drawPolygon();
                } else {
                    hideLiveMarker();
                }
                break;
            case R.id.chk_all:
                if (chk_all.isChecked()) {
                    drawAllMarker();
                    drawPolygon();

                } else {
                    hideAllMarker();
                }
                break;
            case R.id.chk_dvor:
                dataBaseUtility.getNevAidDataByType(this,"1");
                if (chk_dvor.isChecked()) {
                    drawDvorMarker();
                } else {
                    hideDvorMarker();
                }
                break;
            case R.id.chk_dme:
                dataBaseUtility.getNevAidDataByType(this,"2");
                if (chk_dme.isChecked()) {
                    drawDmeMarker();
                } else {
                    hideDmeMarker();
                }
                break;
            case R.id.chk_ndb:
                dataBaseUtility.getNevAidDataByType(this,"3");
                if (chk_ndb.isChecked()) {
                    drawNdbMarker();
                } else {
                    hidendbMarker();
                }
                break;

            case R.id.chk_ilss:
                dataBaseUtility.getNevAidDataByType(this,"4");
                if (chk_ilss.isChecked()) {
                    drawilsMarker();
                } else {
                    hideilsMarker();
                }
                break;
            case R.id.chk_mm:
                dataBaseUtility.getNevAidDataByType(this,"6");
                if (chk_mm.isChecked()) {
                    drawmmMarker();
                } else {
                    hidemmMarker();
                }
                break;
            case R.id.chk_vor:
                dataBaseUtility.getNevAidDataByType(this,"9");
                if (chk_vor.isChecked()) {
                    draVorMarker();
                } else {
                    hideVorMarker();
                }
                break;
            case R.id.chk_ol:
                dataBaseUtility.getNevAidDataByType(this,"8");
                if (chk_ol.isChecked()) {
                    drawOlMarker();
                } else {
                    hideOlMarker();
                }
                break;
            case R.id.chk_om:
                dataBaseUtility.getNevAidDataByType(this,"7");
                if (chk_om.isChecked()) {
                    drawOmMarker();
                } else {
                    hideOMMarker();
                }
                break;

            case R.id.navaid_menu:
                if (li_nav_bar.getVisibility() == View.VISIBLE)
                {
                    li_nav_bar.setVisibility(View.GONE);
                }
                else
                {
                    li_nav_bar.setVisibility(View.VISIBLE);
                }
                break;

        }

    }
    public void BACK(View v){
        this.finish();

    }
    private void drawPolygon(){

        List<LatLng> coordinates = new ArrayList<>();
        for(int i =0; i<myList.size(); i++)  {
            coordinates.add(new LatLng(StringUtility.getlatitude(myList.get(i)),
                    StringUtility.getlongitude(myList.get(i))));
        }

        PolygonOptions polygonOptions=new PolygonOptions();
        polygonOptions.addAll(coordinates);
        polygonOptions.strokeColor(ContextCompat.getColor(this,android.R.color.holo_orange_dark));
        polygonOptions.fillColor(ContextCompat.getColor(this,android.R.color.transparent));
        mMap.addPolygon(polygonOptions);
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(cameraZoomLat,cameraZoomLong)) // Sets the center of the map
                .tilt(20) // Sets the tilt of the camera to 20 degrees
                .zoom(7) // Sets the zoom
                .bearing(0) // Sets the orientation of the camera to east
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }


    private void drawAllMarker() {
        allMarkerOptions.clear();
        // Creating all marker beacon
        for (int i = 0; i < CoordinateVector.getAllCoordinatelist().size(); i++) {
            CoordinateModel query = CoordinateVector.getAllCoordinatelist().elementAt(i);
             double latAll = 0;
            double longAll = 0;

            //converting DMS to latlong

            latAll=Double.parseDouble(CoordinateConvert.dmdtoLatlong(query.getLatitude()));
            longAll=Double.parseDouble(CoordinateConvert.dmdtoLatlong(query.getLongitude()));
            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng((latAll),longAll))
                    .title(query.getPlaces()).icon(BitmapDescriptorFactory
                    .fromResource(R.drawable.location)));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng((latAll),longAll)).zoom(12).build();
            mMap.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));
            allMarkerOptions.add(marker);

        }

    }
    private void drawliveMarker() {
        liveMarker.clear();
        // Adding marker on the Google Map
        for(int i =0; i<myList.size(); i++)  {

            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(StringUtility.getlatitude(myList.get(i)),StringUtility.getlongitude(myList.get(i))))
                    .title(StringUtility.getPlaces(myList.get(i))).icon(BitmapDescriptorFactory
                            .fromResource(R.drawable.location_live)));
            liveMarker.add(marker);

        }
    }

    private void drawDvorMarker() {
        dvorMarker.clear();
        // Adding marker on the Google Map
        for(int i = 0; i< NavaidVector.getAllNavaidlist().size(); i++)  {
            NavAidModel query = NavaidVector.getAllNavaidlist().elementAt(i);
            double lat_nav=Double.parseDouble(CoordinateConvert.dmdtoLatlong(query.getLatitude()));
            double long_nav=Double.parseDouble(CoordinateConvert.dmdtoLatlong(query.getLongitude()));
            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(long_nav,lat_nav))
                    .title(query.getFreq()).snippet(query.getAirport()).icon(BitmapDescriptorFactory
                            .fromResource(R.drawable.location)));
            /*CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(cameraZoomLat,cameraZoomLong)).zoom(12).build();
            mMap.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));*/
            dvorMarker.add(marker);
            marker.showInfoWindow();

        }
    }
    private void drawDmeMarker() {
        dmeMarker.clear();
        // Adding marker on the Google Map
        for(int i = 0; i< NavaidVector.getAllNavaidlist().size(); i++)  {
            NavAidModel query = NavaidVector.getAllNavaidlist().elementAt(i);
            double lat_nav=Double.parseDouble(CoordinateConvert.dmdtoLatlong(query.getLatitude()));
            double long_nav=Double.parseDouble(CoordinateConvert.dmdtoLatlong(query.getLongitude()));
            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(long_nav,lat_nav))
                    .title(query.getFreq()).snippet(query.getAirport()).icon(BitmapDescriptorFactory
                            .fromResource(R.drawable.location)));
        /*    CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(cameraZoomLat,cameraZoomLong)).zoom(12).build();
            mMap.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));*/
            dmeMarker.add(marker);

        }
    }
    private void drawNdbMarker() {
        ndbMarker.clear();
        // Adding marker on the Google Map
        for(int i = 0; i< NavaidVector.getAllNavaidlist().size(); i++)  {
            NavAidModel query = NavaidVector.getAllNavaidlist().elementAt(i);
            double lat_nav=Double.parseDouble(CoordinateConvert.dmdtoLatlong(query.getLatitude()));
            double long_nav=Double.parseDouble(CoordinateConvert.dmdtoLatlong(query.getLongitude()));
            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(long_nav,lat_nav))
                    .title(query.getFreq()).snippet(query.getAirport()).icon(BitmapDescriptorFactory
                            .fromResource(R.drawable.location)));
            /*CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(cameraZoomLat,cameraZoomLong)).zoom(12).build();
            mMap.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));*/
            ndbMarker.add(marker);

        }
    }
    private void drawilsMarker() {
        ilsMarker.clear();
        // Adding marker on the Google Map
        for(int i = 0; i< NavaidVector.getAllNavaidlist().size(); i++)  {
            NavAidModel query = NavaidVector.getAllNavaidlist().elementAt(i);
            double lat_nav=Double.parseDouble(CoordinateConvert.dmdtoLatlong(query.getLatitude()));
            double long_nav=Double.parseDouble(CoordinateConvert.dmdtoLatlong(query.getLongitude()));
            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(long_nav,lat_nav))
                    .title(query.getFreq()).snippet(query.getAirport()).icon(BitmapDescriptorFactory
                            .fromResource(R.drawable.location)));

//            CameraPosition cameraPosition = new CameraPosition.Builder()
//                    .target(new LatLng(cameraZoomLat,cameraZoomLong)).zoom(12).build();
//            mMap.animateCamera(CameraUpdateFactory
//                    .newCameraPosition(cameraPosition));
            ilsMarker.add(marker);

            marker.showInfoWindow();

        }/**/
    }
    private void drawmmMarker() {
        mmMarker.clear();
        // Adding marker on the Google Map
        for(int i = 0; i< NavaidVector.getAllNavaidlist().size(); i++)  {
            NavAidModel query = NavaidVector.getAllNavaidlist().elementAt(i);
            double lat_nav=Double.parseDouble(CoordinateConvert.dmdtoLatlong(query.getLatitude()));
            double long_nav=Double.parseDouble(CoordinateConvert.dmdtoLatlong(query.getLongitude()));
            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(long_nav,lat_nav))
                    .title(query.getFreq()).icon(BitmapDescriptorFactory
                            .fromResource(R.drawable.location)));
          /*  CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(cameraZoomLat,cameraZoomLong)).zoom(12).build();
            mMap.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));*/
            mmMarker.add(marker);
            marker.showInfoWindow();

        }
    }
    private void draVorMarker() {
        vorMarker.clear();
        // Adding marker on the Google Map
        for(int i = 0; i< NavaidVector.getAllNavaidlist().size(); i++)  {
            NavAidModel query = NavaidVector.getAllNavaidlist().elementAt(i);
            double lat_nav=Double.parseDouble(CoordinateConvert.dmdtoLatlong(query.getLatitude()));
            double long_nav=Double.parseDouble(CoordinateConvert.dmdtoLatlong(query.getLongitude()));
            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(long_nav,lat_nav))
                    .title(query.getFreq()).snippet(query.getAirport()).icon(BitmapDescriptorFactory
                            .fromResource(R.drawable.location)));
          /*  CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(cameraZoomLat,cameraZoomLong)).zoom(12).build();
            mMap.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));*/
            vorMarker.add(marker);
            marker.showInfoWindow();

        }
    }
    private void drawOlMarker() {
        olMarker.clear();
        // Adding marker on the Google Map
        for(int i = 0; i< NavaidVector.getAllNavaidlist().size(); i++)  {
            NavAidModel query = NavaidVector.getAllNavaidlist().elementAt(i);
            double lat_nav=Double.parseDouble(CoordinateConvert.dmdtoLatlong(query.getLatitude()));
            double long_nav=Double.parseDouble(CoordinateConvert.dmdtoLatlong(query.getLongitude()));
            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(long_nav,lat_nav))
                    .title(query.getFreq()).icon(BitmapDescriptorFactory
                            .fromResource(R.drawable.location)));
            /*CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(cameraZoomLat,cameraZoomLong)).zoom(12).build();
            mMap.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));*/
            olMarker.add(marker);
            marker.showInfoWindow();

        }
    }
    private void drawOmMarker() {
        omMarker.clear();
        // Adding marker on the Google Map
        for(int i = 0; i< NavaidVector.getAllNavaidlist().size(); i++)  {
            NavAidModel query = NavaidVector.getAllNavaidlist().elementAt(i);
            double lat_nav=Double.parseDouble(CoordinateConvert.dmdtoLatlong(query.getLatitude()));
            double long_nav=Double.parseDouble(CoordinateConvert.dmdtoLatlong(query.getLongitude()));
            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(long_nav,lat_nav))
                    .title(query.getFreq()).icon(BitmapDescriptorFactory
                            .fromResource(R.drawable.location)));
           /* CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(cameraZoomLat,cameraZoomLong)).zoom(12).build();
            mMap.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));*/
            omMarker.add(marker);

        }
    }
    public void hideAllMarker(){
        for (Marker marker : allMarkerOptions){
            marker.remove();
        }
    }

    public void hideLiveMarker(){
        for (Marker marker : liveMarker){
            marker.remove();
        }
    }
    public void hideDvorMarker(){
        for (Marker marker : dvorMarker){
            marker.remove();
        }
    }
    public void hideDmeMarker(){
        for (Marker marker : dmeMarker){
            marker.remove();
        }
    }
    public void hidendbMarker(){
        for (Marker marker : ndbMarker){
            marker.remove();
        }
    }
    public void hideilsMarker(){
        for (Marker marker : ilsMarker){
            marker.remove();
        }
    }
    public void hidemmMarker(){
        for (Marker marker : mmMarker){
            marker.remove();
        }
    }
    public void hideVorMarker(){
        for (Marker marker : vorMarker){
            marker.remove();
        }
    }
    public void hideOlMarker(){
        for (Marker marker : olMarker){
            marker.remove();
        }
    }
    public void hideOMMarker(){
        for (Marker marker : omMarker){
            marker.remove();
        }
    }



}
