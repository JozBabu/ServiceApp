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

public class CompletedAdapter extends RecyclerView.Adapter<CompletedAdapter.CompletedViewholder> {

    private Context context;
    private String mData[];

    public CompletedAdapter(Context context, String[] mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public CompletedViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.completed_list,viewGroup,false);
        return new CompletedAdapter.CompletedViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompletedViewholder completedViewholder, int i) {

//        if(i %2 == 1)
//        {
//            completedViewholder.completedclick.setBackgroundColor(Color.parseColor("#2c80e3"));
//            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFFFF"));
//        }
//        else
//        {
//            completedViewholder.completedclick.setBackgroundColor(Color.parseColor("#345996"));
//            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFAF8FD"));
//        }
    }

    @Override
    public int getItemCount() {
        return mData.length;
    }

    public class CompletedViewholder extends RecyclerView.ViewHolder
    {

        LinearLayout completedclick;

        public CompletedViewholder(@NonNull View itemView) {
            super(itemView);

            completedclick=itemView.findViewById(R.id.completedclick);
        }
    }
}
