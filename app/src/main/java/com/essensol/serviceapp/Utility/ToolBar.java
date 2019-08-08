package com.essensol.serviceapp.Utility;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.essensol.serviceapp.R;

public class ToolBar extends AppCompatActivity {

    public android.support.v7.widget.Toolbar toolbar;

    public android.support.v7.widget.Toolbar getToolBar(){

        return toolbar;
    }

    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
        toolbar =findViewById(R.id.toolbar);
        name=findViewById(R.id.appname);

        //Font
        Typeface custom_font2 = Typeface.createFromAsset(getAssets(),  "fonts/MontserratBold.ttf");
        name.setTypeface(custom_font2);

    }
}
