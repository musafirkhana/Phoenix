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
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.baf.musafir.phoenix.R;
import com.baf.musafir.phoenix.databse.DataBaseHelper;
import com.baf.musafir.phoenix.util.AppConstant;
import com.baf.musafir.phoenix.util.StringUtility;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
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

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission_group.CAMERA;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

        private GoogleMap mMap;
    ArrayList<String> myList;
    List<String> mapTypeList = new ArrayList<String>();
    private Spinner type_spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_map);
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

        type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(type_spinner.getSelectedItem().toString().equalsIgnoreCase("HYBRID")){
                    mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                }else if(type_spinner.getSelectedItem().toString().equalsIgnoreCase("NORMAL")){
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
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


        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        drawPolygon();


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
        //polygonOptions.add(new LatLng[]{23.8103,90.4125,adelaide,brisbane});
        polygonOptions.addAll(coordinates);
        polygonOptions.strokeColor(ContextCompat.getColor(this,android.R.color.holo_orange_dark));
        polygonOptions.fillColor(ContextCompat.getColor(this,android.R.color.holo_orange_light));
        mMap.addPolygon(polygonOptions);
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(23.8103,90.4125)) // Sets the center of the map
                .tilt(20) // Sets the tilt of the camera to 20 degrees
                .zoom(7) // Sets the zoom
                .bearing(0) // Sets the orientation of the camera to east
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
//        mMap.addMarker(new MarkerOptions().position(new LatLng(StringUtility.getlatitude(myList.get(0)),StringUtility.getlongitude(myList.get(0)))).title("Dhaka").icon(BitmapDescriptorFactory
//                .fromResource(R.drawable.location))).showInfoWindow();
//        mMap.addMarker(new MarkerOptions().position(new LatLng(24.7471,90.4203)).title("Mymensingh").icon(BitmapDescriptorFactory
//                .fromResource(R.drawable.location)));
//        mMap.addMarker(new MarkerOptions().position(new LatLng(24.8949,91.8687)).title("Syhlet").icon(BitmapDescriptorFactory
//                .fromResource(R.drawable.location)));


    }


}
