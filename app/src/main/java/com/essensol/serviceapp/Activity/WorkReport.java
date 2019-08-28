package com.essensol.serviceapp.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toolbar;

import com.essensol.serviceapp.Model_Classes.AccessoriesList_model;
import com.essensol.serviceapp.Model_Classes.JobReportStatusList_model;
import com.essensol.serviceapp.Model_Classes.ProblemList_model;
import com.essensol.serviceapp.Model_Classes.ProductList_model;
import com.essensol.serviceapp.Model_Classes.StatusList_model;
import com.essensol.serviceapp.Model_Classes.VehicleNo_model;
import com.essensol.serviceapp.R;
import com.essensol.serviceapp.RetrofitUtilits.ApiClient;
import com.essensol.serviceapp.RetrofitUtilits.Api_interface;
import com.essensol.serviceapp.RetroftResponseClasses.AccessoriesListresponse;
import com.essensol.serviceapp.RetroftResponseClasses.ProblemListresponse;
import com.essensol.serviceapp.RetroftResponseClasses.ProductListResponse;
import com.essensol.serviceapp.RetroftResponseClasses.ServiceJobReportStatusesresponse;
import com.essensol.serviceapp.RetroftResponseClasses.TaskSubmitResponse;
import com.essensol.serviceapp.Utility.ToolBar;
import com.essensol.serviceapp.Utility._CONSTANTS;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorkReport extends ToolBar {

    LinearLayout submit,Pricelayout,accesorieslay;
    Spinner status,productss,problems,accessories;
    String array_status[]={"status","Bring Back","Postponed","Close"};

    ArrayAdapter<ProductList_model> productList_adapter;
    ArrayList<ProductList_model> iteems =new ArrayList<>();

    ArrayAdapter<ProblemList_model> problemList_adapter;
    ArrayList<ProblemList_model> Problemsiteems =new ArrayList<>();

    ArrayAdapter<JobReportStatusList_model> JobReportStatusList_adapter;
    ArrayList<JobReportStatusList_model> StatusListiteems =new ArrayList<>();

    ArrayAdapter<AccessoriesList_model> AccessoriesList_adapter;
    ArrayList<AccessoriesList_model> AccessoriesListiteems =new ArrayList<>();

    TextView title,jobNo;
    String productId,ProblemsId,StatusId,JobNumber,accessoriesId;
    Api_interface api_interface;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_work_report, contentFrameLayout);

        android.support.v7.widget.Toolbar tb= getToolBar();

        title =tb.findViewById(R.id.appname);
        title.setText("Work Report");

        submit=(LinearLayout)findViewById(R.id.submit);
        Pricelayout=(LinearLayout)findViewById(R.id.Pricelayout);
        accesorieslay=(LinearLayout)findViewById(R.id.accesorieslay);

        jobNo=(TextView)findViewById(R.id.jobNo);
        status=(Spinner)findViewById(R.id.status);
        productss=(Spinner)findViewById(R.id.productss);
        problems=(Spinner)findViewById(R.id.problems);
        accessories=(Spinner)findViewById(R.id.accessories);

        Bundle bundle= getIntent().getExtras();
        assert bundle != null;
        JobNumber=bundle.getString("JobNumber");

        jobNo.setText(JobNumber);

        //Api Interface
        api_interface= ApiClient.getRetrofit().create(Api_interface.class);


        // Status Spinner Loading
        GetStattusList();
        status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                StatusId=StatusListiteems.get(position).getJobStatusId();

                if (StatusId.equalsIgnoreCase("7"))
                {
                    Pricelayout.setVisibility(View.VISIBLE);
                    accesorieslay.setVisibility(View.GONE);
                }
                else if (StatusId.equalsIgnoreCase("5"))
                {
                    Pricelayout.setVisibility(View.GONE);
                    accesorieslay.setVisibility(View.VISIBLE);
                }
                else if (StatusId.equalsIgnoreCase("6"))
                {
                    Pricelayout.setVisibility(View.GONE);
                    accesorieslay.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //Product List Spinner Loading
        GetProductList();

        productss.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    productId=iteems.get(position).getProductId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //Problems List Spinner

        GetProblemList();

        problems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

             ProblemsId = Problemsiteems.get(position).getProblemId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Accessories List Spinner
        GetAccessoriesList();

        accessories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                accessoriesId=AccessoriesListiteems.get(position).getAccessoryId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(WorkReport.this,Home.class);
                startActivity(intent);
            }
        });


    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////Product Lit Service////////////////////////////////////////////////////////////////

    public void GetProductList(){

        sp = getSharedPreferences("UserLog", MODE_PRIVATE);
        String CompId = sp.getString(_CONSTANTS.CompID, null);

        Log.e("ComId",""+CompId);

        api_interface.ProductList(CompId).enqueue(new Callback<ProductListResponse>() {
            @Override
            public void onResponse(Call<ProductListResponse> call, Response<ProductListResponse> response) {

                if (response.isSuccessful() && response.code() == 200) {


                    if (response.body().getResponseCode().equalsIgnoreCase("0")) {

                        List<ProductListResponse.Result> responseResult = response.body().getResult();

                        for (int i = 0; i < responseResult.size(); i++) {

                            ProductList_model items = new ProductList_model(
                                    responseResult.get(i).getProductId(),
                                    responseResult.get(i).getProduct());


                            iteems.add(items);

                        }
                        productList_adapter = new ArrayAdapter<ProductList_model>(getApplicationContext(),  R.layout.spinnertextview, iteems);
                        productss.setAdapter(productList_adapter);

                    }

                }

            }

            @Override
            public void onFailure(Call<ProductListResponse> call, Throwable t) {

            }
        });
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////Problem Lit Service////////////////////////////////////////////////////////////////

    public void GetProblemList(){

        api_interface.ProblemList().enqueue(new Callback<ProblemListresponse>() {
            @Override
            public void onResponse(Call<ProblemListresponse> call, Response<ProblemListresponse> response) {

                if (response.isSuccessful() && response.code() == 200) {


                    if (response.body().getResponseCode().equalsIgnoreCase("0")) {

                        List<ProblemListresponse.Result> responseResult = response.body().getResult();

                        for (int i = 0; i < responseResult.size(); i++) {

                            ProblemList_model items = new ProblemList_model(
                                    responseResult.get(i).getProblemId(),
                                    responseResult.get(i).getProblem());


                            Problemsiteems.add(items);

                        }
                        problemList_adapter = new ArrayAdapter<ProblemList_model>(getApplicationContext(),  R.layout.spinnertextview, Problemsiteems);
                        problems.setAdapter(problemList_adapter);
                    }
                }


            }

            @Override
            public void onFailure(Call<ProblemListresponse> call, Throwable t) {

            }
        });
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////Status Lit Service////////////////////////////////////////////////////////////////

    public void GetStattusList(){

        api_interface.JobReportStatuses().enqueue(new Callback<ServiceJobReportStatusesresponse>() {
            @Override
            public void onResponse(Call<ServiceJobReportStatusesresponse> call, Response<ServiceJobReportStatusesresponse> response) {

                if (response.isSuccessful() && response.code() == 200) {


                    if (response.body().getResponseCode().equalsIgnoreCase("0")) {

                        List<ServiceJobReportStatusesresponse.Result> responseResult = response.body().getResult();

                        for (int i = 0; i < responseResult.size(); i++) {

                            JobReportStatusList_model items = new JobReportStatusList_model(
                                    responseResult.get(i).getJobStatusId(),
                                    responseResult.get(i).getJobStatusName());

                            StatusListiteems.add(items);

                        }
                        JobReportStatusList_adapter = new ArrayAdapter<JobReportStatusList_model>(getApplicationContext(),  R.layout.spinnertextview, StatusListiteems);
                        status.setAdapter(JobReportStatusList_adapter);

                    }
                }

            }

            @Override
            public void onFailure(Call<ServiceJobReportStatusesresponse> call, Throwable t) {

            }
        });
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////Accessories Lit Service////////////////////////////////////////////////////////////////

    public void GetAccessoriesList(){

        api_interface.AccessoriesList().enqueue(new Callback<AccessoriesListresponse>() {
            @Override
            public void onResponse(Call<AccessoriesListresponse> call, Response<AccessoriesListresponse> response) {

                if (response.isSuccessful() && response.code() == 200) {


                    if (response.body().getResponseCode().equalsIgnoreCase("0")) {

                        List<AccessoriesListresponse.Result> responseResult = response.body().getResult();

                        for (int i = 0; i < responseResult.size(); i++) {

                            AccessoriesList_model items = new AccessoriesList_model(

                                    responseResult.get(i).getAccessoryId(),
                                    responseResult.get(i).getAccessoryName());

                            AccessoriesListiteems.add(items);

                        }
                        AccessoriesList_adapter = new ArrayAdapter<AccessoriesList_model>(getApplicationContext(),  R.layout.spinnertextview, AccessoriesListiteems);
                        accessories.setAdapter(AccessoriesList_adapter);

                    }
                }


            }

            @Override
            public void onFailure(Call<AccessoriesListresponse> call, Throwable t) {

            }
        });

    }

}
