package com.essensol.serviceapp.Fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.essensol.serviceapp.Adapter.PendingAdapter;
import com.essensol.serviceapp.Model_Classes.PendingServiceModel;
import com.essensol.serviceapp.R;
import com.essensol.serviceapp.RetrofitUtilits.ApiClient;
import com.essensol.serviceapp.RetrofitUtilits.Api_interface;
import com.essensol.serviceapp.RetroftResponseClasses.PendingServiceResponse;
import com.essensol.serviceapp.RetroftResponseClasses.ProfileResponse;
import com.essensol.serviceapp.Utility._CONSTANTS;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class PendingTab extends Fragment {

    RecyclerView pendingrecycle;
    PendingAdapter pendingAdapter;
    SharedPreferences sp;
    Api_interface api_interface;
    ArrayList<PendingServiceModel> items_list;

    public PendingTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View RootView = inflater.inflate(R.layout.fragment_pending_tab, container, false);


        pendingrecycle=RootView.findViewById(R.id.pendingrecycle);
        items_list=new ArrayList<>();
        //Api Interface
        api_interface= ApiClient.getRetrofit().create(Api_interface.class);

        pendingrecycle.setLayoutManager(new LinearLayoutManager(getContext()));

        getPendingServiceList();

        return RootView;


    }


    public void getPendingServiceList()
    {

        sp = getContext().getSharedPreferences("UserLog",MODE_PRIVATE);
        String uid= sp.getString(_CONSTANTS.UserId, null);
        String staffid= sp.getString(_CONSTANTS.StaffId, null);

        api_interface.PendingServiceList(staffid).enqueue(new Callback<PendingServiceResponse>() {
            @Override
            public void onResponse(Call<PendingServiceResponse> call, Response<PendingServiceResponse> response) {

                if (response.isSuccessful() && response.code() == 200) {

                    if (response.body().getResponseCode().equalsIgnoreCase("0")) {

                        List<PendingServiceResponse.Result> responseResult = response.body().getResult();

                        for (int i = 0; i < responseResult.size(); i++) {

                            PendingServiceModel items = new PendingServiceModel(
                                    responseResult.get(i).getServiceId(),
                                    responseResult.get(i).getServiceDate(),
                                    responseResult.get(i).getCustomerId(),
                                    responseResult.get(i).getProblemDetails(),
                                    responseResult.get(i).getCustomerName()
                                    );
                            items_list.add(items);

                        }

                        pendingAdapter = new PendingAdapter(getActivity(),items_list);
                        pendingrecycle.setAdapter(pendingAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<PendingServiceResponse> call, Throwable t) {

            }
        });


    }

}
