package com.s14.petshop.model.dtos.category;

import com.s14.petshop.model.beans.Subcategory;
import com.s14.petshop.model.dtos.subcategory.SubcategoryWithoutCategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    @NotNull
    private int id;

    @NotBlank
    private String name;


    private List<SubcategoryWithoutCategoryDTO> subcategories;
}
