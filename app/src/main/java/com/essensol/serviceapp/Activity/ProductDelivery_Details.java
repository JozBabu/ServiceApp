package com.essensol.serviceapp.Activity;

import android.content.SharedPreferences;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.essensol.serviceapp.Dialogue.ProductDeliveryDialogue;
import com.essensol.serviceapp.Dialogue.TaskSubmitDialogue;
import com.essensol.serviceapp.R;
import com.essensol.serviceapp.RetrofitUtilits.ApiClient;
import com.essensol.serviceapp.RetrofitUtilits.Api_interface;
import com.essensol.serviceapp.RetroftResponseClasses.ProductDeliveryDetailsResponse;
import com.essensol.serviceapp.RetroftResponseClasses.ServiceDetailsResponse;
import com.essensol.serviceapp.Utility.ToolBar;
import com.essensol.serviceapp.Utility._CONSTANTS;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDelivery_Details extends ToolBar {

    Button productsubmitbtn;
    TextView title;
    Api_interface api_interface;
    String JobId;
    SharedPreferences sp;
    TextView CustName,date,product,CustomerAddress,shedule_date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_product_delivery__details, contentFrameLayout);

        android.support.v7.widget.Toolbar tb= getToolBar();

        title =tb.findViewById(R.id.appname);
        title.setText("Product Delivery");

        productsubmitbtn=(Button)findViewById(R.id.productsubmitbtn);

        CustName=(TextView)findViewById(R.id.CustName);
        date=(TextView)findViewById(R.id.date);
        product=(TextView)findViewById(R.id.product);
        CustomerAddress=(TextView)findViewById(R.id.CustomerAddress);
        shedule_date=(TextView)findViewById(R.id.shedule_date);

        Bundle bundle = getIntent().getExtras();
        JobId = bundle.getString("JobId");

        //Api Interface
        api_interface = ApiClient.getRetrofit().create(Api_interface.class);

        GetProductDeliveryDetails();
        productsubmitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogue_box();

            }
        });
    }


    /* Product Delivery Details Service */

    public void GetProductDeliveryDetails(){

        sp = getSharedPreferences("UserLog",MODE_PRIVATE);
        String brid= sp.getString(_CONSTANTS.BrId, null);

        Log.e("briddddddddd",""+brid + "JobIddddddddd"+JobId );

        api_interface.ProductDeliveryDetails(brid,JobId).enqueue(new Callback<ProductDeliveryDetailsResponse>() {
            @Override
            public void onResponse(Call<ProductDeliveryDetailsResponse> call, Response<ProductDeliveryDetailsResponse> response) {

                if (response.isSuccessful() && response.code() == 200) {

                    if (response.body().getResponseCode().equalsIgnoreCase("0")) {


                        List<ProductDeliveryDetailsResponse.Result> responseResult = response.body().getResult();

                        for (int i = 0; i < responseResult.size(); i++) {

                            CustName.setText(responseResult.get(i).getCustomerName());
                            date.setText(responseResult.get(i).getJobDate());
                            product.setText(responseResult.get(i).getProduct());
                            CustomerAddress.setText(responseResult.get(i).getCustomerAddress());
                          //  CustName.setText(responseResult.get(i).getCustomerName());


                        }

                    }

                }
            }

            @Override
            public void onFailure(Call<ProductDeliveryDetailsResponse> call, Throwable t) {

            }
        });
    }

    public void dialogue_box()
    {

        ProductDeliveryDialogue dialogFragment = new ProductDeliveryDialogue();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.framelayout_product, dialogFragment);
        ft.commit();

    }
}
