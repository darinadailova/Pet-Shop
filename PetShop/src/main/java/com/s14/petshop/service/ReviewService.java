package com.s14.petshop.service;

import com.s14.petshop.model.beans.Product;
import com.s14.petshop.model.beans.Review;
import com.s14.petshop.model.beans.User;
import com.s14.petshop.model.dtos.reviews.AddingReviewDTO;
import com.s14.petshop.model.dtos.reviews.ReviewResponseDTO;
import com.s14.petshop.model.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReviewService extends AbstractService {
    public ReviewResponseDTO addReview(int loggedUserId, AddingReviewDTO addingReviewDTO, int pid) {
        Product product = productRepository.findProductById(pid)
                .orElseThrow(() -> new NotFoundException("Product not found"));
        User user = userRepository.getById(loggedUserId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        Review review = modelMapper.map(addingReviewDTO, Review.class);
        review.setPostedAt(LocalDateTime.now());
        review.setOwner(user);
        review.setReviewedProduct(product);
        reviewRepository.save(review);
        product.getReviews().add(review);

        ReviewResponseDTO response = modelMapper.map(review, ReviewResponseDTO.class);
        response.setReviewedProductID(product.getId());
        response.setOwnerId(loggedUserId);

        return response;
    }
}
