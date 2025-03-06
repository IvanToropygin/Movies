package com.example.movies;

import com.google.gson.annotations.SerializedName;

public class Review {

    @SerializedName("type") private String type;
    @SerializedName("author") private String author;
    @SerializedName("title") private String title;
    @SerializedName("description") private String content;

    public Review(String type, String author, String title, String content) {
        this.type = type;
        this.author = author;
        this.title = title;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Review{" +
                "type='" + type + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", description='" + content + '\'' +
                '}';
    }
}
