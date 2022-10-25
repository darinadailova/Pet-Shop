package com.s14.petshop.model.dtos.subcategory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor

public class SubcategoryAddDTO {
    @NotBlank
    @Length(min = 2, max = 45, message =  "Subcategory name must be between 2 and 45 characters")
    private String name;

    @NotNull
    private int categoryId;
}
