package com.essensol.serviceapp.Activity;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.FrameLayout;

import com.essensol.serviceapp.R;
import com.essensol.serviceapp.Utility.ToolBar;

public class ServiceDetails extends ToolBar {

    Chronometer focus;
    Button submitbtn;
    int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_service_details, contentFrameLayout);

         focus = (Chronometer) findViewById(R.id.chronometer1);


        submitbtn=(Button)findViewById(R.id.submitbtn);

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (flag==0) {

                    focus.start();
                    flag=1;
                    submitbtn.setText("Job SignOut");
                }
                else
                {
                    focus.setBase(SystemClock.elapsedRealtime());
                    focus.stop();
                    flag=0;
                    Intent i = new Intent(ServiceDetails.this,WorkReport.class);
                    startActivity(i);

                }

            }
        });
    }
}
