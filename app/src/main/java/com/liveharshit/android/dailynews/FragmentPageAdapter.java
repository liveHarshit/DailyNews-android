package com.liveharshit.android.dailynews;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.liveharshit.android.dailynews.fragments.*;


public class FragmentPageAdapter extends FragmentPagerAdapter {

    public FragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new DefaultFragment();
        } else if (position == 1) {
            return new SportsFragment();
        } else if (position == 2) {
            return new EntertainmentFragment();
        } else if (position == 3) {
            return new TechnologyFragment();
        } else if (position == 4) {
            return new ScienceFragment();
        } else if (position == 5) {
            return new HealthFragment();
        } else {
            return new BusinessFragment();
        }
    }

    @Override
    public int getCount() {
        return 7;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Home";
        } else if (position == 1) {
            return "Sports";
        } else if (position == 2) {
            return "Entertainment";
        } else if (position == 3) {
            return "Technology";
        } else if (position == 4) {
            return "Science";
        } else if (position == 5) {
            return "Health";
        } else {
            return "Business";
        }
    }
}
