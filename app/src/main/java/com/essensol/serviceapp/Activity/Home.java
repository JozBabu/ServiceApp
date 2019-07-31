package com.essensol.serviceapp.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.essensol.serviceapp.Dialogue.Vehicle_km;
import com.essensol.serviceapp.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

public class Home extends AppCompatActivity {

   SimpleDraweeView logout_icon,profpic,serviceImg,taskImg,productImg,profileicon;
   TextView name,role,empid,serviceText,serviceCount,sigin,taskText,taskCount,
            productText,productCount,profileText,appname;
   LinearLayout service,task,productDelivery,profile,signInbtn;
   ImageView profpic_glide,serviceImg_glide,taskImg_glide,productImg_glide,profileicon_glide;
    private Context context=Home.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_test);

        profpic_glide=(ImageView) findViewById(R.id.profpic);
        serviceImg_glide=(ImageView)findViewById(R.id.serviceImg);
        taskImg_glide=(ImageView)findViewById(R.id.taskImg);
        productImg_glide=(ImageView)findViewById(R.id.productImg);
        profileicon_glide=(ImageView)findViewById(R.id.profileicon);


     logout_icon=(SimpleDraweeView)findViewById(R.id.logout);
//        profpic=(SimpleDraweeView)findViewById(R.id.profpic);
//        serviceImg=(SimpleDraweeView)findViewById(R.id.serviceImg);
//        taskImg=(SimpleDraweeView)findViewById(R.id.taskImg);
//        productImg=(SimpleDraweeView)findViewById(R.id.productImg);
//        profileicon=(SimpleDraweeView)findViewById(R.id.profileicon);

        appname=(TextView)findViewById(R.id.appname);
        name=(TextView)findViewById(R.id.name);
        role=(TextView)findViewById(R.id.role);
        empid=(TextView)findViewById(R.id.empid);
        serviceText=(TextView)findViewById(R.id.serviceText);
        serviceCount=(TextView)findViewById(R.id.serviceCount);
        sigin=(TextView) findViewById(R.id.sigin);
        taskText=(TextView)findViewById(R.id.taskText);
        taskCount=(TextView)findViewById(R.id.taskCount);
        productText=(TextView)findViewById(R.id.productText);
        productCount=(TextView)findViewById(R.id.productCount);
        profileText=(TextView)findViewById(R.id.profileText);

        service=(LinearLayout)findViewById(R.id.service);
        task=(LinearLayout)findViewById(R.id.task);
        productDelivery=(LinearLayout)findViewById(R.id.productDelivery);
        profile=(LinearLayout)findViewById(R.id.profile);
        signInbtn=(LinearLayout)findViewById(R.id.signInbtn);


        //Simpledrawerview Image loading
        ImageRequest imageRequest1 = ImageRequestBuilder.newBuilderWithResourceId(R.drawable.logouticon).build();
//        ImageRequest imageRequest2 =ImageRequestBuilder.newBuilderWithResourceId(R.drawable.employe_pic).build();
//        ImageRequest imageRequest3=ImageRequestBuilder.newBuilderWithResourceId(R.drawable.service_icon).build();
//        ImageRequest imageRequest4 = ImageRequestBuilder.newBuilderWithResourceId(R.drawable.list).build();
//        ImageRequest imageRequest5 =ImageRequestBuilder.newBuilderWithResourceId(R.drawable.user).build();
//        ImageRequest imageRequest6=ImageRequestBuilder.newBuilderWithResourceId(R.drawable.shopping_bag).build();
        logout_icon.setImageURI(imageRequest1.getSourceUri());
//        profpic.setImageURI(imageRequest2.getSourceUri());
//        serviceImg.setImageURI(imageRequest3.getSourceUri());
//        taskImg.setImageURI(imageRequest4.getSourceUri());
//        productImg.setImageURI(imageRequest6.getSourceUri());
//        profileicon.setImageURI(imageRequest5.getSourceUri());


        //Glide Image Loading
        int profilepic= R.drawable.employe_pic;
        int serviceicon= R.drawable.service_icon;
        int list= R.drawable.list;
        int user= R.drawable.user;
        int product=R.drawable.shopping_bag;

        //Employee Pic
        Glide
                .with(context)
                .load(profilepic)
                .into(profpic_glide);

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
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/MontserratBold.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getAssets(),  "fonts/MontserratMedium.ttf");
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
        appname.setTypeface(custom_font2);

        //ProfilePic Click
        profpic_glide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this, Profile.class);
                startActivity(intent);
            }
        });

        //Logout Click
        logout_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home.this, Login.class);
                startActivity(intent);
            }
        });

        //SignIn Click
        signInbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vehicle_km dialogFragment = new Vehicle_km();

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.framelayout, dialogFragment);
                ft.commit();
            }
        });

        //Click to Service Page
        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home.this, Service.class);
                startActivity(intent);
            }
        });

        //Click to Task Page
        task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home.this, Task.class);
                startActivity(intent);
            }
        });

        //Click to Product Delivery Page
        productDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home.this, Product_Delivery.class);
                startActivity(intent);
            }
        });

        //Click to Profile Page
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home.this, Profile.class);
                startActivity(intent);
            }
        });

    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        moveTaskToBack(true);
//    }
}
