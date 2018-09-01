package com.liveharshit.android.dailynews;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    private String query;
    private static NewsAdapter mAdapter;
    private final String BASE_API_URI = "https://newsapi.org/v2/everything";
    private final String QUERY_PARAM = "q";
    private final String SORT_BY_PARAM = "sortBy";
    private final String KEY_PARAM = "apiKey";
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        query = extras.getString("query");
        setTitle("DailyNews- " + query);

        Uri builtUri = Uri.parse(BASE_API_URI).buildUpon()
                .appendQueryParameter(QUERY_PARAM, query)
                .appendQueryParameter(SORT_BY_PARAM, "publishedAt")
                .appendQueryParameter(KEY_PARAM, "399c82904bbc41dda0f3ae51acab425d").build();

        String finalUrl = builtUri.toString();
        Log.d("fianl url", finalUrl);

        listView = findViewById(R.id.list_view);
        mAdapter = new NewsAdapter(this, 0, new ArrayList<NewsItems>());
        listView.setAdapter(mAdapter);

        NewsAsyncTask task = new NewsAsyncTask();
        task.execute(finalUrl);

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
            if (newsItems != null && !newsItems.isEmpty()) {
                mAdapter.addAll(newsItems);
            }
        }
    }
}
