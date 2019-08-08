package com.essensol.serviceapp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

import com.essensol.serviceapp.Adapter.PaymentCollectionAdapter;
import com.essensol.serviceapp.Adapter.Task_Adapter;
import com.essensol.serviceapp.R;
import com.essensol.serviceapp.Utility.ToolBar;

public class PaymentCollection extends ToolBar {

    RecyclerView paymentCollection_reecycler;
    PaymentCollectionAdapter paymentCollectionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_payment_collection, contentFrameLayout);

        paymentCollection_reecycler=(RecyclerView)findViewById(R.id.paymentCollection_reecycler);

        String data[]={"1","2"};

        paymentCollection_reecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        paymentCollectionAdapter = new PaymentCollectionAdapter(getApplicationContext(),data);
        paymentCollection_reecycler.setAdapter(paymentCollectionAdapter);


    }
}
