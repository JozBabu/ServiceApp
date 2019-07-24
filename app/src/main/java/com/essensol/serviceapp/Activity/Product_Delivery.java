package com.essensol.serviceapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.essensol.serviceapp.R;
import com.essensol.serviceapp.Utility.ToolBar;

public class Product_Delivery extends ToolBar {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_product_delivery, contentFrameLayout);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Product_Delivery.this,Home.class);
        startActivity(intent);
    }
}

