package com.essensol.serviceapp.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.essensol.serviceapp.Adapter.PendingAdapter;
import com.essensol.serviceapp.Adapter.ProductDeliveryAdapter;
import com.essensol.serviceapp.Model_Classes.ProductListModel;
import com.essensol.serviceapp.Model_Classes.TaskListModel;
import com.essensol.serviceapp.R;
import com.essensol.serviceapp.RetrofitUtilits.ApiClient;
import com.essensol.serviceapp.RetrofitUtilits.Api_interface;
import com.essensol.serviceapp.RetroftResponseClasses.ProductDeliveryListResponse;
import com.essensol.serviceapp.RetroftResponseClasses.TaskListResponse;
import com.essensol.serviceapp.Utility.ToolBar;
import com.essensol.serviceapp.Utility._CONSTANTS;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.security.AccessController.getContext;

public class Product_Delivery extends ToolBar {

    RecyclerView productDelivery_recycle;
    ProductDeliveryAdapter productDeliveryAdapter;
    TextView title;
    SharedPreferences sp;
    Api_interface api_interface;
    ArrayList<ProductListModel> items_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_product_delivery, contentFrameLayout);

        android.support.v7.widget.Toolbar tb= getToolBar();

        title =tb.findViewById(R.id.appname);
        title.setText("Product Delivery");

        items_list=new ArrayList<>();

        productDelivery_recycle=(RecyclerView)findViewById(R.id.productDelivery_recycle);

        //Api Interface
        api_interface = ApiClient.getRetrofit().create(Api_interface.class);



        productDelivery_recycle.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        GetproductDeliveryList();

    }

    public void GetproductDeliveryList(){

        sp = getSharedPreferences("UserLog", MODE_PRIVATE);
        String uid = sp.getString(_CONSTANTS.UserId, null);
        String staffid = sp.getString(_CONSTANTS.StaffId, null);

        api_interface.ProductDeliveryList(staffid).enqueue(new Callback<ProductDeliveryListResponse>() {
            @Override
            public void onResponse(Call<ProductDeliveryListResponse> call, Response<ProductDeliveryListResponse> response) {


                if (response.isSuccessful() && response.code() == 200) {

                    if (response.body().getResponseCode().equalsIgnoreCase("0")) {

                        List<ProductDeliveryListResponse.Result> responseResult = response.body().getResult();

                        for (int i = 0; i < responseResult.size(); i++) {

                            ProductListModel items = new ProductListModel(
                                    responseResult.get(i).getJobId(),
                                    responseResult.get(i).getName(),
                                    responseResult.get(i).getProduct(),
                                    responseResult.get(i).getCreatedOn()

                            );

                            items_list.add(items);
                            productDeliveryAdapter = new ProductDeliveryAdapter(getApplicationContext(),items_list);
                            productDelivery_recycle.setAdapter(productDeliveryAdapter);
                        }

                    }

                }
            }

            @Override
            public void onFailure(Call<ProductDeliveryListResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Product_Delivery.this,Home.class);
        startActivity(intent);



    }
}

