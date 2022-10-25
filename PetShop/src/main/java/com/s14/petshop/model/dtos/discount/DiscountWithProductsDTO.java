package com.s14.petshop.model.dtos.discount;

import com.s14.petshop.model.dtos.product.ProductWithoutDiscountDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor

public class DiscountWithProductsDTO {

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

    private List<ProductWithoutDiscountDTO> products;
}
