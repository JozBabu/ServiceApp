package com.essensol.serviceapp.Dialogue;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.essensol.serviceapp.Activity.Home;
import com.essensol.serviceapp.R;


public class DeliveryDialogue extends DialogFragment {

    Spinner delivery_status;
    String array_vehicleNo[] = {"Completed", "Postponed"};
    TextView title;


    public DeliveryDialogue() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View RootView = inflater.inflate(R.layout.fragment_delivery_dialogue, container, false);

        final EditText editText = RootView.findViewById(R.id.inKm);

        title = RootView.findViewById(R.id.title);
        delivery_status = RootView.findViewById(R.id.delivery_status);

        //Font
        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/MontserratBold.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/MontserratMedium.ttf");
        title.setTypeface(custom_font2);

        //Set Value to spinner
        final ArrayAdapter<String> spinner_adapter_day = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, array_vehicleNo);
        spinner_adapter_day.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        delivery_status.setAdapter(spinner_adapter_day);


        if (getArguments() != null && !TextUtils.isEmpty(getArguments().getString("email")))
            editText.setText(getArguments().getString("email"));

        Button btnDone = RootView.findViewById(R.id.submitbtn);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                DialogListener dialogListener = (DialogListener) getActivity();
//                dialogListener.onFinishEditDialog(editText.getText().toString());
                Intent i = new Intent(getContext(), Home.class);
                startActivity(i);

                dismiss();


            }
        });
        Button cncelbutton = RootView.findViewById(R.id.cncelbutton);

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

    public interface DialogListener {
        void onFinishEditDialog(String inputText);
    }

}
