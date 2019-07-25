package com.essensol.serviceapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

import com.essensol.serviceapp.Adapter.ProductDeliveryAdapter;
import com.essensol.serviceapp.Adapter.Task_Adapter;
import com.essensol.serviceapp.R;
import com.essensol.serviceapp.Utility.ToolBar;

public class Task extends ToolBar {

    RecyclerView task_recycle;
    Task_Adapter task_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_task, contentFrameLayout);

        task_recycle=(RecyclerView)findViewById(R.id.task_recycle);

        String data[]={"1","2","3","4"};

        task_recycle.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        task_adapter = new Task_Adapter(getApplicationContext(),data);
        task_recycle.setAdapter(task_adapter);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Task.this,Home.class);
        startActivity(intent);
    }
}
