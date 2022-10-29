package com.s14.petshop.model.dtos.review;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewWithoutOwnerDTO {

    private int id;
    private int rating;
    private String comment;
    private LocalDateTime postedAt;
}
