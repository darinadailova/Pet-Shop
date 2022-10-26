package com.s14.petshop.model.dtos.reviews;

import com.s14.petshop.model.beans.Product;
import com.s14.petshop.model.beans.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
public class AddingReviewDTO {

    private int rating;

    private String comment;

}
