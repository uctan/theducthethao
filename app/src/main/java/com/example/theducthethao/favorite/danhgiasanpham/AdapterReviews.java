package com.example.theducthethao.favorite.danhgiasanpham;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theducthethao.R;

import java.util.List;

public class AdapterReviews extends RecyclerView.Adapter<AdapterReviews.ViewHolder> {

    private List<ReviewItem> reviewItems;

    public AdapterReviews(List<ReviewItem> reviewItems) {
        this.reviewItems = reviewItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.danhgia_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ReviewItem item = reviewItems.get(position);

        holder.reviewTextView.setText(item.getReview());
        holder.ratingBar.setRating(item.getRating()); // Set rating

        float myRating = item.getRating();
        String message = item.getReview();
    }

    @Override
    public int getItemCount() {
        return reviewItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        RatingBar ratingBar;
        TextView reviewTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ratingBar = itemView.findViewById(R.id.ratingBar);
            reviewTextView = itemView.findViewById(R.id.showcmt);
        }
    }
}