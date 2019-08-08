package com.essensol.serviceapp.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.essensol.serviceapp.R;
import com.essensol.serviceapp.RetrofitUtilits.ApiClient;
import com.essensol.serviceapp.RetrofitUtilits.Api_interface;
import com.essensol.serviceapp.Utility.ToolBar;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends ToolBar {

    LinearLayout login;
    String request;
    ProgressDialog myprog;
    JSONObject jsonString;
    Api_interface api_interface;
    EditText username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_login, contentFrameLayout);

        login=(LinearLayout)findViewById(R.id.login);
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);

      //  api_interface=ApiClient.getRetrofit().create(Api_interface.class);


        //click
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                try {
//
//                    JSONObject values = new JSONObject();
//                    values.put("UserName", username.getText().toString());
//                    values.put("UserPassword", password.getText().toString());
//                    jsonString = new JSONObject();
//                    jsonString.put("Token", "0001");
//                    jsonString.put("call", "GetloginAdminDetails");
//                    jsonString.put("values", values);
//
//                    request = jsonString.toString();
//
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }


//                myprog =new ProgressDialog(Login.this);
//
//                myprog.setTitle("Service App");
//                myprog.setMessage("Logging In");
//                myprog.setCancelable(false);
//                myprog.show();

                Log.e("test","logg"+"  ");
                Intent intent=new Intent(Login.this,Home.class);
                startActivity(intent);
                finish();

            }
        });


    }

    public void Login(final String request)
    {



    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
    }
}
