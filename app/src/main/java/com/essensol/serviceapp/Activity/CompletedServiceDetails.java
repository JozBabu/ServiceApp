package com.essensol.serviceapp.Activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.essensol.serviceapp.R;
import com.essensol.serviceapp.RetrofitUtilits.ApiClient;
import com.essensol.serviceapp.RetrofitUtilits.Api_interface;
import com.essensol.serviceapp.RetroftResponseClasses.ServiceDetailsResponse;
import com.essensol.serviceapp.Utility.ToolBar;
import com.essensol.serviceapp.Utility._CONSTANTS;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompletedServiceDetails extends ToolBar {

    TextView title;
    TextView cuatmosername,date,jobNo,details,cust_mob,location_details,shedule_date;
    Api_interface api_interface;
    SharedPreferences sp;
    String ServiceId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_completed_servicedetails, contentFrameLayout);

        android.support.v7.widget.Toolbar tb= getToolBar();
        title =tb.findViewById(R.id.appname);
        title.setText("Service Details");

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

        GetCompletedServiceDetails();

    }

    public void GetCompletedServiceDetails()
    {
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

                        }
                    }

                }
            }
            @Override
            public void onFailure(Call<ServiceDetailsResponse> call, Throwable t) {

            }
        });
    }



}
