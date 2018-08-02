package com.liveharshit.android.dailynews;

import android.widget.SearchView;

public class NewsItems {
    private String mTitle;
    private String mDescription;
    private String mNewsUrl;
    private String mImageUrl;

    public NewsItems(String title, String description, String newsUrl, String imageUrl) {
        mTitle = title;
        mDescription = description;
        mNewsUrl = newsUrl;
        mImageUrl = imageUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getNewsUrl() {
        return mNewsUrl;
    }

    public String getImageUrl() {
        return mImageUrl;
    }
}
