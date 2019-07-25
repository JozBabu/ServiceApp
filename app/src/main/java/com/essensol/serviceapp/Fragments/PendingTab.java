package com.essensol.serviceapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.essensol.serviceapp.Adapter.PendingAdapter;
import com.essensol.serviceapp.R;

public class PendingTab extends Fragment {

    RecyclerView pendingrecycle;
    PendingAdapter pendingAdapter;


    public PendingTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View RootView = inflater.inflate(R.layout.fragment_pending_tab, container, false);

        String [] data={"1","2"};

        pendingrecycle=RootView.findViewById(R.id.pendingrecycle);

        pendingrecycle.setLayoutManager(new LinearLayoutManager(getContext()));
        pendingAdapter = new PendingAdapter(data,getContext());
        pendingrecycle.setAdapter(pendingAdapter);

        return RootView;
    }

}
