package com.example.movies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movie {

    @SerializedName("kinopoiskId") private int id;
    @SerializedName("nameRu") private String name;
    @SerializedName("countries") private List<Country> countries;
    @SerializedName("ratingKinopoisk") private double rating;
    @SerializedName("year") private int year;
    @SerializedName("posterUrl") private String posterUrl;

    public Movie(int id, String name, List<Country> countries, double rating, int year, String posterUrl) {
        this.id = id;
        this.name = name;
        this.countries = countries;
        this.rating = rating;
        this.year = year;
        this.posterUrl = posterUrl;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public double getRating() {
        return rating;
    }

    public int getYear() {
        return year;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countries=" + countries +
                ", rating=" + rating +
                ", year=" + year +
                ", posterUrl='" + posterUrl + '\'' +
                '}';
    }
}
