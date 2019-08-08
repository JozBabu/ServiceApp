package com.essensol.serviceapp.Activity;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.essensol.serviceapp.Dialogue.DeliveryDialogue;
import com.essensol.serviceapp.R;
import com.essensol.serviceapp.Utility.ToolBar;

public class Task_Details extends ToolBar {

    Button tasksubmitbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

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
