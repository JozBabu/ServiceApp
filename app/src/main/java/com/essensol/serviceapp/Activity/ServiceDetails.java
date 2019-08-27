package com.essensol.serviceapp.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.essensol.serviceapp.RetrofitUtilits.ApiClient;
import com.essensol.serviceapp.RetrofitUtilits.Api_interface;
import com.essensol.serviceapp.RetroftResponseClasses.CompletedServiceResponse;
import com.essensol.serviceapp.RetroftResponseClasses.JobSignInSignOutResponse;
import com.essensol.serviceapp.RetroftResponseClasses.ServiceDetailsResponse;
import com.essensol.serviceapp.Utility.ToolBar;
import com.essensol.serviceapp.Utility.Utils;
import com.essensol.serviceapp.Utility._CONSTANTS;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceDetails extends ToolBar implements LocationListener {

    Chronometer focus;
    Button submitbtn;
    int flag=0;
    TextView title;
    double lng=0;
    private LocationManager locationManager;
    double lat=0;
    String ServiceId;
    SharedPreferences sp;
    Api_interface api_interface;
    private String JobStatus;
    TextView cuatmosername,date,jobNo,details,cust_mob,location_details,shedule_date;

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

        cuatmosername=(TextView)findViewById(R.id.cuatmosername);
        date=(TextView)findViewById(R.id.date);
        jobNo=(TextView)findViewById(R.id.jobNo);
        details=(TextView)findViewById(R.id.details);
        cust_mob=(TextView)findViewById(R.id.cust_mob);
        location_details=(TextView)findViewById(R.id.location_details);
        shedule_date=(TextView)findViewById(R.id.shedule_date);


        Bundle bundle= getIntent().getExtras();
        ServiceId=bundle.getString("ServiceId");


        //Api Interface
        api_interface= ApiClient.getRetrofit().create(Api_interface.class);

        GetServiceDetails();


        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    JobSignInSignOut();

            }
        });


//        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//
//
//        if (ActivityCompat.checkSelfPermission(ServiceDetails.this, Manifest.permission.ACCESS_FINE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED
//                && ActivityCompat.checkSelfPermission(ServiceDetails.this, Manifest.permission.ACCESS_COARSE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(ServiceDetails.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION},1);
//            return;
//        }else{
//            Location location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
//            onLocationChanged(location);
//        }

    }

    @Override
    public void onLocationChanged(Location location) {
//        lat=location.getLatitude();
//        lng=location.getLongitude();
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

    public void GetServiceDetails(){

        sp = getSharedPreferences("UserLog",MODE_PRIVATE);
        String uid= sp.getString(_CONSTANTS.UserId, null);
        String staffid= sp.getString(_CONSTANTS.StaffId, null);
        String brid= sp.getString(_CONSTANTS.BrId, null);

        api_interface.ServiceDetails(staffid,brid,ServiceId).enqueue(new Callback<ServiceDetailsResponse>() {
            @Override
            public void onResponse(Call<ServiceDetailsResponse> call, Response<ServiceDetailsResponse> response) {


                if (response.isSuccessful() && response.code() == 200) {

                    if (response.body().getResponseCode().equalsIgnoreCase("0")) {


                        List<ServiceDetailsResponse.Result> responseResult = response.body().getResult();

                        for (int i = 0; i < responseResult.size(); i++) {
                            cuatmosername.setText(responseResult.get(i).getCustomerName());
                            date.setText(responseResult.get(i).getServiceDate());
                            jobNo.setText(responseResult.get(i).getJobNo());
                            details.setText(responseResult.get(i).getProblemDetails());
                            cust_mob.setText(responseResult.get(i).getContactNo());
                            location_details.setText(responseResult.get(i).getAddress());
                            shedule_date.setText(responseResult.get(i).getAssignDate());

                            JobStatus=responseResult.get(i).getJobStatus();
                            if(JobStatus.equalsIgnoreCase("false")){

                                submitbtn.setText("Job SignIn");
                            }
                            else if(JobStatus.equalsIgnoreCase("true")){

                                submitbtn.setText("Job SignOut");
                            }


                        }
                    }

                }
            }
            @Override
            public void onFailure(Call<ServiceDetailsResponse> call, Throwable t) {

            }
        });

    }

    public void JobSignInSignOut(){

        sp = getSharedPreferences("UserLog",MODE_PRIVATE);
        String staffid= sp.getString(_CONSTANTS.StaffId, null);
        String brid= sp.getString(_CONSTANTS.BrId, null);

        Log.e("latitute "," Longitude "+lat+","+lng);



//        api_interface.JobInOut(staffid,brid,lng).enqueue(new Callback<JobSignInSignOutResponse>() {
//            @Override
//            public void onResponse(Call<JobSignInSignOutResponse> call, Response<JobSignInSignOutResponse> response) {
//
//
//            }
//
//            @Override
//            public void onFailure(Call<JobSignInSignOutResponse> call, Throwable t) {
//
//            }
//        });


    }


}
