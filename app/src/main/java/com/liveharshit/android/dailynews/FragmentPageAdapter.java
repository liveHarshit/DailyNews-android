package com.liveharshit.android.dailynews;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.liveharshit.android.dailynews.fragments.*;


public class FragmentPageAdapter extends FragmentPagerAdapter {

    public FragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    Bundle bundle = new Bundle();
    Fragment defaultFragment = new DefaultFragment();


    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            bundle.putString("url", "This is the url");

            defaultFragment.setArguments(bundle);
            return defaultFragment;
        } else if (position == 1) {
            return defaultFragment;
        } else if (position == 2) {
            return defaultFragment;
        } else if (position == 3) {
            return defaultFragment;
        } else if (position == 4) {
            return defaultFragment;
        } else if (position == 5) {
            return defaultFragment;
        } else {
            return defaultFragment;
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
            return "Headlines";
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
