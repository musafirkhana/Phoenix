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
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.baf.musafir.phoenix.R;
import com.baf.musafir.phoenix.databse.DataBaseHelper;
import com.baf.musafir.phoenix.util.AppConstant;
import com.baf.musafir.phoenix.util.StringUtility;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
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
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        drawPolygon();


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
