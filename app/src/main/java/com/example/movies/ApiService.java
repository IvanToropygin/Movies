package com.example.movies;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("films?order=RATING&type=ALL&ratingTo=9")
    Single<MovieResponse> loadMovies(@Query("page") int page);

    @GET("films/{kinopoiskId}/videos")
    Single<TrailersResponse> loadTrailers(@Path("kinopoiskId") long kinopoiskId);
}
