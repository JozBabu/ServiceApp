package com.essensol.serviceapp.Activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.essensol.serviceapp.R;
import com.essensol.serviceapp.RetrofitUtilits.ApiClient;
import com.essensol.serviceapp.RetrofitUtilits.Api_interface;
import com.essensol.serviceapp.RetroftResponseClasses.LoginResponse;
import com.essensol.serviceapp.Utility.ToolBar;
import com.essensol.serviceapp.Utility.Utils;
import com.essensol.serviceapp.Utility._CONSTANTS;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends ToolBar {

    LinearLayout login;
    String request;
   // ProgressDialog myprog;
    JSONObject jsonString;
    RelativeLayout parentlayout;
    Api_interface api_interface;
    EditText username,password;
    SharedPreferences sp ;
    private AlertDialog myprog;
    TextView signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginn);

        login=(LinearLayout)findViewById(R.id.login);
        parentlayout=(RelativeLayout)findViewById(R.id.parentlayout);

        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);




        myprog = new SpotsDialog(getApplicationContext(), R.style.Custom);


        api_interface=ApiClient.getRetrofit().create(Api_interface.class);

        //Fonts
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/MontserratBold.ttf");
        Typeface custom_font2 = Typeface.createFromAsset(getAssets(), "fonts/MontserratMedium.ttf");



        //click
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myprog =new ProgressDialog(Login.this);
//                myprog.setTitle("Service App");
//                myprog.setMessage("Logging In");
//                myprog.setCancelable(false);
                myprog.show();
                Login();
            }
        });

        if (ActivityCompat.checkSelfPermission(Login.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(Login.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Login.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION},1);
            return;
        }

    }

    public void Login(){

        String  uname= username.getText().toString();
        String  Password= password.getText().toString();

        api_interface.Login(uname,Password,"M","123131231231235354").enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if(response.isSuccessful()&&response.code()==200) {
                    myprog.dismiss();

                if(response.body().getResponseCode().equalsIgnoreCase("0")) {

                    List<LoginResponse.Result> responseResult = response.body().getResult();
                    for(int i=0;i<responseResult.size();i++) {

                        switch (responseResult.get(i).getLoginResult()) {

                            case "1":
                                sp=getSharedPreferences("UserLog",MODE_PRIVATE);
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString(_CONSTANTS.UserId,responseResult.get(i).getUserId());
                                editor.putString(_CONSTANTS.StaffId,responseResult.get(i).getStaffId());
                                editor.putBoolean("LoggedUser", true);
                                editor.apply();

                                Intent intent=new Intent(Login.this,Home.class);
                                startActivity(intent);
                                finish();
                                break;

                            case "2":
                                 Utils.setSnackBar(parentlayout,responseResult.get(i).getLoginMsg());
                                 break;
                            case "3":
                                 Utils.setSnackBar(parentlayout,responseResult.get(i).getLoginMsg());
                                 break;
                        }
                    }
                }

                else {
                    myprog.dismiss();
                }
             }
                else {
                    myprog.dismiss();
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                myprog.dismiss();
                Utils.setSnackBar(parentlayout,"Network Error,Please try again.");
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
    }
}
