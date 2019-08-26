package com.essensol.serviceapp.Fragments;


import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.essensol.serviceapp.Adapter.CompletedAdapter;
import com.essensol.serviceapp.Adapter.PendingAdapter;
import com.essensol.serviceapp.Model_Classes.CompletedServiceModel;
import com.essensol.serviceapp.Model_Classes.PendingServiceModel;
import com.essensol.serviceapp.R;
import com.essensol.serviceapp.RetrofitUtilits.ApiClient;
import com.essensol.serviceapp.RetrofitUtilits.Api_interface;
import com.essensol.serviceapp.RetroftResponseClasses.CompletedServiceResponse;
import com.essensol.serviceapp.RetroftResponseClasses.PendingServiceResponse;
import com.essensol.serviceapp.Utility._CONSTANTS;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


public class CompletedTab extends Fragment {

    RecyclerView completedrecycle;
    CompletedAdapter completedAdapter;
    Api_interface api_interface;
    ArrayList<CompletedServiceModel> items_list;
    SharedPreferences sp;
    Spinner FilterSpinner;
    ProgressDialog dialog;
    String array_filter[] = {"Today's", "Monthly","Yearly"};

    public CompletedTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View RootView = inflater.inflate(R.layout.fragment_completed_tab, container, false);

        completedrecycle = RootView.findViewById(R.id.completedrecycle);
        FilterSpinner=RootView.findViewById(R.id.delivery_status);
      //  rg=RootView.findViewById(R.id.radiogrp);

        final ArrayAdapter<String> spinner_adapter_day = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, array_filter);
        spinner_adapter_day.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        FilterSpinner.setAdapter(spinner_adapter_day);

        items_list=new ArrayList<>();




        //Api Interface
        api_interface= ApiClient.getRetrofit().create(Api_interface.class);

        String data[]={"1","2"};


     completedrecycle.setLayoutManager(new LinearLayoutManager(getContext()));
      //  getCompletedservicelist();


         dialog =new ProgressDialog(getActivity());

        dialog.setTitle("Getting Information");
        dialog.setMessage("This may take a while..");

        FilterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position==0)
                {
                   dialog.show();
                    getCompletedservicelist("T");



                }
                else if (position==1)
                {
                    dialog.show();
                    getCompletedservicelist("M");

                }
                else if (position==2)
                {
                    dialog.show();
                    getCompletedservicelist("Y");

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return RootView;
    }

    public void getCompletedservicelist(String Type) {
        sp = getActivity().getSharedPreferences("UserLog",MODE_PRIVATE);
        String uid= sp.getString(_CONSTANTS.UserId, null);
        String staffid= sp.getString(_CONSTANTS.StaffId, null);
        String brid= sp.getString(_CONSTANTS.BrId, null);

        api_interface.CompltedServicelist(staffid,brid,Type).enqueue(new Callback<CompletedServiceResponse>() {
            @Override
            public void onResponse(Call<CompletedServiceResponse> call, Response<CompletedServiceResponse> response) {
                items_list.clear();

                dialog.dismiss();


                if (response.isSuccessful() && response.code() == 200) {
                    items_list.clear();

                    if (response.body().getResponseCode().equalsIgnoreCase("0")) {


                        List<CompletedServiceResponse.Result> responseResult = response.body().getResult();

                        for (int i = 0; i < responseResult.size(); i++) {

                            CompletedServiceModel items = new CompletedServiceModel(
                                    responseResult.get(i).getServiceId(),
                                    responseResult.get(i).getServiceDate(),
                                    responseResult.get(i).getCustomerId(),
                                    responseResult.get(i).getProblemDetails(),
                                    responseResult.get(i).getCustomerName()
                            );

                            items_list.add(items);

                        }



                    completedAdapter = new CompletedAdapter(getActivity(),items_list);
                    completedrecycle.setAdapter(completedAdapter);

                        completedAdapter.notifyDataSetChanged();
                    }

                    else{
                        completedAdapter = new CompletedAdapter(getActivity(),items_list);
                        completedrecycle.setAdapter(completedAdapter);
                        completedAdapter.notifyDataSetChanged();
                    }

                }

            }

            @Override
            public void onFailure(Call<CompletedServiceResponse> call, Throwable t) {

            }
        });
    }
}
