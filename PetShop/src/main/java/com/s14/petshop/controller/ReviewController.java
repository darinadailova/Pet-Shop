package com.s14.petshop.controller;

import com.s14.petshop.model.dtos.reviews.AddingReviewDTO;
import com.s14.petshop.model.dtos.reviews.ReviewResponseDTO;
import com.s14.petshop.model.exceptions.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class ReviewController extends AbstractController{

    @PostMapping("/products/{pid}/review")
    public ResponseEntity<ReviewResponseDTO> addReview(@Valid @RequestBody AddingReviewDTO addingReviewDTO,
                                                       HttpServletRequest request, @PathVariable int pid) {

        int loggedUserId = getLoggedUserId(request);
        if (addingReviewDTO.getRating() < 1 || addingReviewDTO.getRating() > 5) {
            throw new BadRequestException("Rating must be a number between 1 and 5");
        }
        return new ResponseEntity<>(reviewService.addReview(loggedUserId, addingReviewDTO, pid), HttpStatus.OK);
    }
}
