package com.essensol.serviceapp.Activity;

import android.content.SharedPreferences;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.essensol.serviceapp.Dialogue.TaskSubmitDialogue;
import com.essensol.serviceapp.R;
import com.essensol.serviceapp.RetrofitUtilits.ApiClient;
import com.essensol.serviceapp.RetrofitUtilits.Api_interface;
import com.essensol.serviceapp.RetroftResponseClasses.TaskDetailsResponse;
import com.essensol.serviceapp.Utility.ToolBar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Task_Details extends ToolBar {

    Button tasksubmitbtn;
    TextView title,TaskName,Taskdate,details,shedule_date,shedule_time;
    Api_interface  api_interface;
    SharedPreferences sp;
    String TaskId,TaskIdD,TaskStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_task_details, contentFrameLayout);

        android.support.v7.widget.Toolbar tb= getToolBar();

        title =tb.findViewById(R.id.appname);
        title.setText("Task Details");

        tasksubmitbtn=(Button)findViewById(R.id.tasksubmitbtn);

        TaskName=(TextView)findViewById(R.id.TaskName);
        Taskdate=(TextView)findViewById(R.id.Taskdate);
        details=(TextView)findViewById(R.id.details);
        shedule_date=(TextView)findViewById(R.id.shedule_date);
        shedule_time=(TextView)findViewById(R.id.shedule_time);

        Bundle bundle= getIntent().getExtras();
        TaskId=bundle.getString("TaskId");
        TaskStatus=bundle.getString("TaskStaus");

        //Api Interface
        api_interface = ApiClient.getRetrofit().create(Api_interface.class);

        GetTaskDetails();

        if (TaskStatus.equalsIgnoreCase("Completed"))
        {
            tasksubmitbtn.setVisibility(View.GONE);
        }

        //Completed Click
        tasksubmitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogue_box();

            }
        });
    }

    public void GetTaskDetails(){

        api_interface.TaskDetails(TaskId).enqueue(new Callback<TaskDetailsResponse>() {
            @Override
            public void onResponse(Call<TaskDetailsResponse> call, Response<TaskDetailsResponse> response) {

                if (response.isSuccessful() && response.code() == 200) {

                    if (response.body().getResponseCode().equalsIgnoreCase("0")) {

                        List<TaskDetailsResponse.Result> responseResult = response.body().getResult();

                        for (int i = 0; i < responseResult.size(); i++) {

                            TaskName.setText(responseResult.get(i).getTaskName());
                            Taskdate.setText(responseResult.get(i).getTaskDate());
                            details.setText(responseResult.get(i).getTaskDetails());
                            shedule_date.setText(responseResult.get(i).getDueDate());
                            shedule_time.setText(responseResult.get(i).getDueTime());
                            TaskIdD=responseResult.get(i).getTaskId();

                        }

                        tasksubmitbtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialogue_box();
                            }
                        });

                    }
                }
            }

            @Override
            public void onFailure(Call<TaskDetailsResponse> call, Throwable t) {

            }
        });

    }

    public void dialogue_box(){
        TaskSubmitDialogue dialogFragment = new TaskSubmitDialogue();
        Bundle bundle=new Bundle();
        bundle.putString("TaskId",TaskIdD);
        dialogFragment.setArguments(bundle);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.framelayout_task, dialogFragment);
        ft.commit();
    }

}
