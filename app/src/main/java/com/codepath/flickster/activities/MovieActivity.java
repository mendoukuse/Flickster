package com.codepath.flickster.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.codepath.flickster.R;
import com.codepath.flickster.adapters.MovieArrayAdapter;
import com.codepath.flickster.models.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MovieActivity extends AppCompatActivity {
    static final String MOVIE_API_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    @BindView(R.id.lvMovies) ListView lvItems;

    ArrayList<Movie> movies;
    MovieArrayAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        ButterKnife.bind(this);

        lvItems = (ListView) findViewById(R.id.lvMovies);
        movies = new ArrayList<>();
        movieAdapter = new MovieArrayAdapter(this, movies);
        lvItems.setAdapter(movieAdapter);

        // makeAsyncHttpRequest(MOVIE_API_URL);
        makeOkHttpRequest(MOVIE_API_URL);
        setupListViewListener();
    }

    private void makeOkHttpRequest(String url) {
        // This should be a singleton. Find out if this is a singleton out of the box
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url).build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }

                final String responseData = response.body().string();

                MovieActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject json = new JSONObject(responseData);
                            loadMoviesFromApiResponse((JSONArray) json.get("results"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

    }

    private void makeAsyncHttpRequest(String url) {
        AsyncHttpClient client = new AsyncHttpClient();

        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray movieJsonResults = null;

                try {
                    movieJsonResults = response.getJSONArray("results");
                    loadMoviesFromApiResponse(movieJsonResults);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }

    private void loadMoviesFromApiResponse(JSONArray movieJsonResults) {
        movies.addAll(Movie.fromJSONArray(movieJsonResults));
        movieAdapter.notifyDataSetChanged();
        Log.d("DEBUG", movies.toString());
    }

    private void setupListViewListener() {
        lvItems.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent(MovieActivity.this, MovieDetailsActivity.class);
                        Movie m = movies.get(position);
                        i.putExtra("originalTitle", m.getOriginalTitle());
                        i.putExtra("overview", m.getOverview());
                        i.putExtra("rating", m.getRating());
                        i.putExtra("releaseDate", m.getReleaseDate());

                        int orientation = getResources().getConfiguration().orientation;
                        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                            i.putExtra("imagePath", m.getBackdropPath());
                        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                            i.putExtra("imagePath", m.getPosterPath());
                        }

                        startActivity(i);
                    }
                }
        );
    }
}
