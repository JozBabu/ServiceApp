package com.essensol.serviceapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.essensol.serviceapp.R;
import com.essensol.serviceapp.Utility.ToolBar;

public class Profile extends ToolBar {

    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_profile, contentFrameLayout);

        android.support.v7.widget.Toolbar tb= getToolBar();

        title =tb.findViewById(R.id.appname);
        title.setText("Profile");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Profile.this,Home.class);
        startActivity(intent);
    }
}
