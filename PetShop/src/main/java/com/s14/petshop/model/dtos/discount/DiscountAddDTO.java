package com.s14.petshop.model.dtos.discount;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor

public class DiscountAddDTO {

    @NotBlank
    @Length(min = 2, max = 45, message = "Discount name must be between 2 and 45 characters")
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
