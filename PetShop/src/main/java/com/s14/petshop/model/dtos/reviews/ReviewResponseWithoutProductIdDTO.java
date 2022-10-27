package com.s14.petshop.model.dtos.reviews;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewResponseWithoutProductIdDTO {

    private int id;
    private int rating;
    private String comment;
    private LocalDateTime postedAt;
    private int ownerId;
}
