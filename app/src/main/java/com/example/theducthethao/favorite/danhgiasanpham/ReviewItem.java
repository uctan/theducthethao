package com.example.theducthethao.favorite.danhgiasanpham;

public class ReviewItem {

    private float rating;
    private String review;

    public ReviewItem(float rating, String review) {
        this.rating = rating;
        this.review = review;
    }

    public float getRating() {
        return rating;
    }

    public String getReview() {
        return review;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setReview(String review) {
        this.review = review;
    }
}

