package com.essensol.serviceapp.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.essensol.serviceapp.R;

public class Task_Adapter extends RecyclerView.Adapter<Task_Adapter.Task_ViewHolder>{


    private Context context;
    private String mData[];

    public Task_Adapter(Context context, String[] mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public Task_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.task_list,viewGroup,false);
        return new Task_Adapter.Task_ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull Task_ViewHolder task_viewHolder, int i) {

        if(i %2 == 1)
        {
            task_viewHolder.taskclick.setBackgroundColor(Color.parseColor("#2c80e3"));
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        else
        {
            task_viewHolder.taskclick.setBackgroundColor(Color.parseColor("#345996"));
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFAF8FD"));
        }
    }

    @Override
    public int getItemCount() {
        return mData.length;
    }

    public class Task_ViewHolder extends RecyclerView.ViewHolder
    {

        LinearLayout taskclick;

        public Task_ViewHolder(@NonNull View itemView) {
            super(itemView);

            taskclick=itemView.findViewById(R.id.taskclick);
        }
    }
}
