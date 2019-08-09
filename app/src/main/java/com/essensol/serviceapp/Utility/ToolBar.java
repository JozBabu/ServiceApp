package com.essensol.serviceapp.Utility;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.essensol.serviceapp.R;

public class ToolBar extends AppCompatActivity {

    public android.support.v7.widget.Toolbar toolbar;

    public android.support.v7.widget.Toolbar getToolBar(){

        return toolbar;
    }

    TextView name;
    ImageView backpress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolbar_new);

        toolbar =findViewById(R.id.toolbar);
        name=findViewById(R.id.appname);
        backpress=(ImageView)findViewById(R.id.backpress) ;

        //Font
        Typeface custom_font2 = Typeface.createFromAsset(getAssets(),  "fonts/OpenSansSemiBold.ttf");
        name.setTypeface(custom_font2);


        backpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               onBackPressed();

            }
        });


    }


}
