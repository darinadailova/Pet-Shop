package com.s14.petshop.model.dtos.product;

import com.s14.petshop.model.dtos.image.ImageWithoutProductDTO;
import com.s14.petshop.model.dtos.brand.BrandResponseDTO;
import com.s14.petshop.model.dtos.discount.DiscountResponseDTO;
import com.s14.petshop.model.dtos.subcategory.SubcategoryWithoutProductsDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class ProductResponseDTO {

    private int id;
    private String name;
    private double price;
    private String info;
    private SubcategoryWithoutProductsDTO subcategory;
    private BrandResponseDTO brand;
    private DiscountResponseDTO discount;

    private List<ImageWithoutProductDTO> images;
}
