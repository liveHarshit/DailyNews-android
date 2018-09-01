package com.liveharshit.android.dailynews;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;

public class SearchActivity extends AppCompatActivity {
    private String query;
    private static NewsAdapter mAdapter;
    private final String BASE_API_URI = "https://newsapi.org/v2/everything";
    private final String QUERY_PARAM = "q";
    private final String SORT_BY_PARAM = "sortBy";
    private final String KEY_PARAM = "apiKey";
    private ListView listView;
    private ProgressBar progressBar;
    private TextView alertTextView;
    private Toolbar toolbar;
    private WaveSwipeRefreshLayout mWaveSwipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        query = extras.getString("query");
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("DailyNews- " + query);

        Uri builtUri = Uri.parse(BASE_API_URI).buildUpon()
                .appendQueryParameter(QUERY_PARAM, query)
                .appendQueryParameter(SORT_BY_PARAM, "publishedAt")
                .appendQueryParameter(KEY_PARAM, "399c82904bbc41dda0f3ae51acab425d").build();

        final String finalUrl = builtUri.toString();
        Log.d("final url", finalUrl);

        progressBar = findViewById(R.id.progress_bar);
        alertTextView = findViewById(R.id.alert_text_view);
        listView = findViewById(R.id.list_view);
        mAdapter = new NewsAdapter(this, 0, new ArrayList<NewsItems>());
        listView.setAdapter(mAdapter);

        final NewsAsyncTask task = new NewsAsyncTask();

        if (isNetworkConnected()) {
            task.execute(finalUrl);
        } else {
            alertTextView.setText("Check your internet connection! \nPull to refresh.");
            progressBar.setVisibility(View.INVISIBLE);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsAdapter adapter = (NewsAdapter) parent.getAdapter();
                NewsItems currentNews = adapter.getItem(position);
                String title = currentNews.getTitle();
                String description = currentNews.getDescription();
                String imageUrl = currentNews.getImageUrl();
                String newsUrl = currentNews.getNewsUrl();
                Intent intent = new Intent(SearchActivity.this, DetailActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("description", description);
                intent.putExtra("imageUrl", imageUrl);
                intent.putExtra("newsUrl", newsUrl);
                startActivity(intent);
            }
        });

        mWaveSwipeRefreshLayout = findViewById(R.id.main_swipe);
        mWaveSwipeRefreshLayout.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshActivity();
            }
        });


    }

    private void refreshActivity() {
        Intent intent = new Intent(SearchActivity.this, SearchActivity.class);
        intent.putExtra("query", query);
        startActivity(intent);
        finish();
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
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
            } else {
                progressBar.setVisibility(View.INVISIBLE);
                alertTextView.setText("No item found!");
            }
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.homeAsUp) {
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
