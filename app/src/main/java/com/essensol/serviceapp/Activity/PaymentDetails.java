package com.essensol.serviceapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.essensol.serviceapp.R;
import com.essensol.serviceapp.Utility.ToolBar;

public class PaymentDetails extends ToolBar {

    Button paymentsubmitbtn;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_payment_details, contentFrameLayout);

        android.support.v7.widget.Toolbar tb= getToolBar();

        title =tb.findViewById(R.id.appname);
        title.setText("Payment Details");

        paymentsubmitbtn=(Button)findViewById(R.id.paymentsubmitbtn);


        //submit click
        paymentsubmitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentDetails.this,PaymentReport.class);
                startActivity(intent);
            }
        });
    }
}
