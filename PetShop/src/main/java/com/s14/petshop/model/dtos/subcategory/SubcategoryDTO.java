package com.s14.petshop.model.dtos.subcategory;

import com.s14.petshop.model.dtos.category.CategoryWithoutSubsDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

public class SubcategoryDTO {
    private int id;
    private String name;
    private CategoryWithoutSubsDTO category;
}
