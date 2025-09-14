package com.phegon.FoodApp.review.services;

import com.phegon.FoodApp.response.Response;
import com.phegon.FoodApp.review.dtos.ReviewDTO;

import java.util.List;

public interface ReviewService {
    Response<ReviewDTO> createReview(ReviewDTO reviewDTO);
    Response<List<ReviewDTO>> getReviewsForMenu(Long menuId);
    Response<Double> getAverageRating(Long menuId);
}
