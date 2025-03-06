package com.example.movies;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewsViewHolder> {

    public static final String TAG = "ReviewsAdapter";
    public static final String POSITIVE_TYPE = "POSITIVE";
    public static final String NEGATIVE_TYPE = "NEGATIVE";
    public static final String NEUTRAL_TYPE = "NEUTRAL";

    private List<Review> reviews = new ArrayList<>();

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReviewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(
                        R.layout.review_item,
                        parent,
                        false);
        return new ReviewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewsViewHolder holder, int position) {

        Review review = reviews.get(position);
        Log.d(TAG, review.toString());
        holder.textViewReviewTitle.setText(String.valueOf(review.getTitle()));
        holder.textViewReviewAuthor.setText(String.valueOf(review.getAuthor()));
        holder.textViewReviewContent.setText(String.valueOf(review.getContent()));

        String type = null;
        try {
            type = review.getType();
        } catch (Exception e) {
            Log.d(TAG, "Error on get type review: " + e);
        }

        int backgroundColorResId = android.R.color.holo_blue_light;
        if (type != null && !type.equals(NEUTRAL_TYPE)) {
            if (type.equals(POSITIVE_TYPE)) backgroundColorResId = android.R.color.holo_green_light;
            else if (type.equals(NEGATIVE_TYPE))
                backgroundColorResId = android.R.color.holo_red_light;
        }
        int color = ContextCompat.getColor(holder.itemView.getContext(), backgroundColorResId);
        holder.linearLayoutReviewContainer.setBackgroundColor(color);
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    static class ReviewsViewHolder extends RecyclerView.ViewHolder {

        private final LinearLayout linearLayoutReviewContainer;
        private final TextView textViewReviewTitle;
        private final TextView textViewReviewAuthor;
        private final TextView textViewReviewContent;

        public ReviewsViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayoutReviewContainer = itemView.findViewById(R.id.linearLayoutReviewContainer);
            textViewReviewTitle = itemView.findViewById(R.id.textViewReviewTitle);
            textViewReviewAuthor = itemView.findViewById(R.id.textViewReviewAuthor);
            textViewReviewContent = itemView.findViewById(R.id.textViewReviewContent);
        }
    }
}
