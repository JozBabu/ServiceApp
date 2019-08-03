package com.essensol.serviceapp.Activity;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.essensol.serviceapp.Dialogue.DeliveryDialogue;
import com.essensol.serviceapp.Dialogue.Vehicle_km;
import com.essensol.serviceapp.R;

public class ProductDelivery_Details extends AppCompatActivity {

    Button productsubmitbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_delivery__details);

        productsubmitbtn=(Button)findViewById(R.id.productsubmitbtn);



        productsubmitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogue_box();

            }
        });
    }

    public void dialogue_box()
    {
        DeliveryDialogue dialogFragment = new DeliveryDialogue();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.framelayout_product, dialogFragment);
        ft.commit();

    }
}
