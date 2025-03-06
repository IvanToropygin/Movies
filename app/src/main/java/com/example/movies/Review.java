package com.example.movies;

import com.google.gson.annotations.SerializedName;

public class Review {

    @SerializedName("type") private String type;
    @SerializedName("author") private String author;
    @SerializedName("title") private String title;
    @SerializedName("description") private String description;

    public Review(String type, String author, String title, String description) {
        this.type = type;
        this.author = author;
        this.title = title;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Review{" +
                "type='" + type + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
