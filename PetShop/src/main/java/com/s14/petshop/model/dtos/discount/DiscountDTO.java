package com.s14.petshop.model.dtos.discount;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class DiscountDTO {

    @NotNull
    private int id;

    @NotBlank
    private String name;

    @NotNull
    @Min(0)
    @Max(70)
    private int percentDiscount;

    @NotNull
    private LocalDateTime startAt;

    @NotNull
    private LocalDateTime endAt;
}
