package com.essensol.serviceapp.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.essensol.serviceapp.Adapter.CompletedAdapter;
import com.essensol.serviceapp.Adapter.ProductDeliveryAdapter;
import com.essensol.serviceapp.Adapter.Task_Adapter;
import com.essensol.serviceapp.Model_Classes.CompletedServiceModel;
import com.essensol.serviceapp.Model_Classes.TaskListModel;
import com.essensol.serviceapp.R;
import com.essensol.serviceapp.RetrofitUtilits.ApiClient;
import com.essensol.serviceapp.RetrofitUtilits.Api_interface;
import com.essensol.serviceapp.RetroftResponseClasses.CompletedServiceResponse;
import com.essensol.serviceapp.RetroftResponseClasses.TaskListResponse;
import com.essensol.serviceapp.Utility.ToolBar;
import com.essensol.serviceapp.Utility._CONSTANTS;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Task extends ToolBar {

    RecyclerView task_recycle;
    Task_Adapter task_adapter;
    TextView title;
    Api_interface api_interface;
    SharedPreferences sp;
    ArrayList<TaskListModel> items_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_task, contentFrameLayout);

        android.support.v7.widget.Toolbar tb = getToolBar();
        title = tb.findViewById(R.id.appname);
        title.setText("Task");

        task_recycle = (RecyclerView) findViewById(R.id.task_recycle);

        items_list=new ArrayList<>();

        //Api Interface
        api_interface = ApiClient.getRetrofit().create(Api_interface.class);

        task_recycle.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        getCompletedservicelist();

    }


    public void getCompletedservicelist() {

        sp = getSharedPreferences("UserLog", MODE_PRIVATE);
        String uid = sp.getString(_CONSTANTS.UserId, null);
        String staffid = sp.getString(_CONSTANTS.StaffId, null);

        api_interface.TaskList(staffid).enqueue(new Callback<TaskListResponse>() {
            @Override
            public void onResponse(Call<TaskListResponse> call, Response<TaskListResponse> response) {
                items_list.clear();

                if (response.isSuccessful() && response.code() == 200) {

                    if (response.body().getResponseCode().equalsIgnoreCase("0")) {

                        List<TaskListResponse.Result> responseResult = response.body().getResult();

                        for (int i = 0; i < responseResult.size(); i++) {

                            TaskListModel items = new TaskListModel(
                                    responseResult.get(i).getTaskId(),
                                    responseResult.get(i).getTaskName(),
                                    responseResult.get(i).getDescription(),
                                    responseResult.get(i).getStatusName(),
                                    responseResult.get(i).getCreatedOn()
                            );

                            items_list.add(items);

                        }
                        task_adapter = new Task_Adapter(getApplicationContext(),items_list);
                        task_recycle.setAdapter(task_adapter);
                    }
                }

            }

            @Override
            public void onFailure(Call<TaskListResponse> call, Throwable t) {

            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Task.this,Home.class);
        startActivity(intent);
    }

}
