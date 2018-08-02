package com.liveharshit.android.dailynews.fragments;


import com.liveharshit.android.dailynews.NetworkUtils;
import com.liveharshit.android.dailynews.NewsItems;
import com.liveharshit.android.dailynews.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class DefaultFragment extends Fragment {

    public DefaultFragment() {
        // Required empty public constructor
    }

    private String jsonResponse;
    private String url;
    private TextView alertTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.news_list, container, false);
        url = getArguments().getString("API_URL");

        alertTextView = (TextView) rootView.findViewById(R.id.alert_text_view);
        alertTextView.setText(jsonResponse);

        NewsAsyncTask task = new NewsAsyncTask();
        task.execute(url);

        return rootView;
    }

    private class NewsAsyncTask extends AsyncTask<String, Void, String /*ArrayList<NewsItems>*/> {

        @Override
        protected /*ArrayList<NewsItems>*/ String doInBackground(String... strings) {
            String jsonResponse = NetworkUtils.fetchNewsData(strings[0]);
            return jsonResponse;
        }

        @Override
        protected void onPostExecute(String jsonResponse/*ArrayList<NewsItems> newsItems*/) {
            alertTextView.setText(jsonResponse);
        }
    }

}
