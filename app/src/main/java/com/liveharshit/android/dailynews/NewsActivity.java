package com.liveharshit.android.dailynews;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

public class NewsActivity extends AppCompatActivity {
    private MaterialSearchView searchView;
    private ViewPager viewPager;
    private Toolbar toolbar;
    private PagerSlidingTabStrip tabs;
    private TextView alertTextview;
    private ImageView refreshButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        tabs = (PagerSlidingTabStrip) findViewById(R.id.sliding_tabs);
        alertTextview = findViewById(R.id.alert_text_view);
        refreshButton = findViewById(R.id.refresh_button);
        if (isNetworkConnected()) {
            FragmentPagerAdapter adapter = new FragmentPageAdapter(getSupportFragmentManager());
            viewPager.setAdapter(adapter);
            tabs.setViewPager(viewPager);
        } else {
            refreshButton.setVisibility(View.VISIBLE);
            alertTextview.setText("Check your internet connection!");
            refreshButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(NewsActivity.this, NewsActivity.class));
                    finish();
                }
            });
        }

        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                performSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.news_activity, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    private void performSearch(String query) {
        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(searchView.getWindowToken(), 0);
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra("query", query);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
            tabs.setVisibility(View.VISIBLE);
        } else {
            super.onBackPressed();
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

}
