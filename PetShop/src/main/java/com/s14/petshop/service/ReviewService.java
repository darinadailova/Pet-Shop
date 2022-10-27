package com.s14.petshop.service;

import com.s14.petshop.model.beans.Product;
import com.s14.petshop.model.beans.Review;
import com.s14.petshop.model.beans.User;
import com.s14.petshop.model.dtos.reviews.AddingReviewDTO;
import com.s14.petshop.model.dtos.reviews.ReviewResponseDTO;
import com.s14.petshop.model.dtos.reviews.ReviewResponseWithoutOwnerIdDTO;
import com.s14.petshop.model.dtos.reviews.ReviewResponseWithoutProductIdDTO;
import com.s14.petshop.model.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<ReviewResponseWithoutProductIdDTO> getAllReviewsByProductId(int pid) {
        Product product = productRepository.findProductById(pid)
                .orElseThrow(() -> new NotFoundException("Product not found"));
        List<Review> reviews = reviewRepository.findAllByReviewedProductId(pid);

        return reviews.stream()
                        .map(r -> modelMapper.map(r, ReviewResponseWithoutProductIdDTO.class))
                        .collect(Collectors.toList());
    }

    public List<ReviewResponseWithoutOwnerIdDTO> getAllReviewsByUserId(int uid) {
        User user = userRepository.getById(uid)
                .orElseThrow(() -> new NotFoundException("User not found"));
        List<Review> reviews = reviewRepository.findAllByOwnerId(uid);

        return reviews.stream()
                .map(r -> modelMapper.map(r, ReviewResponseWithoutOwnerIdDTO.class))
                .collect(Collectors.toList());
    }
}
