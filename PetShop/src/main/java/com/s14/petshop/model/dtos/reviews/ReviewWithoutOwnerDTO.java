package com.s14.petshop.model.dtos.reviews;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewWithoutOwnerDTO {

    private int id;
    private int rating;
    private String comment;
    private LocalDateTime postedAt;
}