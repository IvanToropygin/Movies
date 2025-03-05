package com.example.movies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrailersResponse {

    @SerializedName("items") private List<Trailer> trailersList;

    public TrailersResponse(List<Trailer> trailersList) {
        this.trailersList = trailersList;
    }

    public List<Trailer> getTrailersList() {
        return trailersList;
    }

    @Override
    public String toString() {
        return "TrailersList{" +
                "trailersList=" + trailersList +
                '}';
    }
}
