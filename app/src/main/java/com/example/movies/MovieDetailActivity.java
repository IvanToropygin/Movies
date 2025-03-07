package com.example.movies;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MovieDetailActivity extends AppCompatActivity {

    private static final String EXTRA_MOVIE_KEY = "movie";
    private static final String TAG = "MovieDetailActivity";

    private MovieDetailViewModel viewModel;

    private ImageView imageViewPoster;

    private TextView textViewTitle;
    private TextView textViewYear;
    private TextView textViewCountry;

    private RecyclerView recyclerViewTrailers;
    private TrailersAdapter trailersAdapter;

    private RecyclerView recyclerViewReviews;
    private ReviewsAdapter reviewsAdapter;

    private ImageView imageViewStar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        initViews();

        trailersAdapter = new TrailersAdapter();
        recyclerViewTrailers.setAdapter(trailersAdapter);

        reviewsAdapter = new ReviewsAdapter();
        recyclerViewReviews.setAdapter(reviewsAdapter);

        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE_KEY);

        Glide.with(this)
                .load(movie.getPosterUrl())
                .into(imageViewPoster);

        textViewTitle.setText(movie.getName());
        textViewYear.setText(String.valueOf(movie.getYear()));
        textViewCountry.setText(movie.getCountries().toString());

        viewModel = new ViewModelProvider(this).get(MovieDetailViewModel.class);
        viewModel.loadTrailers(movie.getId());
        viewModel.getTrailers().observe(this, new Observer<List<Trailer>>() {
            @Override
            public void onChanged(List<Trailer> trailers) {
                Log.d(TAG, trailers.toString());
                trailersAdapter.setTrailers(trailers);
            }
        });

        trailersAdapter.setOnTrailerClickListener(new TrailersAdapter.OnTrailerClickListener() {
            @Override
            public void onTrailerClick(Trailer trailer) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Log.d(TAG, trailer.getTrailerUrl());
                intent.setData(Uri.parse(trailer.getTrailerUrl()));
                startActivity(intent);
            }
        });

        viewModel.loadReviews(movie.getId());
        viewModel.getReviews().observe(this, new Observer<List<Review>>() {
            @Override
            public void onChanged(List<Review> reviews) {
                Log.d(TAG, reviews.toString());
                reviewsAdapter.setReviews(reviews);
            }
        });

        Drawable starOn = ContextCompat.getDrawable(MovieDetailActivity.this,
                android.R.drawable.star_big_on);
        Drawable starOff = ContextCompat.getDrawable(MovieDetailActivity.this,
                android.R.drawable.star_big_off);


        viewModel.getFavouriteMovie(movie.getId()).observe(this,
                new Observer<Movie>() {
                    @Override
                    public void onChanged(Movie favouriteMovie) {
                        if (favouriteMovie != null) {
                            imageViewStar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    viewModel.deleteMovieFromFavourite(movie.getId());
                                }
                            });
                            imageViewStar.setImageDrawable(starOn);
                        }
                        else {
                            imageViewStar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    viewModel.insertMovieToFavourite(movie);
                                }
                            });
                            imageViewStar.setImageDrawable(starOff);
                        }
                    }
                });
    }

    private void initViews() {
        imageViewPoster = findViewById(R.id.imageViewPoster);
        imageViewStar = findViewById(R.id.imageViewStar);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewYear = findViewById(R.id.textViewYear);
        textViewCountry = findViewById(R.id.textViewCountry);
        recyclerViewTrailers = findViewById(R.id.recyclerViewTrailers);
        recyclerViewReviews = findViewById(R.id.recyclerViewReviews);
    }

    public static Intent newIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(EXTRA_MOVIE_KEY, movie);
        return intent;
    }
}