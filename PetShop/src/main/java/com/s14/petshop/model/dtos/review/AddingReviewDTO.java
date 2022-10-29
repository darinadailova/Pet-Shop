package com.s14.petshop.model.dtos.review;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class AddingReviewDTO {

    @NotNull
    private int rating;

    private String comment;

}
