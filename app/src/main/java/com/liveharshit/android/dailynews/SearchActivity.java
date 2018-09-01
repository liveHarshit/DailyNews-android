package com.liveharshit.android.dailynews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SearchActivity extends AppCompatActivity {
    private String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        query = extras.getString("query");
        setTitle("DailyNews- " + query);
    }
}
