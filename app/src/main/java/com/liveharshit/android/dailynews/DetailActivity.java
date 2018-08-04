package com.liveharshit.android.dailynews;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.ByteArrayOutputStream;

import static java.net.Proxy.Type.HTTP;

import android.Manifest;

public class DetailActivity extends AppCompatActivity {

    private CollapsingToolbarLayout collapsingToolbar;
    private AppBarLayout appBarLayout;
    private String category;
    private String title;
    private String description;
    private String imageUrl;
    private String newsUrl;
    private TextView titleTextView;
    private TextView descriptionTextView;
    private TextView readMoreTextView;
    private FloatingActionButton share;
    private ImageView newsImageView;
    private int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        category = bundle.getString("category");
        title = bundle.getString("title");
        description = bundle.getString("description");
        imageUrl = bundle.getString("imageUrl");
        newsUrl = bundle.getString("newsUrl");

        titleTextView = (TextView) findViewById(R.id.title_text_view);
        descriptionTextView = (TextView) findViewById(R.id.description_text_view);
        readMoreTextView = (TextView) findViewById(R.id.read_more);
        share = (FloatingActionButton) findViewById(R.id.feb_share);
        newsImageView = (ImageView) findViewById(R.id.header);

        titleTextView.setText(title);
        descriptionTextView.setText(description);
        readMoreTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri webPage = Uri.parse(newsUrl);
                Intent intent = new Intent(Intent.ACTION_VIEW, webPage);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkWritePermissionAndShare();
            }
        });

        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(category);

        Picasso.get().load(imageUrl).into(newsImageView);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                //  Vertical offset == 0 indicates appBar is fully expanded.
                if (Math.abs(verticalOffset) > 200) {
                    invalidateOptionsMenu();
                } else {
                    invalidateOptionsMenu();
                }
            }
        });
    }

    private void checkWritePermissionAndShare() {
        if (Build.VERSION.SDK_INT >= 23) {

            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (shouldShowRequestPermissionRationale(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    // Explain to the user why we need to read the contacts
                }

                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

                // MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
                // app-defined int constant that should be quite unique

            } else {
                Bitmap bitmap = ((BitmapDrawable) newsImageView.getDrawable()).getBitmap();
                Uri imageUri = getImageUri(getBaseContext(), bitmap);
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_TEXT, title + "\n" + newsUrl);
                intent.putExtra(Intent.EXTRA_STREAM, imageUri);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

}
