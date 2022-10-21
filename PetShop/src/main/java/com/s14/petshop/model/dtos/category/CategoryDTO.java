package com.s14.petshop.model.dtos.category;

import com.s14.petshop.model.beans.Subcategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class CategoryDTO {
    private int id;
    private String name;
    private List<Subcategory> subcategories;
    //todo list of subcategories without categories
}
