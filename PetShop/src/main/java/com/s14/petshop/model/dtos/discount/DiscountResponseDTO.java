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
public class DiscountResponseDTO {

    private int id;
    private String name;
    private int percentDiscount;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
}
