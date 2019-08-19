package com.essensol.serviceapp.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.essensol.serviceapp.Dialogue.BackButton;
import com.essensol.serviceapp.Dialogue.LogoutDialogue;
import com.essensol.serviceapp.Dialogue.Vehicle_km;
import com.essensol.serviceapp.R;
import com.essensol.serviceapp.RetrofitUtilits.ApiClient;
import com.essensol.serviceapp.RetrofitUtilits.Api_interface;
import com.essensol.serviceapp.RetroftResponseClasses.HomeResponse;
import com.essensol.serviceapp.RetroftResponseClasses.LoginResponse;
import com.essensol.serviceapp.RetroftResponseClasses.WorkSignInSignOutResponse;
import com.essensol.serviceapp.Utility.Utils;
import com.essensol.serviceapp.Utility._CONSTANTS;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.iid.FirebaseInstanceId;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends AppCompatActivity {

    SimpleDraweeView logout_icon;
    TextView name, role, empid, serviceText, serviceCount, sigin, taskText, taskCount,
            productText, productCount, profileText, appname, paymentCount,km_text;
    LinearLayout service, task, productDelivery, paymentCollection, signInbtn,KmEntering;
    ImageView profpic_glide, serviceImg_glide, taskImg_glide, productImg_glide, profileicon_glide;
    private Context context = Home.this;
    Api_interface api_interface;
    SharedPreferences sp;
    ShimmerFrameLayout shimmer;
    private String Status,mode,PunchType;
    String staffid,uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        profpic_glide = (ImageView) findViewById(R.id.profpic);
        serviceImg_glide = (ImageView) findViewById(R.id.serviceImg);
        taskImg_glide = (ImageView) findViewById(R.id.taskImg);
        productImg_glide = (ImageView) findViewById(R.id.productImg);
        profileicon_glide = (ImageView) findViewById(R.id.profileicon);

        logout_icon = (SimpleDraweeView) findViewById(R.id.logout);

        km_text = (TextView) findViewById(R.id.km_text);
        appname = (TextView) findViewById(R.id.appname);
        name = (TextView) findViewById(R.id.name);
        role = (TextView) findViewById(R.id.role);
        empid = (TextView) findViewById(R.id.empid);
        serviceText = (TextView) findViewById(R.id.serviceText);
        serviceCount = (TextView) findViewById(R.id.serviceCount);
        sigin = (TextView) findViewById(R.id.sigin);
        taskText = (TextView) findViewById(R.id.taskText);
        taskCount = (TextView) findViewById(R.id.taskCount);
        productText = (TextView) findViewById(R.id.productText);
        productCount = (TextView) findViewById(R.id.productCount);
        profileText = (TextView) findViewById(R.id.profileText);
        paymentCount = (TextView) findViewById(R.id.paymentCount);

        shimmer = (ShimmerFrameLayout) findViewById(R.id.frame);

        service = (LinearLayout) findViewById(R.id.service);
        task = (LinearLayout) findViewById(R.id.task);
        productDelivery = (LinearLayout) findViewById(R.id.productDelivery);
        paymentCollection = (LinearLayout) findViewById(R.id.paymentCollection);
        signInbtn = (LinearLayout) findViewById(R.id.signInbtn);
        KmEntering = (LinearLayout) findViewById(R.id.KmEntering);

        //Initialising Api Interface
        api_interface = ApiClient.getRetrofit().create(Api_interface.class);

        //Simpledrawerview Image loading
        ImageRequest imageRequest1 = ImageRequestBuilder.newBuilderWithResourceId(R.drawable.logouticon).build();
        logout_icon.setImageURI(imageRequest1.getSourceUri());


        //Glide Image Loading
        int serviceicon = R.drawable.service_icon;
        int list = R.drawable.list;
        int user = R.drawable.purse;
        int product = R.drawable.shopping_bag;


        //Service Icon
        Glide
                .with(context)
                .load(serviceicon)
                .into(serviceImg_glide);

        //Task Icon
        Glide
                .with(context)
                .load(list)
                .into(taskImg_glide);

        //Product Icon
        Glide
                .with(context)
                .load(product)
                .into(productImg_glide);

        //Profile Icon
        Glide
                .with(context)
                .load(user)
                .into(profileicon_glide);


        //Fonts
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/MontserratBold.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getAssets(), "fonts/MontserratMedium.ttf");
        name.setTypeface(custom_font);
        role.setTypeface(custom_font2);
        empid.setTypeface(custom_font2);
        serviceText.setTypeface(custom_font2);
        serviceCount.setTypeface(custom_font2);
        sigin.setTypeface(custom_font2);
        taskText.setTypeface(custom_font2);
        taskCount.setTypeface(custom_font2);
        productText.setTypeface(custom_font2);
        productCount.setTypeface(custom_font2);
        profileText.setTypeface(custom_font2);
        appname.setTypeface(custom_font);
        paymentCount.setTypeface(custom_font);

        //Shimmer
        shimmer.startShimmer();

        //SharedPreference
        sp = getSharedPreferences("UserLog",MODE_PRIVATE);
        uid= sp.getString(_CONSTANTS.UserId, null);
        staffid= sp.getString(_CONSTANTS.StaffId, null);

        //Home service Calling
        HomeService();

        //ProfilePic Click
        profpic_glide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Profile.class);
                startActivity(intent);
            }
        });

        //Logout Click
        logout_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Clicked","Logout");


                SharedPreferences sp = getSharedPreferences("UserLog",MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean("LoggedUser",false);
                editor.apply();

                Logout_dialogue();

            }
        });

        //SignIn Click
        signInbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Status.equalsIgnoreCase("S")) {
                    SignInService();
                }
                else if (Status.equalsIgnoreCase("E"))
                {
                    Utils.ShowCustomToast("You Need To End Ride to SignOut",Home.this);
                }
            }
        });


        //start reading button
        KmEntering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mode.equalsIgnoreCase("signOutt"))
                {
                    dialogue_box();
                }
                else
                {
                    Utils.ShowCustomToast("Please SignIn.",Home.this);
                }

            }
        });

        //Click to Service Page
        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Service.class);
                startActivity(intent);
            }
        });

        //Click to Task Page
        task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Task.class);
                startActivity(intent);
            }
        });

        //Click to Product Delivery Page
        productDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Product_Delivery.class);
                startActivity(intent);
            }
        });

        //Click to Profile Page
        paymentCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, PaymentCollection.class);
                startActivity(intent);
            }
        });

    }



    //DashBoard Service
    public void HomeService(){

        Log.e("CALLL","uid->"+uid+"sid-->"+staffid);

        api_interface.Home(staffid).enqueue(new Callback<HomeResponse>() {
            @Override
            public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {

                if(response.isSuccessful()&&response.code()==200) {

                    if (response.body().getResponseCode().equalsIgnoreCase("0")) {
                        List<HomeResponse.Result> responseResult = response.body().getResult();
                        for (int i = 0; i < responseResult.size(); i++) {

                            name.setText(responseResult.get(i).getStaffName());
                            role.setText(responseResult.get(i).getDesignationName());
                            empid.setText(responseResult.get(i).getStaffCode());
                            taskCount.setText(responseResult.get(i).getTaskCount());
                            serviceCount.setText(responseResult.get(i).getServiceCount());
                            productCount.setText(responseResult.get(i).getProductDeliveryCount());
                            paymentCount.setText(responseResult.get(i).getPaymentCollectionCount());

                            Log.e("ImageString",""+responseResult.get(i).getProfileImage());

                            //Employee Pic
                            String url=("http://192.168.1.16:1212"+responseResult.get(i).getProfileImage());
                            Glide
                                    .with(context)
                                    .load(url)
                                    .into(profpic_glide);

                            Log.e("IO Status","    "+responseResult.get(i).getIOStatus());
                            Log.e("Meter Status","    "+responseResult.get(i).getMeterReading());

                            //Sign In/SignOut
                            if (responseResult.get(i).getIOStatus().equals(true)){
                                sigin.setText("SignOut");
                                mode="signOutt";
                            }
                            else{
                                sigin.setText("SignIn");
                                mode="signINN";
                            }

                           //Ride Start and stop
                            if (responseResult.get(i).getMeterReading().equals(true)){
                                km_text.setText("End Ride");
                                 Status="E";
                            }
                            else{
                                km_text.setText("Start Ride");
                                Status="S";
                            }
                        }
                        shimmer.stopShimmer();
                        shimmer.setVisibility(View.GONE);
                    }
                }
            }
            @Override
            public void onFailure(Call<HomeResponse> call, Throwable t) {

            }
        });
    }

    //SignInSignOut Service
    public void SignInService() {
        if(mode.equalsIgnoreCase("signOutt")){
             PunchType="O";
        }
        else if (mode.equalsIgnoreCase("signINN")){
             PunchType="I";
        }
        Log.e("PunchType","  "+PunchType);
        Log.e("staffid","  "+staffid);
        Log.e("staffid","  "+uid);

        api_interface.WorkSignInSignOut(staffid,PunchType,"M",uid).enqueue(new Callback<WorkSignInSignOutResponse>() {
            @Override
            public void onResponse(Call<WorkSignInSignOutResponse> call, Response<WorkSignInSignOutResponse> response) {

                if(response.isSuccessful()&&response.code()==200) {

                    if (response.body().getResponseCode().equalsIgnoreCase("0")) {
                        List<WorkSignInSignOutResponse.Result> responseResult = response.body().getResult();
                        for (int i = 0; i < responseResult.size(); i++) {

                            Log.e("Msg","  "+responseResult.get(i).getMsg());
                            Log.e("Msg","  "+responseResult.get(i).getErrorcode());
                            Log.e("Msg","  "+responseResult.get(i).getResult());

                            Utils.ShowCustomToast(responseResult.get(i).getMsg(),getApplicationContext());
                            if (responseResult.get(i).getErrorcode().equalsIgnoreCase("0")) {

                                HomeService();
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<WorkSignInSignOutResponse> call, Throwable t) {

            }
        });
    }


    //KM Entering Dialogue
    public void dialogue_box() {
        Vehicle_km dialogFragment = new Vehicle_km(Home.this);
        Bundle bundle=new Bundle();
        bundle.putString("Type",Status);
        dialogFragment.setArguments(bundle);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.framelayout, dialogFragment);
        ft.commit();
    }

    //Logout Dialogue
    public void Logout_dialogue() {
        LogoutDialogue dialogFragment = new LogoutDialogue();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.framelayout, dialogFragment);
        ft.commit();
    }

    //Back Button Dialogue
    public void Back_dialogue() {
        BackButton dialogFragment = new BackButton();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.framelayout, dialogFragment);
        ft.commit();
    }


    @Override
    protected void onResume() {
        super.onResume();

        HomeService();
    }


    @Override
    public void onBackPressed() {
        Back_dialogue();
    }
}
