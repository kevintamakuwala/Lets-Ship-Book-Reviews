package com.shipmnts.backend.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.shipmnts.backend.entities.Review;
import com.shipmnts.backend.requests.ReviewReq;
import com.shipmnts.backend.responses.ReviewRes;
import com.shipmnts.backend.services.ReviewService;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // POST /reviews
    @PostMapping
    public ResponseEntity<ReviewRes> createReview(@RequestBody ReviewReq review) {
        Review review2 = reviewService.createReview(review);
        return new ResponseEntity<>(convertReviewToReviewRes(review2), HttpStatus.CREATED);
    }

    // Get All Reviews with Pagination /reviews?page=1&size=10
    @GetMapping
    public ResponseEntity<List<ReviewRes>> getAllReviews(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        List<Review> reviews = reviewService.getAllReviews(PageRequest.of(page, size));

        return new ResponseEntity<>((convertReviewsToReviewRes(reviews)), HttpStatus.OK);

    }

    // Get a Single Review by review_id /reviews/{review_id}
    @GetMapping("/{review_id}")
    public ResponseEntity<ReviewRes> getReviewById(@PathVariable("review_id") String reviewId) {
        Review review = reviewService.getReviewById(reviewId);
        return review != null ? new ResponseEntity<>(convertReviewToReviewRes(review), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Update a Review by review_id PUT /reviews/{review_id}
    @PutMapping("/{review_id}")
    public ResponseEntity<ReviewRes> updateReview(
            @PathVariable("review_id") String reviewId,
            @RequestBody Review reviewDetails) {

        Review review = reviewService.updateReview(reviewId, reviewDetails);
        return review != null ? new ResponseEntity<>(convertReviewToReviewRes(review), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete a Review by review_id DELETE /reviews/{review_id}
    @DeleteMapping("/{review_id}")
    public ResponseEntity<Void> deleteReview(@PathVariable("review_id") String reviewId) {
        reviewService.deleteReview(reviewId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // mapper methods
    private ReviewRes convertReviewToReviewRes(Review review) {
        return new ReviewRes(review.getId(), review.getBook().getId(), review.getComment(), review.getRating());
    }

    private List<ReviewRes> convertReviewsToReviewRes(List<Review> reviews) {
        return reviews.stream()
                .map(review -> new ReviewRes(review.getId(), review.getBook().getId(), review.getComment(),
                        review.getRating()))
                .collect(Collectors.toList());
    }
}
