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

import com.essensol.serviceapp.Activity.ServiceDetails;
import com.essensol.serviceapp.R;

public class PendingAdapter extends  RecyclerView.Adapter<PendingAdapter.PendingViewholder>{


    private String mData[];
    private Context context;

    public PendingAdapter(String[] mData, Context context) {
        this.mData = mData;
        this.context = context;
    }

    @NonNull
    @Override
    public PendingViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pending_list,viewGroup,false);
        return new PendingAdapter.PendingViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PendingViewholder pendingViewholder, int i) {

        pendingViewholder.pendingserivceclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, ServiceDetails.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.length;
    }

    public class PendingViewholder extends RecyclerView.ViewHolder
    {

        LinearLayout pendingserivceclick;
        public PendingViewholder(@NonNull View itemView) {
            super(itemView);

            pendingserivceclick=itemView.findViewById(R.id.pendingserivceclick);
        }
    }
}
