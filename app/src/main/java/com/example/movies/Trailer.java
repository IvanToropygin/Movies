package com.example.movies;

import com.google.gson.annotations.SerializedName;

public class Trailer {

    @SerializedName("name") private String trailerName;
    @SerializedName("url") private String trailerUrl;

    public Trailer(String trailerName, String trailerUrl) {
        this.trailerName = trailerName;
        this.trailerUrl = trailerUrl;
    }

    public String getTrailerName() {
        return trailerName;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    @Override
    public String toString() {
        return "Trailer{" +
                "trailerName='" + trailerName + '\'' +
                ", trailerUrl='" + trailerUrl + '\'' +
                '}';
    }
}
