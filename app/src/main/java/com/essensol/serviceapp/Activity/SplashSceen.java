package com.essensol.serviceapp.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.essensol.serviceapp.R;

public class SplashSceen extends AppCompatActivity {

    ImageView Splash_logo;
    private Context context = SplashSceen.this;
    Boolean isLogged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_sceen);

        Splash_logo=(ImageView)findViewById(R.id.Splash_logo);

        int pic=R.mipmap.employeeicon_round;
        Glide
                .with(context)
                .load(pic)
                .into(Splash_logo);

        SharedPreferences settings1 = getSharedPreferences("UserLog", 0);
        isLogged = settings1.getBoolean("LoggedUser", false);
        Log.e("checking boolllllll",""+isLogged);

        if (isLogged) {
            Log.e("checking boolllllll","innn"+isLogged);



            final Intent i = new Intent(this, Home.class);
            Thread timer = new Thread() {
                public void run() {
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        startActivity(i);
                        finish();
                    }
                }
            };
            timer.start();
        }
        else {
            final Intent i = new Intent(this, Login.class);
            Thread timer = new Thread() {
                public void run() {
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        startActivity(i);
                        finish();
                    }
                }
            };
            timer.start();
        }
    }

}

