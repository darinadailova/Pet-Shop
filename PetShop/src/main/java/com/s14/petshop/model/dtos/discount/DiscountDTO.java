package com.s14.petshop.model.dtos.discount;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class DiscountDTO {

    private int id;
    private String name;
    private int percentDiscount;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
}
