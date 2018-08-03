package com.liveharshit.android.dailynews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DescriptionActivity extends AppCompatActivity {
    private String title, description, imageUrl;
    private TextView descriptionTextView;
    private Button backButton;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        Bundle bundle = getIntent().getExtras();
        description = bundle.getString("description");
        imageUrl = bundle.getString("imageUrl");

        backButton = (Button) findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imageView = (ImageView) findViewById(R.id.description_image_view);
        Picasso.get().load(imageUrl).into(imageView);


        descriptionTextView = (TextView) findViewById(R.id.description_text_view);
        descriptionTextView.setText(description);
    }
}
