package com.essensol.serviceapp.Dialogue;



import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
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
import com.essensol.serviceapp.RetrofitUtilits.ApiClient;
import com.essensol.serviceapp.RetrofitUtilits.Api_interface;
import com.essensol.serviceapp.RetroftResponseClasses.TaskStatusListresponse;
import com.essensol.serviceapp.RetroftResponseClasses.TaskSubmitResponse;
import com.essensol.serviceapp.Utility._CONSTANTS;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


public class TaskSubmitDialogue extends DialogFragment {

    Spinner delivery_status;
    TextView title;
    Api_interface api_interface;
    ArrayAdapter<StatusList_model> sendList_adapter;
    ArrayList<StatusList_model> iteems =new ArrayList<>();
    Button btnDone,cncelbutton;
    String statusid,TaskId;

    private String errorcode;
    SharedPreferences sp;

    public TaskSubmitDialogue() {
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

        Bundle bundle=this.getArguments();
        assert bundle != null;
        TaskId=  bundle.getString("TaskId");


        //Api Interface
        api_interface= ApiClient.getRetrofit().create(Api_interface.class);

        //Font
        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/MontserratBold.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/MontserratMedium.ttf");
        title.setTypeface(custom_font2);

        GetStatusList();

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


                    TaskSubmitTask();
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


    /*  Get Status list service   */
    public void GetStatusList(){

        api_interface.TaskStatus().enqueue(new Callback<TaskStatusListresponse>() {
            @Override
            public void onResponse(Call<TaskStatusListresponse> call, Response<TaskStatusListresponse> response) {

                if (response.isSuccessful() && response.code() == 200) {

                    if (response.body().getResponseCode().equalsIgnoreCase("0")) {

                        List<TaskStatusListresponse.Result> responseResult = response.body().getResult();

                        for (int i = 0; i < responseResult.size(); i++) {

                            StatusList_model items = new StatusList_model(
                                    responseResult.get(i).getStatusId(),
                                    responseResult.get(i).getStatusName());


                            iteems.add(items);
                        }

                        sendList_adapter = new ArrayAdapter<StatusList_model>(getActivity(),  R.layout.spinnertextview, iteems);
                        delivery_status.setAdapter(sendList_adapter);

                    }

                }

            }

            @Override
            public void onFailure(Call<TaskStatusListresponse> call, Throwable t) {

            }
        });


    }


    /* Task Submit Service */
    public void TaskSubmitTask(){

        sp = getActivity().getSharedPreferences("UserLog", MODE_PRIVATE);
        String uid = sp.getString(_CONSTANTS.UserId, null);
        String staffid = sp.getString(_CONSTANTS.StaffId, null);


        api_interface.TaskSubmit(TaskId,statusid,uid).enqueue(new Callback<TaskSubmitResponse>() {
            @Override
            public void onResponse(Call<TaskSubmitResponse> call, Response<TaskSubmitResponse> response) {

                if (response.isSuccessful() && response.code() == 200) {



                    if (response.body().getResponseCode().equalsIgnoreCase("0")) {


                        List<TaskSubmitResponse.Result> responseResult = response.body().getResult();

                        for (int i = 0; i < responseResult.size(); i++) {

                            dismiss();
                        }

                    }

                }

            }

            @Override
            public void onFailure(Call<TaskSubmitResponse> call, Throwable t) {

            }
        });

    }

}
