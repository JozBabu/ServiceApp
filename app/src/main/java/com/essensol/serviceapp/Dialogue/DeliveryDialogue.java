package com.essensol.serviceapp.Dialogue;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    String array_vehicleNo[]={"Completed","Postponed"};
    TextView title;

    public DeliveryDialogue() {

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
        return inflater.inflate(R.layout.activity_delivery_dialogue, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        final EditText editText = view.findViewById(R.id.inKm);

        title=view.findViewById(R.id.title);
        delivery_status=view.findViewById(R.id.delivery_status);

        //Font
        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/MontserratBold.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/MontserratMedium.ttf");
        title.setTypeface(custom_font2);

        //Set Value to spinner
        final ArrayAdapter<String> spinner_adapter_day = new ArrayAdapter<String>(getContext() ,
                android.R.layout.simple_spinner_item, array_vehicleNo);
        spinner_adapter_day.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        delivery_status.setAdapter(spinner_adapter_day);


        if (getArguments() != null && !TextUtils.isEmpty(getArguments().getString("email")))
            editText.setText(getArguments().getString("email"));

        Button btnDone = view.findViewById(R.id.submitbtn);
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
        Button cncelbutton= view.findViewById(R.id.cncelbutton);

        cncelbutton.setOnClickListener(new View.OnClickListener() {
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

        Log.e("API123", "onCreate");

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
