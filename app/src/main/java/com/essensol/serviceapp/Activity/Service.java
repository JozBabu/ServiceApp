package com.essensol.serviceapp.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.FrameLayout;

import com.essensol.serviceapp.Adapter.MyAdapter;
import com.essensol.serviceapp.R;
import com.essensol.serviceapp.Utility.ToolBar;

public class Service extends ToolBar {

    TabLayout tabLayout;
    ViewPager TabItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_service, contentFrameLayout);
        toolbar.setTitle("Service");

        TabItem= findViewById(R.id.pager);
        tabLayout = (TabLayout) findViewById(R.id.serviceTab);

        tabLayout.addTab(tabLayout.newTab().setText("Pending Services"));
        tabLayout.addTab(tabLayout.newTab().setText("Completed Services"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setSelectedTabIndicator(R.color.gradienttop);


        final MyAdapter adapter = new MyAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        TabItem.setAdapter(adapter);

        TabItem.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TabItem.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
