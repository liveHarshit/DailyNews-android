package com.liveharshit.android.dailynews.fragments;


import com.liveharshit.android.dailynews.NetworkUtils;
import com.liveharshit.android.dailynews.NewsAdapter;
import com.liveharshit.android.dailynews.NewsItems;
import com.liveharshit.android.dailynews.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class DefaultFragment extends Fragment {

    public DefaultFragment() {
    }

    private static NewsAdapter mAdapter;
    private String url;
    private ListView listView;
    private ProgressBar progressBar;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.news_list, container, false);
        url = getArguments().getString("API_URL");
        Log.d("url on fragment", url);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        listView = (ListView) rootView.findViewById(R.id.news_list);
        mAdapter = new NewsAdapter(getActivity(), 0, new ArrayList<NewsItems>());
        listView.setAdapter(mAdapter);
        NewsAsyncTask task = new NewsAsyncTask();
        task.execute(url);


        return rootView;
    }

    private class NewsAsyncTask extends AsyncTask<String, Void, ArrayList<NewsItems>> {

        @Override
        protected ArrayList<NewsItems> doInBackground(String... strings) {
            ArrayList<NewsItems> newsData = NetworkUtils.fetchNewsData(strings[0]);
            return newsData;
        }

        @Override
        protected void onPostExecute(ArrayList<NewsItems> newsItems) {
            /*mAdapter.clear();*/
            progressBar.setVisibility(View.INVISIBLE);
            if (newsItems != null && !newsItems.isEmpty()) {
                mAdapter.addAll(newsItems);
            }
        }
    }

}
