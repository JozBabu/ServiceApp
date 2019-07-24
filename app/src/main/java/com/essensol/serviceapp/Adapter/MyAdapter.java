package com.essensol.serviceapp.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.essensol.serviceapp.Fragments.CompletedTab;
import com.essensol.serviceapp.Fragments.PendingTab;

public class MyAdapter extends FragmentStatePagerAdapter {
    int tabCount;

    public MyAdapter(FragmentManager fm , int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                PendingTab tab1 = new PendingTab();
                return tab1;

            case 1:
                CompletedTab tab2 = new CompletedTab();
                return tab2;

            default:
                return null;




        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
