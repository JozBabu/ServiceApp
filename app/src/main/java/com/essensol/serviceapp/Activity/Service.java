package com.essensol.serviceapp.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.essensol.serviceapp.Adapter.MyAdapter;
import com.essensol.serviceapp.R;
import com.essensol.serviceapp.Utility.ToolBar;

public class Service extends ToolBar {

    TabLayout tabLayout;
    ViewPager TabItem;
    Typeface custom_font;
    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_service, contentFrameLayout);

        android.support.v7.widget.Toolbar tb= getToolBar();

        title =tb.findViewById(R.id.appname);
        title.setText("Services");

         custom_font = Typeface.createFromAsset(getAssets(),  "fonts/OpenSansSemiBold.ttf");

        TabItem = findViewById(R.id.pager);
        tabLayout = (TabLayout) findViewById(R.id.serviceTab);

        tabLayout.addTab(tabLayout.newTab().setText("Assigned"));
        tabLayout.addTab(tabLayout.newTab().setText("Completed"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setSelectedTabIndicator(R.color.gradienttop);


        final MyAdapter adapter = new MyAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        TabItem.setAdapter(adapter);

        TabItem.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        for (int i = 0; i < tabLayout.getTabCount(); i++) {

            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {

                TextView tabTextView = new TextView(this);
                tab.setCustomView(tabTextView);

                tabTextView.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
                tabTextView.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;

                tabTextView.setText(tab.getText());

                if (i == 0) {
                    tabTextView.setTextSize(18);
                    tabTextView.setTextColor(Color.parseColor("#6FA4B7"));
                    tabTextView.setTypeface(custom_font);
                }else {

                    tabTextView.setTextColor(Color.parseColor("#AFDBE9"));
//                    tabTextView.setTypeface(custom_font);
                }

            }

            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    TabItem.setCurrentItem(tab.getPosition());

                    ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
                    ViewGroup vgTab = (ViewGroup) vg.getChildAt(tab.getPosition());
                    int tabChildsCount = vgTab.getChildCount();
                    for (int i = 0; i < tabChildsCount; i++) {
                        View tabViewChild = vgTab.getChildAt(i);
                        if (tabViewChild instanceof TextView) {
                            ((TextView) tabViewChild).setTextSize(18);
                            ((TextView) tabViewChild).setTypeface(custom_font);
                            ((TextView) tabViewChild).setTextColor(Color.parseColor("#6FA4B7"));
                        }

                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {


                    ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
                    ViewGroup vgTab = (ViewGroup) vg.getChildAt(tab.getPosition());
                    int tabChildsCount = vgTab.getChildCount();

                    for (int i = 0; i < tabChildsCount; i++) {
                        View tabViewChild = vgTab.getChildAt(i);
                        if (tabViewChild instanceof TextView) {
                            ((TextView) tabViewChild).setTextSize(14);
                            ((TextView) tabViewChild).setTextColor(Color.parseColor("#AFDBE9"));

                        }
}

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Service.this,Home.class);
        startActivity(intent);
    }
}
