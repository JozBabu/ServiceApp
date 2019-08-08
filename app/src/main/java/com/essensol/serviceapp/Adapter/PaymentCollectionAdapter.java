package com.essensol.serviceapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.essensol.serviceapp.Activity.PaymentDetails;
import com.essensol.serviceapp.Activity.ServiceDetails;
import com.essensol.serviceapp.R;

public class PaymentCollectionAdapter extends RecyclerView.Adapter<PaymentCollectionAdapter.PaymentCollectionViewholder>{

    private Context context;
    private String mdata[];

    public PaymentCollectionAdapter(Context context, String[] mdata) {
        this.context = context;
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public PaymentCollectionViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.payment_list,viewGroup,false);
        return new PaymentCollectionAdapter.PaymentCollectionViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentCollectionViewholder paymentCollectionViewholder, int i) {

        paymentCollectionViewholder.paymentlistclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, PaymentDetails.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mdata.length;
    }

    public class PaymentCollectionViewholder extends RecyclerView.ViewHolder
    {

        LinearLayout paymentlistclick;

        public PaymentCollectionViewholder(@NonNull View itemView) {
            super(itemView);

            paymentlistclick=itemView.findViewById(R.id.paymentlistclick);

        }
    }
}
