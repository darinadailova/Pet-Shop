package com.s14.petshop.model.dtos.brand;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor

public class BrandAddDTO {

    @NotBlank
    @Length(min = 2, max = 45, message =  "Brand name must be between 2 and 45 characters")
    private String name;

    @NotNull
    private String logoUrl;
}
