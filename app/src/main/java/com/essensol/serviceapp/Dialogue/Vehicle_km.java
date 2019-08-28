package com.essensol.serviceapp.Dialogue;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.essensol.serviceapp.Activity.Home;
import com.essensol.serviceapp.Activity.Login;
import com.essensol.serviceapp.Model_Classes.VehicleNo_model;
import com.essensol.serviceapp.R;
import com.essensol.serviceapp.RetrofitUtilits.ApiClient;
import com.essensol.serviceapp.RetrofitUtilits.Api_interface;
import com.essensol.serviceapp.RetroftResponseClasses.InsertMeterRedingResponse;
import com.essensol.serviceapp.RetroftResponseClasses.LoginResponse;
import com.essensol.serviceapp.RetroftResponseClasses.VehicleNoResponse;
import com.essensol.serviceapp.Utility.Utils;
import com.essensol.serviceapp.Utility._CONSTANTS;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


public class Vehicle_km extends DialogFragment {

    Api_interface api_interface;
    Spinner vehicleNo;
    ArrayAdapter<VehicleNo_model> sendList_adapter;
    TextView title;
    ArrayList<VehicleNo_model> iteems =new ArrayList<>();
    String vehID="0";
    SharedPreferences sp;
    String type;
    EditText inKm;
    Context activity;
    String Km,vehicleKm;

    public Vehicle_km() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public Vehicle_km(Context context) {
        this.activity=context;
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

        View RootView = inflater.inflate(R.layout.fragment_vehicle_km, container, false);

        //Api Interface
        api_interface= ApiClient.getRetrofit().create(Api_interface.class);

        Bundle bundle=this.getArguments();
        assert bundle != null;
        type=  bundle.getString("Type");
        inKm = RootView.findViewById(R.id.inKm);
        vehicleKm=bundle.getString("VehicleKM");

        Log.e("VehicleKm",""+vehicleKm);

        title=RootView.findViewById(R.id.title);
        vehicleNo=RootView.findViewById(R.id.vehicleNo);

        if(type.equalsIgnoreCase("E")){
            vehicleNo.setEnabled(false);

        }

        //Font
        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/MontserratBold.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/MontserratMedium.ttf");
        title.setTypeface(custom_font2);

        getVehicleNo();

        vehicleNo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                vehID = iteems.get(i).getVehicleId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button btnDone = RootView.findViewById(R.id.submitbtn);
        Button cncelbutton=RootView.findViewById(R.id.cncelbutton);
         Km=inKm.getText().toString();
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(inKm.getText())) {
                    inKm.requestFocus();
                    inKm.setError("Field is Mandatory");
                }
                else {
                    InsertMeterReaading();
                    Intent intent=new Intent(getContext(),Home.class);
                    intent.putExtra("VehicleKM",Km);
                    startActivity(intent);
                    dismiss();
                }


            }
        });

        cncelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();
            }
        });

        LinearLayout parentlay=RootView.findViewById(R.id.parentlay);
        parentlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                dismiss();

            }
        });

        setCancelable(false);
        // Inflate the layout for this fragment
        return RootView;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
            setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    /* Service to Get Vehicle No */
    public void getVehicleNo()
    {
        api_interface.VehcileNo().enqueue(new Callback<VehicleNoResponse>() {
            @Override
            public void onResponse(Call<VehicleNoResponse> call, Response<VehicleNoResponse> response) {

                if (response.isSuccessful() && response.code() == 200) {

                    if (response.body().getResponseCode().equalsIgnoreCase("0")) {

                        List<VehicleNoResponse.Result> responseResult = response.body().getResult();

                        for (int i = 0; i < responseResult.size(); i++) {

                            VehicleNo_model items = new VehicleNo_model(
                                    responseResult.get(i).getVehicleId(),
                                    responseResult.get(i).getLicenseNo());


                            iteems.add(items);
                       }

                            sendList_adapter = new ArrayAdapter<VehicleNo_model>(activity,  R.layout.spinnertextview, iteems);
                            vehicleNo.setAdapter(sendList_adapter);

                        }

                    }
                }


            @Override
            public void onFailure(Call<VehicleNoResponse> call, Throwable t) {

            }
        });

    }

    //Insert MeterReading

    public void InsertMeterReaading()
    {
        sp = getContext().getSharedPreferences("UserLog",MODE_PRIVATE);
        String uid= sp.getString(_CONSTANTS.UserId, null);
        String staffid= sp.getString(_CONSTANTS.StaffId, null);
        String Km=inKm.getText().toString();

        api_interface.InsertmeterReading(staffid,vehID,Km,type,uid).enqueue(new Callback<InsertMeterRedingResponse>() {
            @Override
            public void onResponse(Call<InsertMeterRedingResponse> call, Response<InsertMeterRedingResponse> response) {

                if(response.isSuccessful()&&response.code()==200)
                {
                    if(response.body().getResponseCode().equalsIgnoreCase("0"))
                    {
                        List<InsertMeterRedingResponse.Result> responseResult = response.body().getResult();

                        for(int i=0;i<responseResult.size();i++)
                        {



                           dismiss();

                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<InsertMeterRedingResponse> call, Throwable t) {

            }
        });

    }

}
