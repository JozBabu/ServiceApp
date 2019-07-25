package com.essensol.serviceapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.essensol.serviceapp.Adapter.CompletedAdapter;
import com.essensol.serviceapp.Adapter.PendingAdapter;
import com.essensol.serviceapp.R;


public class CompletedTab extends Fragment {

    RecyclerView completedrecycle;
    CompletedAdapter completedAdapter;


    public CompletedTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View RootView = inflater.inflate(R.layout.fragment_completed_tab, container, false);

        completedrecycle = RootView.findViewById(R.id.completedrecycle);
        String data[]={"1","2"};




        completedrecycle.setLayoutManager(new LinearLayoutManager(getContext()));
        completedAdapter = new CompletedAdapter(getContext(),data);
        completedrecycle.setAdapter(completedAdapter);

        return RootView;
    }

}
