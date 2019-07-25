package com.essensol.serviceapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

import com.essensol.serviceapp.Adapter.PendingAdapter;
import com.essensol.serviceapp.Adapter.ProductDeliveryAdapter;
import com.essensol.serviceapp.R;
import com.essensol.serviceapp.Utility.ToolBar;

import static java.security.AccessController.getContext;

public class Product_Delivery extends ToolBar {

    RecyclerView productDelivery_recycle;
    ProductDeliveryAdapter productDeliveryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_product_delivery, contentFrameLayout);

        productDelivery_recycle=(RecyclerView)findViewById(R.id.productDelivery_recycle);

        String data[]={"1","2"};

        productDelivery_recycle.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        productDeliveryAdapter = new ProductDeliveryAdapter(getApplicationContext(),data);
        productDelivery_recycle.setAdapter(productDeliveryAdapter);


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Product_Delivery.this,Home.class);
        startActivity(intent);
    }
}

