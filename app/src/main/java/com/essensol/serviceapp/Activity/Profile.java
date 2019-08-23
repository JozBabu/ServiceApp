package com.essensol.serviceapp.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.essensol.serviceapp.R;
import com.essensol.serviceapp.RetrofitUtilits.ApiClient;
import com.essensol.serviceapp.RetrofitUtilits.Api_interface;
import com.essensol.serviceapp.RetroftResponseClasses.HomeResponse;
import com.essensol.serviceapp.RetroftResponseClasses.ProfileResponse;
import com.essensol.serviceapp.Utility.ToolBar;
import com.essensol.serviceapp.Utility._CONSTANTS;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile extends ToolBar {

    TextView title,employeName,empDesignation,Emp_code,emp_contact,emp_email,emp_address;
    SharedPreferences sp;
    Api_interface api_interface;
    ImageView profpic,bgimage;
    private Context context = Profile.this;
    ShimmerFrameLayout shimmer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_profile, contentFrameLayout);

        android.support.v7.widget.Toolbar tb= getToolBar();
        title =tb.findViewById(R.id.appname);
        title.setText("Profile");

        profpic=(ImageView) findViewById(R.id.profpic);
        bgimage=(ImageView) findViewById(R.id.bgimage);

        employeName =(TextView) findViewById(R.id.employeName);
        empDesignation =(TextView) findViewById(R.id.empDesignation);
        Emp_code =(TextView) findViewById(R.id.staffcode);
        emp_contact =(TextView) findViewById(R.id.emp_contact);
        emp_email =(TextView) findViewById(R.id.emp_email);
        emp_address =(TextView) findViewById(R.id.emp_address);

        shimmer=(ShimmerFrameLayout) findViewById(R.id.frame);


        //Api Interface
        api_interface= ApiClient.getRetrofit().create(Api_interface.class);


        shimmer.startShimmer();
        //Profile Service Call
        GetProfileDetaails();
    }


    public void GetProfileDetaails()
    {
        sp = getSharedPreferences("UserLog",MODE_PRIVATE);
        String uid= sp.getString(_CONSTANTS.UserId, null);
        String staffid= sp.getString(_CONSTANTS.StaffId, null);

        api_interface.Profile(staffid).enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {

                if(response.isSuccessful()&&response.code()==200) {

                    if (response.body().getResponseCode().equalsIgnoreCase("0")) {

                        List<ProfileResponse.Result> responseResult = response.body().getResult();

                        for (int i = 0; i < responseResult.size(); i++) {

                            employeName.setText(responseResult.get(i).getStaffName());
                            empDesignation.setText(responseResult.get(i).getDesignationName());
                            Emp_code .setText(responseResult.get(i).getStaffCode());
                            emp_contact .setText(responseResult.get(i).getMobileNo());
                            emp_email .setText(responseResult.get(i).getEmailId());
                            emp_address .setText(responseResult.get(i).getAddress());

                            //Employee Pic
                            String url=("http://192.168.1.15:1212"+responseResult.get(i).getProfileImage());

                            if (!Profile.this.isFinishing()) {

                            Glide
                                    .with(context)
                                    .load(url)
                                    .into(profpic);

                            Glide
                                    .with(context)
                                    .load(url)
                                    .into(bgimage);
                            }
                        }

                        shimmer.stopShimmer();
                        shimmer.setVisibility(View.GONE);
                    }
                }

            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Profile.this,Home.class);
        startActivity(intent);
    }
}
