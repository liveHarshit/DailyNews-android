package com.liveharshit.android.dailynews;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.liveharshit.android.dailynews.fragments.*;

import java.net.MalformedURLException;
import java.net.URL;


public class FragmentPageAdapter extends FragmentPagerAdapter {

    public FragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    public boolean isFinished = true;

    private static final String BASE_API_URL = "https://newsapi.org/v2/top-headlines?country=in&apiKey=399c82904bbc41dda0f3ae51acab425d";

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            Bundle bundle = new Bundle();
            bundle.putString("API_URL", BASE_API_URL);
            bundle.putString("category", "Headline");
            Fragment defaultFragment = new DefaultFragment();
            defaultFragment.setArguments(bundle);
            return defaultFragment;
        } else if (position == 1) {
            Bundle bundle = new Bundle();
            bundle.putString("API_URL", BASE_API_URL + "&category=sports");
            bundle.putString("category", "Sports");
            Fragment defaultFragment = new DefaultFragment();
            defaultFragment.setArguments(bundle);
            return defaultFragment;
        } else if (position == 2) {
            Bundle bundle = new Bundle();
            bundle.putString("API_URL", BASE_API_URL + "&category=entertainment");
            bundle.putString("category", "Entertainment");
            Fragment defaultFragment = new DefaultFragment();
            defaultFragment.setArguments(bundle);
            return defaultFragment;
        } else if (position == 3) {
            Bundle bundle = new Bundle();
            bundle.putString("API_URL", BASE_API_URL + "&category=technology");
            bundle.putString("category", "Technology");
            Fragment defaultFragment = new DefaultFragment();
            defaultFragment.setArguments(bundle);
            return defaultFragment;
        } else if (position == 4) {
            Bundle bundle = new Bundle();
            bundle.putString("API_URL", BASE_API_URL + "&category=science");
            bundle.putString("category", "Science");
            Fragment defaultFragment = new DefaultFragment();
            defaultFragment.setArguments(bundle);
            return defaultFragment;
        } else if (position == 5) {
            Bundle bundle = new Bundle();
            bundle.putString("API_URL", BASE_API_URL + "&category=health");
            bundle.putString("category", "Health");
            Fragment defaultFragment = new DefaultFragment();
            defaultFragment.setArguments(bundle);
            return defaultFragment;
        } else {
            Bundle bundle = new Bundle();
            bundle.putString("API_URL", BASE_API_URL + "&category=business");
            bundle.putString("category", "Business");
            Fragment defaultFragment = new DefaultFragment();
            defaultFragment.setArguments(bundle);
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
