package com.essensol.serviceapp.Activity;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.essensol.serviceapp.Dialogue.DeliveryDialogue;
import com.essensol.serviceapp.R;
import com.essensol.serviceapp.Utility.ToolBar;

public class Task_Details extends ToolBar {

    Button tasksubmitbtn;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_task_details, contentFrameLayout);

        android.support.v7.widget.Toolbar tb= getToolBar();

        title =tb.findViewById(R.id.appname);
        title.setText("Task Details");

        tasksubmitbtn=(Button)findViewById(R.id.tasksubmitbtn);



        //Completed Click
        tasksubmitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogue_box();



            }
        });
    }

    public void dialogue_box()
    {
        DeliveryDialogue dialogFragment = new DeliveryDialogue();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.framelayout_task, dialogFragment);
        ft.commit();

    }
}
