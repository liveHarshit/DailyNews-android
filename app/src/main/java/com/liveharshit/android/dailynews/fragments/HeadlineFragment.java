package com.liveharshit.android.dailynews.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.liveharshit.android.dailynews.DetailActivity;
import com.liveharshit.android.dailynews.NetworkUtils;
import com.liveharshit.android.dailynews.NewsAdapter;
import com.liveharshit.android.dailynews.NewsItems;
import com.liveharshit.android.dailynews.R;

import java.util.ArrayList;

public class HeadlineFragment extends Fragment {

    private static NewsAdapter mAdapter;
    private String url;
    private String category;
    private ListView listView;
    private ProgressBar progressBar;

    public HeadlineFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.news_list, container, false);
        url = getArguments().getString("API_URL");
        category = getArguments().getString("category");
        Log.d("url on fragment", url);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        listView = (ListView) rootView.findViewById(R.id.news_list);
        mAdapter = new NewsAdapter(getActivity(), 0, new ArrayList<NewsItems>());
        listView.setAdapter(mAdapter);
        NewsAsyncTask task = new NewsAsyncTask();
        task.execute(url);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsAdapter adapter = (NewsAdapter) parent.getAdapter();
                NewsItems currentNews = adapter.getItem(position);
                String title = currentNews.getTitle();
                String description = currentNews.getDescription();
                String imageUrl = currentNews.getImageUrl();
                String newsUrl = currentNews.getNewsUrl();
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("description", description);
                intent.putExtra("imageUrl", imageUrl);
                intent.putExtra("newsUrl", newsUrl);
                intent.putExtra("category", category);
                getContext().startActivity(intent);
            }
        });


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
