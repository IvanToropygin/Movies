package com.example.movies;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("films/collections?type=TOP_POPULAR_MOVIES")
    Single<MovieResponse> loadMovies(@Query("page") int page);

    @GET("films/{kinopoiskId}/videos")
    Single<TrailersResponse> loadTrailers(@Path("kinopoiskId") long kinopoiskId);

    @GET("films/{kinopoiskId}/reviews?order=DATE_DESC")
    Single<ReviewsResponse> loadReviews(@Path("kinopoiskId") long kinopoiskId);
}
