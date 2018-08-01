package com.liveharshit.android.dailynews.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liveharshit.android.dailynews.R;

public class ScienceFragment extends Fragment {
    public ScienceFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.news_list, container, false);

        TextView alertTextView = (TextView) rootView.findViewById(R.id.alert_text_view);
        alertTextView.setText("Science news here!");

        return rootView;
    }
}
