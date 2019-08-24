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

import com.essensol.serviceapp.Activity.ServiceDetails;
import com.essensol.serviceapp.Model_Classes.PendingServiceModel;
import com.essensol.serviceapp.R;

import java.util.List;

public class PendingAdapter extends  RecyclerView.Adapter<PendingAdapter.PendingViewholder>{



    private Context context;
    private List<PendingServiceModel> list;

    public PendingAdapter(Context context, List<PendingServiceModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PendingViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pending_list,viewGroup,false);
        return new PendingAdapter.PendingViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PendingViewholder pendingViewholder, final int i) {



        pendingViewholder.custName.setText(list.get(i).getCustomerName());
        pendingViewholder.date.setText(list.get(i).getServiceDate());
        pendingViewholder.pblm_det.setText(list.get(i).getProblemDetails());

        pendingViewholder.pendingserivceclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, ServiceDetails.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("ServiceId",list.get(i).getServiceId());
                context.getApplicationContext().startActivity(intent);

            }
        });




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PendingViewholder extends RecyclerView.ViewHolder
    {

        LinearLayout pendingserivceclick;
        TextView custName,date,pblm_det;
        public PendingViewholder(@NonNull View itemView) {
            super(itemView);

            pendingserivceclick=itemView.findViewById(R.id.pendingserivceclick);
            custName=itemView.findViewById(R.id.custName);
            date=itemView.findViewById(R.id.date);
            pblm_det=itemView.findViewById(R.id.pblm_det);

        }
    }
}
