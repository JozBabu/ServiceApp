package com.essensol.serviceapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.essensol.serviceapp.Activity.ServiceDetails;
import com.essensol.serviceapp.Activity.Task_Details;
import com.essensol.serviceapp.Model_Classes.TaskListModel;
import com.essensol.serviceapp.R;

import java.util.List;

public class Task_Adapter extends RecyclerView.Adapter<Task_Adapter.Task_ViewHolder>{


    private Context context;
    private List<TaskListModel> list;

    public Task_Adapter(Context context, List<TaskListModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Task_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.task_list,viewGroup,false);
        return new Task_Adapter.Task_ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Task_ViewHolder task_viewHolder,final int i) {

        task_viewHolder.Name.setText(list.get(i).getTaskName());
        task_viewHolder.date.setText(list.get(i).getCreatedOn());
        task_viewHolder.details.setText(list.get(i).getDescription());
        task_viewHolder.status.setText(list.get(i).getStatusName());

     if (list.get(i).getStatusName().equalsIgnoreCase("Pending Task"))
     {
         task_viewHolder.status.setTextColor(Color.parseColor("#D81B60"));

     }

        task_viewHolder.taskclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, Task_Details.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("TaskId",list.get(i).getTaskId());
                context.getApplicationContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Task_ViewHolder extends RecyclerView.ViewHolder
    {
        LinearLayout taskclick;
        TextView Name,date,details,status;

        public Task_ViewHolder(@NonNull View itemView) {
            super(itemView);

            taskclick=itemView.findViewById(R.id.taskclick);
            Name=itemView.findViewById(R.id.Name);
            date=itemView.findViewById(R.id.date);
            details=itemView.findViewById(R.id.details);
            status=itemView.findViewById(R.id.status);
        }
    }
}
