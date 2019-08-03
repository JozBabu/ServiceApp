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

import com.essensol.serviceapp.Activity.ProductDelivery_Details;
import com.essensol.serviceapp.Activity.Task_Details;
import com.essensol.serviceapp.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

public class ProductDeliveryAdapter extends RecyclerView.Adapter<ProductDeliveryAdapter.ProductDeliveryViewholder>{


    private Context context;
    private String[] mData;

    public ProductDeliveryAdapter(Context context, String[] mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ProductDeliveryViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_list,viewGroup,false);
        return new ProductDeliveryAdapter.ProductDeliveryViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductDeliveryViewholder productDeliveryViewholder, int i) {

        productDeliveryViewholder.productclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ProductDelivery_Details.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mData.length;
    }

    public class ProductDeliveryViewholder extends RecyclerView.ViewHolder
    {

        LinearLayout productclick;

        public ProductDeliveryViewholder(@NonNull View itemView) {
            super(itemView);

            productclick=itemView.findViewById(R.id.productclick);

        }
    }
}
