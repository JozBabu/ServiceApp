package com.essensol.serviceapp.Utility;


import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

public class MyApplications extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }


}
