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

//        if(i %2 == 1)
//        {
//            productDeliveryViewholder.productclick.setBackgroundColor(Color.parseColor("#2c80e3"));
//            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFFFF"));
//        }
//        else
//        {
//            productDeliveryViewholder.productclick.setBackgroundColor(Color.parseColor("#345996"));
//            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFAF8FD"));
//        }


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
