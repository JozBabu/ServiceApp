package com.essensol.serviceapp.Dialogue;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.essensol.serviceapp.Activity.Home;
import com.essensol.serviceapp.Activity.Login;
import com.essensol.serviceapp.R;


public class BackButton extends DialogFragment {


    public BackButton() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        this.getDialog().setCancelable(false);

        return super.onCreateDialog(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_back_button, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




        TextView head=view.findViewById(R.id.head);


        //Font
        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/MontserratBold.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/MontserratMedium.ttf");
        head.setTypeface(custom_font2);


        Button btnDone = view.findViewById(R.id.yesbutton);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             getActivity().moveTaskToBack(true);
                dismiss();

            }
        });

        Button  NoButton =view.findViewById(R.id.NoButton);
        NoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();
            }
        });



        setCancelable(false);
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
