package com.codepath.flickster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Movie {
    String posterPath;
    String backdropPath;
    String originalTitle;
    String overview;
    String releaseDate;
    Integer rating;
    Integer popularity;

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w780/%s", backdropPath);
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public Integer getRating() {
        return rating;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public Movie(JSONObject jsonObject) throws JSONException{
        this.posterPath = jsonObject.getString("poster_path");
        this.originalTitle = jsonObject.getString("original_title");
        this.overview = jsonObject.getString("overview");
        this.backdropPath = jsonObject.getString("backdrop_path");
        this.releaseDate = jsonObject.getString("release_date");
        this.rating = jsonObject.getInt("vote_average");
        this.popularity = jsonObject.getInt("popularity");
    }

    public static ArrayList<Movie> fromJSONArray(JSONArray array) {
        ArrayList<Movie> movies = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            try {
                movies.add(new Movie(array.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return movies;
    }
}
