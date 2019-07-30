package com.essensol.serviceapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toolbar;

import com.essensol.serviceapp.R;
import com.essensol.serviceapp.Utility.ToolBar;

public class WorkReport extends ToolBar {

    LinearLayout submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_work_report, contentFrameLayout);

        submit=(LinearLayout)findViewById(R.id.submit);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(WorkReport.this,Home.class);
                startActivity(intent);
            }
        });


    }
}
