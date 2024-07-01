package com.shipmnts.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.shipmnts.backend.entities.Book;
import com.shipmnts.backend.entities.Review;
import com.shipmnts.backend.entities.User;
import com.shipmnts.backend.repositories.BookRepository;
import com.shipmnts.backend.repositories.ReviewRepository;
import com.shipmnts.backend.requests.ReviewReq;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private BookRepository bookRepository;

    public Review createReview(ReviewReq review) {
        // UserId from Application Context
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Book book = bookRepository.findById(review.getBookId()).orElse(null);
        return reviewRepository.save(getReviewFromReviewReq(review, user, book));
    }

    public List<Review> getAllReviews(PageRequest pageRequest) {
        Page<Review> pages = reviewRepository.findAll(pageRequest);
        return pages.getContent();
    }

    public Review getReviewById(String reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    public Review updateReview(String reviewId, Review reviewDetails) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review != null) {
            review.setComment(reviewDetails.getComment());
            review.setRating(reviewDetails.getRating());
            return reviewRepository.save(review);
        }
        return null;
    }

    public void deleteReview(String reviewId) {
        System.out.println("Deleting review with id: " + reviewId);
        reviewRepository.deleteById(reviewId);
    }

    private Review getReviewFromReviewReq(ReviewReq review, User user, Book book) {
        return new Review(review.getComment(),
                review.getRating(), book, user);
    }

}
