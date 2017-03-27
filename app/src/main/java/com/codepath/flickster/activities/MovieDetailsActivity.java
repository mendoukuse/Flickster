package com.codepath.flickster.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.codepath.flickster.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by christine_nguyen on 3/26/17.
 */

public class MovieDetailsActivity extends AppCompatActivity {
    @BindView(R.id.tvDetailsTitle) TextView tvDetailsTitle;
    @BindView(R.id.tvDetailsOverview) TextView tvDetailsOverview;
    @BindView(R.id.ivMovieDetailsImage) ImageView ivMovieDetailsImage;
    @BindView(R.id.tvReleaseDate) TextView tvReleaseDate;
    @BindView(R.id.rbDetailsRating) RatingBar rbRatings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);

        //Extract movie details from intent
        Intent i = getIntent();
        tvDetailsTitle.setText(i.getStringExtra("originalTitle"));
        tvDetailsOverview.setText(i.getStringExtra("overview"));
        tvReleaseDate.setText(String.format("Release Date: %s", i.getStringExtra("releaseDate")));

        Integer rating = (Integer) i.getExtras().get("rating");
        float r = 0;
        if (rating != null) {
            r = (float) rating / 2;
        }
        rbRatings.setRating(r);

        Picasso.with(getApplicationContext()).load(i.getStringExtra("imagePath"))
                .transform(new RoundedCornersTransformation(10, 10))
                .into(ivMovieDetailsImage);

    }

    public void closeDetailsView(View v) {
        finish();
    }
}
