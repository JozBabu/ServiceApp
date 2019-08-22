package com.essensol.serviceapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toolbar;

import com.essensol.serviceapp.R;
import com.essensol.serviceapp.Utility.ToolBar;

public class WorkReport extends ToolBar {

    LinearLayout submit,Pricelayout,accesorieslay;
    Spinner status;
    String array_status[]={"status","Bring Back","Postponed","Close"};
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_work_report, contentFrameLayout);

        android.support.v7.widget.Toolbar tb= getToolBar();

        title =tb.findViewById(R.id.appname);
        title.setText("Work Report");

        submit=(LinearLayout)findViewById(R.id.submit);
        Pricelayout=(LinearLayout)findViewById(R.id.Pricelayout);
        accesorieslay=(LinearLayout)findViewById(R.id.accesorieslay);

        status=(Spinner)findViewById(R.id.status);

        final ArrayAdapter<String> spinner_adapter_day = new ArrayAdapter<String>(getApplicationContext() ,
                R.layout.spinnertextview, array_status);

        spinner_adapter_day.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status.setAdapter(spinner_adapter_day);

        status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position==3)
                {
                    Pricelayout.setVisibility(View.VISIBLE);
                    accesorieslay.setVisibility(View.GONE);
                }
                else if (position==1)
                {
                    Pricelayout.setVisibility(View.GONE);
                    accesorieslay.setVisibility(View.VISIBLE);
                }
                else
                {
                    Pricelayout.setVisibility(View.GONE);
                    accesorieslay.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(WorkReport.this,Home.class);
                startActivity(intent);
            }
        });


    }
}
