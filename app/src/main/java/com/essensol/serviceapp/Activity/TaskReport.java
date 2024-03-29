package com.essensol.serviceapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.essensol.serviceapp.R;
import com.essensol.serviceapp.Utility.ToolBar;

public class TaskReport extends ToolBar {

    LinearLayout submit;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_task_report, contentFrameLayout);

        android.support.v7.widget.Toolbar tb= getToolBar();

        title =tb.findViewById(R.id.appname);
        title.setText("Payment Collection");

        submit=(LinearLayout)findViewById(R.id.submit);


        //submit click
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(TaskReport.this,Home.class);
                startActivity(intent);

            }
        });


    }
}
