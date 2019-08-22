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


    public CompletedTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View RootView = inflater.inflate(R.layout.fragment_completed_tab, container, false);

        completedrecycle = RootView.findViewById(R.id.completedrecycle);

        items_list=new ArrayList<>();

        //Api Interface
        api_interface= ApiClient.getRetrofit().create(Api_interface.class);

        String data[]={"1","2"};


      //  completedrecycle.setLayoutManager(new LinearLayoutManager(getContext()));

      //  getCompletedservicelist();

        return RootView;
    }

    public void getCompletedservicelist()
    {

        sp = getContext().getSharedPreferences("UserLog",MODE_PRIVATE);
        String uid= sp.getString(_CONSTANTS.UserId, null);
        String staffid= sp.getString(_CONSTANTS.StaffId, null);

        api_interface.CompltedServicelist(staffid).enqueue(new Callback<CompletedServiceResponse>() {
            @Override
            public void onResponse(Call<CompletedServiceResponse> call, Response<CompletedServiceResponse> response) {

                if (response.isSuccessful() && response.code() == 200) {

//                    if (response.body().getResponseCode().equalsIgnoreCase("0")) {
//
//                        List<CompletedServiceResponse.Result> responseResult = response.body().getResult();
//
//                        for (int i = 0; i < responseResult.size(); i++) {
//
//                            CompletedServiceModel items = new CompletedServiceModel(
//                                    responseResult.get(i).getServiceId(),
//                                    responseResult.get(i).getServiceDate(),
//                                    responseResult.get(i).getCustomerId(),
//                                    responseResult.get(i).getProblemDetails(),
//                                    responseResult.get(i).getCustomerName()
//                            );
//                            items_list.add(items);
//
//                        }

                    completedAdapter = new CompletedAdapter(getActivity(),items_list);
                    completedrecycle.setAdapter(completedAdapter);
                    }
                }

          //  }

            @Override
            public void onFailure(Call<CompletedServiceResponse> call, Throwable t) {

            }
        });
    }

}
