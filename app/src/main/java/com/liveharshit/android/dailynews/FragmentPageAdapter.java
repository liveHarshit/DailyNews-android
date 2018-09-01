package com.liveharshit.android.dailynews;

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

    public boolean isFinished = true;

    private static final String BASE_API_URL = "https://newsapi.org/v2/top-headlines?country=in&apiKey=399c82904bbc41dda0f3ae51acab425d";

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            Bundle bundle = new Bundle();
            bundle.putString("API_URL", BASE_API_URL);
            bundle.putString("category", "Headline");
            Fragment headlineFragment = new HeadlineFragment();
            headlineFragment.setArguments(bundle);
            return headlineFragment;
        } else if (position == 1) {
            Bundle bundle = new Bundle();
            bundle.putString("API_URL", BASE_API_URL + "&category=sports");
            bundle.putString("category", "Sports");
            Fragment sportsFragment = new SportsFragment();
            sportsFragment.setArguments(bundle);
            return sportsFragment;
        } else if (position == 2) {
            Bundle bundle = new Bundle();
            bundle.putString("API_URL", BASE_API_URL + "&category=entertainment");
            bundle.putString("category", "Entertainment");
            Fragment entertainmentFragment = new EntertainmentFragment();
            entertainmentFragment.setArguments(bundle);
            return entertainmentFragment;
        } else if (position == 3) {
            Bundle bundle = new Bundle();
            bundle.putString("API_URL", BASE_API_URL + "&category=technology");
            bundle.putString("category", "Technology");
            Fragment technologyFragment = new TechnologyFragment();
            technologyFragment.setArguments(bundle);
            return technologyFragment;
        } else if (position == 4) {
            Bundle bundle = new Bundle();
            bundle.putString("API_URL", BASE_API_URL + "&category=science");
            bundle.putString("category", "Science");
            Fragment scienceFragment = new ScienceFragment();
            scienceFragment.setArguments(bundle);
            return scienceFragment;
        } else if (position == 5) {
            Bundle bundle = new Bundle();
            bundle.putString("API_URL", BASE_API_URL + "&category=health");
            bundle.putString("category", "Health");
            Fragment healthFragment = new HealthFragment();
            healthFragment.setArguments(bundle);
            return healthFragment;
        } else {
            Bundle bundle = new Bundle();
            bundle.putString("API_URL", BASE_API_URL + "&category=business");
            bundle.putString("category", "Business");
            Fragment businessFragment = new BusinessFragment();
            businessFragment.setArguments(bundle);
            return businessFragment;
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
