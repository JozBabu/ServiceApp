package com.essensol.serviceapp.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.essensol.serviceapp.Fragments.PendingTab;
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
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceDetails extends ToolBar implements LocationListener {

    Chronometer focus;
    Button submitbtn;
    int flag = 0;
    LinearLayout parent_layout;
    TextView title;
    double lng = 0;
    private LocationManager locationManager;
    double lat = 0;
     String ServiceId;
    SharedPreferences sp;
    Api_interface api_interface;
    private String JobStatus;
    TextView cuatmosername, date, jobNo, details, cust_mob, location_details, shedule_date;
    private String provider;
    String city;
    String iostatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_service_details, contentFrameLayout);

        android.support.v7.widget.Toolbar tb = getToolBar();
        title = tb.findViewById(R.id.appname);
        title.setText("Service Details");

        focus = (Chronometer) findViewById(R.id.chronometer1);
        submitbtn = (Button) findViewById(R.id.submitbtn);

        cuatmosername = (TextView) findViewById(R.id.cuatmosername);
        date = (TextView) findViewById(R.id.date);
        jobNo = (TextView) findViewById(R.id.jobNo);
        details = (TextView) findViewById(R.id.details);
        cust_mob = (TextView) findViewById(R.id.cust_mob);
        location_details = (TextView) findViewById(R.id.location_details);
        shedule_date = (TextView) findViewById(R.id.shedule_date);

        parent_layout=(LinearLayout)findViewById(R.id.parent_layout);

        Bundle bundle= getIntent().getExtras();
        assert bundle != null;
        ServiceId=bundle.getString("ServiceId");


        //Api Interface
        api_interface = ApiClient.getRetrofit().create(Api_interface.class);

        GetServiceDetails();


        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("JobStatus",""+JobStatus);

                if(JobStatus.equalsIgnoreCase("false")){

                    iostatus="true";
                    JobSignInSignOut();
                    Intent intent=new Intent(ServiceDetails.this,ServiceDetails.class);
                    intent.putExtra("ServiceId",ServiceId);
                    startActivity(intent);
                }
                else if (JobStatus.equalsIgnoreCase("true")){

                    iostatus="false";
                    JobSignInSignOut();
                    Intent intent=new Intent(ServiceDetails.this,WorkReport.class);
                    startActivity(intent);
                }

              //  JobSignInSignOut();

            }
        });

        Criteria criteria = new Criteria();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        provider = locationManager.getBestProvider(criteria, true);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        else {
            Location location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
            onLocationChanged(location);
        }

        Log.e("Location","  "+lat+lng);
    }

    @Override
    public void onLocationChanged(Location location) {
        if(location!= null) {
            lat = location.getLatitude();
            lng = location.getLongitude();

            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(this, Locale.getDefault());

            try {
                addresses = geocoder.getFromLocation(lat, lng, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

                city= addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
               // city = addresses.get(0).getLocality();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
        String uid= sp.getString(_CONSTANTS.UserId, null);

        Bundle bundle= getIntent().getExtras();
        assert bundle != null;
        String JobId=bundle.getString("ServiceId");

        api_interface.JobInOut(staffid,JobId,city,lat,lng,"M",uid,iostatus).enqueue(new Callback<JobSignInSignOutResponse>() {
            @Override
            public void onResponse(Call<JobSignInSignOutResponse> call, Response<JobSignInSignOutResponse> response) {

                if (response.isSuccessful() && response.code() == 200) {

                    if (response.body().getResponseCode().equalsIgnoreCase("0")) {


                        List<JobSignInSignOutResponse.Result> responseResult = response.body().getResult();

                        for (int i = 0; i < responseResult.size(); i++) {



                                Utils.setSnackBar(parent_layout,responseResult.get(i).getMsg());








                        }
                    }

                }

            }

            @Override
            public void onFailure(Call<JobSignInSignOutResponse> call, Throwable t) {

            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ServiceDetails.this,PendingTab.class);
        startActivity(intent);
    }
}
