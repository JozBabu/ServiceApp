package com.essensol.serviceapp.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.essensol.serviceapp.R;
import com.essensol.serviceapp.Utility.ToolBar;
import com.essensol.serviceapp.Utility.Utils;
import com.google.android.gms.maps.model.LatLng;

public class ServiceDetails extends ToolBar implements LocationListener {

    Chronometer focus;
    Button submitbtn;
    int flag=0;
    TextView title;
    double lng=0;
    private LatLng latLng;
    String valuess;
    private LocationManager locationManager;
    double lat=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_service_details, contentFrameLayout);

        android.support.v7.widget.Toolbar tb= getToolBar();
        title =tb.findViewById(R.id.appname);
        title.setText("Service Details");

         focus = (Chronometer) findViewById(R.id.chronometer1);
         submitbtn=(Button)findViewById(R.id.submitbtn);




        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag==0) {
                    focus.start();
                    flag=1;
                    submitbtn.setText("Job SignOut");
                    Utils.ShowCustomToast(valuess,ServiceDetails.this);
                }
                else
                {
                    focus.setBase(SystemClock.elapsedRealtime());
                    focus.stop();
                    flag=0;
                    Intent i = new Intent(ServiceDetails.this,WorkReport.class);
                    startActivity(i);
                    Utils.ShowCustomToast(valuess,ServiceDetails.this);
                }

            }
        });

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


        if (ActivityCompat.checkSelfPermission(ServiceDetails.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(ServiceDetails.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ServiceDetails.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION},1);
            return;
        }else{
            Location location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
            onLocationChanged(location);
        }
        Log.e("Vluesssss","  "+valuess);
        Log.e("lat&lng","  "+lat+lng);
    }


    @Override
    public void onLocationChanged(Location location) {
        lat=location.getLatitude();
        lng=location.getLongitude();
        valuess=String.valueOf(lat+","+lng);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
