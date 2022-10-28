package com.s14.petshop.model.dtos.subcategory;

import com.s14.petshop.model.dtos.category.CategoryWithoutSubsDTO;
import com.s14.petshop.model.dtos.product.ProductWithoutSubDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor

public class SubcategoryResponseDTO {

    private int id;
    private String name;
    private CategoryWithoutSubsDTO category;

    private List<ProductWithoutSubDTO> products;
}
