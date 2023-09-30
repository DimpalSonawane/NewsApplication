package com.appdroid.lokshahiapplication.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabsAdapter extends FragmentPagerAdapter {

    int noOfTabs;

    List<Fragment> fragmentList = new ArrayList<>();

    List<String> titles = new ArrayList<>();

    public TabsAdapter(@NonNull FragmentManager fm) { super(fm);}


    @Override
    public Fragment getItem(int position) {
        Fragment fragment = fragmentList.get(position);
        fragment.setRetainInstance(true);
        return fragment;
    }

    @Override
    public int getCount(){ return fragmentList.size();}

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    public  void addFragment(Fragment fragment, String s) {
        fragmentList.add(fragment);
        titles.add(s);
    }
    public void removeTabPage(int position) {
        if (!fragmentList.isEmpty() && position<fragmentList.size()) {
            fragmentList.remove(position);
            notifyDataSetChanged();

        }
    }
}
