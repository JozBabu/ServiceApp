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

import com.essensol.serviceapp.Activity.ProductDelivery_Details;
import com.essensol.serviceapp.Activity.Task_Details;
import com.essensol.serviceapp.Model_Classes.ProductListModel;
import com.essensol.serviceapp.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.util.List;

public class ProductDeliveryAdapter extends RecyclerView.Adapter<ProductDeliveryAdapter.ProductDeliveryViewholder>{


    private Context context;
    private List<ProductListModel> list;

    public ProductDeliveryAdapter(Context context, List<ProductListModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ProductDeliveryViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_list,viewGroup,false);
        return new ProductDeliveryAdapter.ProductDeliveryViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductDeliveryViewholder productDeliveryViewholder,final int i) {

        productDeliveryViewholder.studentName.setText(list.get(i).getName());
        productDeliveryViewholder.msgSubject.setText(list.get(i).getProduct());
        productDeliveryViewholder.date.setText(list.get(i).getCreatedOn());


        productDeliveryViewholder.productclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ProductDelivery_Details.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("JobId",list.get(i).getJobId());
                context.getApplicationContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ProductDeliveryViewholder extends RecyclerView.ViewHolder
    {

        LinearLayout productclick;
        TextView studentName,date,msgSubject;

        public ProductDeliveryViewholder(@NonNull View itemView) {
            super(itemView);

            productclick=itemView.findViewById(R.id.productclick);
            studentName=itemView.findViewById(R.id.studentName);
            date=itemView.findViewById(R.id.date);
            msgSubject=itemView.findViewById(R.id.msgSubject);

        }
    }
}
