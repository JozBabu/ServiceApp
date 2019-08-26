package com.essensol.serviceapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.essensol.serviceapp.Activity.CompletedServiceDetails;
import com.essensol.serviceapp.Activity.ServiceDetails;
import com.essensol.serviceapp.Model_Classes.CompletedServiceModel;
import com.essensol.serviceapp.R;

import java.util.List;

public class CompletedAdapter extends RecyclerView.Adapter<CompletedAdapter.CompletedViewholder> {

    private Context context;
    private List<CompletedServiceModel> list;

    public CompletedAdapter(Context context, List<CompletedServiceModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CompletedViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.completed_list,viewGroup,false);
        return new CompletedAdapter.CompletedViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompletedViewholder completedViewholder, final int i) {

        completedViewholder.completedclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, CompletedServiceDetails.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("ServiceId",list.get(i).getServiceId());
                context.getApplicationContext().startActivity(intent);
            }
        });

        completedViewholder.custName.setText(list.get(i).getCustomerName());
        completedViewholder.date.setText(list.get(i).getServiceDate());
        completedViewholder.ProblemDetails.setText(list.get(i).getProblemDetails());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CompletedViewholder extends RecyclerView.ViewHolder
    {

        LinearLayout completedclick;
        TextView custName,date,ProblemDetails;

        public CompletedViewholder(@NonNull View itemView) {
            super(itemView);

            completedclick=itemView.findViewById(R.id.completedclick);
            custName=itemView.findViewById(R.id.custName);
            date=itemView.findViewById(R.id.date);
            ProblemDetails=itemView.findViewById(R.id.ProblemDetails);

        }
    }
}
