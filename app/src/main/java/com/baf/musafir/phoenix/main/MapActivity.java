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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.baf.musafir.phoenix.R;
import com.baf.musafir.phoenix.databse.DataBaseHelper;
import com.baf.musafir.phoenix.holder.CoordinateVector;
import com.baf.musafir.phoenix.holder.NavaidVector;
import com.baf.musafir.phoenix.model.CoordinateModel;
import com.baf.musafir.phoenix.model.FlgHourModel;
import com.baf.musafir.phoenix.model.NavAidModel;
import com.baf.musafir.phoenix.util.AppConstant;
import com.baf.musafir.phoenix.util.CoordinateConvert;
import com.baf.musafir.phoenix.util.StringUtility;
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
    List<String> mapTypeList = new ArrayList<String>();
    private Spinner type_spinner;
    static LatLng LAT_LONG = null;
    private LinearLayout li_live_data;
    private LinearLayout li_all_data;
    private LinearLayout li_nav_aid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_map);
        mContext=this;
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
     This callback is triggered when the map is ready to be used.This is where we can add markers or lines, add listeners or move the camera.In this case, we just add a marker near Sydney, Australia.If Google Play services is not installed on the device, the user will be prompted to install it inside the SupportMapFragment. This method will only be triggered once the user has installed Google Play services and returned to the app.
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
        li_live_data=(LinearLayout) findViewById(R.id.li_live_data);
        li_all_data=(LinearLayout) findViewById(R.id.li_all_data);
        li_nav_aid=(LinearLayout) findViewById(R.id.li_nav_aid);
        li_live_data.setOnClickListener(this);
        li_all_data.setOnClickListener(this);
        li_nav_aid.setOnClickListener(this);


    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.li_live_data:
                drawliveMarker();
                drawPolygon();
                break;
            case R.id.li_all_data:
                drawAllMarker();
                drawPolygon();
                break;
            case R.id.li_nav_aid:
                drawNavaidMarker();
                drawPolygon();
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
                .target(new LatLng(23.8103,90.4125)) // Sets the center of the map
                .tilt(20) // Sets the tilt of the camera to 20 degrees
                .zoom(7) // Sets the zoom
                .bearing(0) // Sets the orientation of the camera to east
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }


    private void drawAllMarker() {
        mMap.clear();
        // Creating all marker beacon
        for (int i = 0; i < CoordinateVector.getAllCoordinatelist().size(); i++) {
            CoordinateModel query = CoordinateVector.getAllCoordinatelist().elementAt(i);
             double latAll = 0;
            double longAll = 0;
            //converting DMS to latlong

            latAll=Double.parseDouble(CoordinateConvert.dmdtoLatlong(query.getLatitude()));
            longAll=Double.parseDouble(CoordinateConvert.dmdtoLatlong(query.getLongitude()));
            Timber.i("lat Original     " + query.getLatitude());
            Timber.i("lat Original     " + query.getLongitude());
            Timber.i("lat map     " + latAll);
            Timber.i("lat map     " + longAll);
          MarkerOptions markerOptions=null;
         markerOptions = new MarkerOptions().icon(BitmapDescriptorFactory
                .fromResource(R.drawable.location));

        // Setting latitude and longitude for the marker
        markerOptions.position(new LatLng((latAll),longAll));
        mMap.addMarker(markerOptions.title(query.getPlaces()));

        }

        // Creating selected marker beacon
        for(int i =0; i<myList.size(); i++)  {

            MarkerOptions markerOptions=null;
            markerOptions = new MarkerOptions().icon(BitmapDescriptorFactory
                    .fromResource(R.drawable.location_live));

            // Setting latitude and longitude for the marker
            markerOptions.position(new LatLng(StringUtility.getlatitude(myList.get(i)),StringUtility.getlongitude(myList.get(i))));
            mMap.addMarker(markerOptions.title(StringUtility.getPlaces(myList.get(i))));
        }


    }
    private void drawliveMarker() {
        mMap.clear();
        // Adding marker on the Google Map
        for(int i =0; i<myList.size(); i++)  {

            MarkerOptions markerOptions=null;
            markerOptions = new MarkerOptions().icon(BitmapDescriptorFactory
                    .fromResource(R.drawable.location_live));

            // Setting latitude and longitude for the marker
            markerOptions.position(new LatLng(StringUtility.getlatitude(myList.get(i)),StringUtility.getlongitude(myList.get(i))));
            mMap.addMarker(markerOptions.title(StringUtility.getPlaces(myList.get(i)))).showInfoWindow();

        }
    }


    private void drawNavaidMarker() {
        mMap.clear();
        // Adding marker on the Google Map
        for(int i =0; i<myList.size(); i++)  {

            MarkerOptions markerOptions=null;
            markerOptions = new MarkerOptions().icon(BitmapDescriptorFactory
                    .fromResource(R.drawable.location_live));

            // Setting latitude and longitude for the marker
            markerOptions.position(new LatLng(StringUtility.getlatitude(myList.get(i)),StringUtility.getlongitude(myList.get(i))));
            mMap.addMarker(markerOptions.title(StringUtility.getPlaces(myList.get(i)))).showInfoWindow();

        }
        // Adding marker on the Google Map
        for(int i = 0; i< NavaidVector.getAllNavaidlist().size(); i++)  {
            NavAidModel query = NavaidVector.getAllNavaidlist().elementAt(i);

            double lat_nav = 0;
            double long_nav = 0;
            //converting DMS to latlong

            lat_nav=Double.parseDouble(CoordinateConvert.dmdtoLatlong(query.getLatitude()));
            long_nav=Double.parseDouble(CoordinateConvert.dmdtoLatlong(query.getLongitude()));

            Timber.i("lat Navaid     " + lat_nav);
            Timber.i("lat getType     " + query.getType());
            Timber.i("long Navaid     " + long_nav);
            MarkerOptions markerOptions=null;
            if(query.getType_code().equalsIgnoreCase("1")){
                markerOptions = new MarkerOptions().icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.dvor));
            }else if(query.getType_code().equalsIgnoreCase("2")){
                markerOptions = new MarkerOptions().icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.dme));
            }else if(query.getType_code().equalsIgnoreCase("3")){
                markerOptions = new MarkerOptions().icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.ndb));
            }else if(query.getType_code().equalsIgnoreCase("4")){
                markerOptions = new MarkerOptions().icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.ils_llz));
            }else if(query.getType_code().equalsIgnoreCase("5")){
                markerOptions = new MarkerOptions().icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.ils_gp));
            }else if(query.getType_code().equalsIgnoreCase("6")){
                markerOptions = new MarkerOptions().icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.mm));
            }else if(query.getType_code().equalsIgnoreCase("7")){
                markerOptions = new MarkerOptions().icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.om));
            }else if(query.getType_code().equalsIgnoreCase("8")){
                markerOptions = new MarkerOptions().icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.ol));
            }else {
                markerOptions = new MarkerOptions().icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.location_live));
            }

            markerOptions.position(new LatLng(long_nav,lat_nav));
            mMap.addMarker(markerOptions.title(query.getType())).showInfoWindow();

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(long_nav,lat_nav)) // Sets the center of the map
                    .tilt(20) // Sets the tilt of the camera to 20 degrees
                    .zoom(24) // Sets the zoom
                    .bearing(0) // Sets the orientation of the camera to east
                    .build();
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        }
    }



}
