package com.essensol.serviceapp.Dialogue;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.essensol.serviceapp.Activity.Home;
import com.essensol.serviceapp.Model_Classes.StatusList_model;
import com.essensol.serviceapp.R;
import com.essensol.serviceapp.RetrofitUtilits.Api_interface;

import java.util.ArrayList;


public class ProductDeliveryDialogue extends DialogFragment {

    Spinner delivery_status;
    TextView title;
    Api_interface api_interface;
    ArrayAdapter<StatusList_model> sendList_adapter;
    ArrayList<StatusList_model> iteems =new ArrayList<>();
    Button btnDone,cncelbutton;
    String statusid,TaskId;

    public ProductDeliveryDialogue() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View RootView = inflater.inflate(R.layout.fragment_product_delivery_dialogue, container, false);

        final EditText editText = RootView.findViewById(R.id.inKm);

        title = RootView.findViewById(R.id.title);
        delivery_status = RootView.findViewById(R.id.delivery_status);

        //Font
        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/MontserratBold.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/MontserratMedium.ttf");
        title.setTypeface(custom_font2);


        delivery_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                statusid=iteems.get(position).getStatusId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //*Button Clickss*//

        btnDone = RootView.findViewById(R.id.submitbtn);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(getContext(), Home.class);
                startActivity(i);


                //    dismiss();

            }
        });
        cncelbutton = RootView.findViewById(R.id.cncelbutton);

        cncelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();
            }
        });

        setCancelable(false);

        return RootView;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean setFullScreen = false;
        if (getArguments() != null) {
            setFullScreen = getArguments().getBoolean("fullScreen");
        }

        if (setFullScreen)
            setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
