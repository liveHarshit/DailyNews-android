package com.liveharshit.android.dailynews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.liveharshit.android.dailynews.fragments.DefaultFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends ArrayAdapter<NewsItems> {


    public NewsAdapter(@NonNull Context context, int resource, @NonNull List<NewsItems> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.news_format, parent, false);
        }
        NewsItems currentNews = getItem(position);
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image_view);
        String imageUrl = currentNews.getImageUrl();
        Picasso.get()
                .load(imageUrl)
                .resize(128, 128)
                .centerCrop()
                .into(imageView);
        TextView titleTextView = (TextView) listItemView.findViewById(R.id.title_text_view);
        String title = currentNews.getTitle();
        titleTextView.setText(title);

        return listItemView;
    }
}
