package com.codepath.flickster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.flickster.R;
import com.codepath.flickster.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by christine_nguyen on 3/23/17.
 */

public class MovieArrayAdapter extends ArrayAdapter<Movie> {
    // Butterknife alternatives
    @BindView(R.id.tvTitle) TextView tvTitle;
    @BindView(R.id.tvOverview) TextView tvOverview;
    @BindView(R.id.ivMovieImage)ImageView ivImage;
    private Unbinder unbinder;

    public MovieArrayAdapter(Context context, List<Movie> movies) {
        super(context, android.R.layout.simple_list_item_1, movies);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // get the data item for position
        Movie movie = getItem(position);

        // check the existing view being reused
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie, parent, false);
        }
        unbinder = ButterKnife.bind(this, convertView);

        // find the views (traditional way)
        // ImageView ivImage = (ImageView) convertView.findViewById(R.id.ivMovieImage);
        // TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        // TextView tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);

        // clear out image from convertView
        ivImage.setImageResource(0);


        // populate data
        tvTitle.setText(movie.getOriginalTitle());
        tvOverview.setText(movie.getOverview());

        String imagePath;
        int orientation = getContext().getResources().getConfiguration().orientation;

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            imagePath = movie.getPosterPath();
            Picasso.with(getContext()).load(imagePath)
                    .placeholder(R.drawable.movie_placeholder)
                    .error(R.drawable.movie_poster_error)
                    .into(ivImage);

        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            imagePath = movie.getBackdropPath();
            Picasso.with(getContext()).load(imagePath)
                    .into(ivImage);
        }


        return convertView;
    }
}
