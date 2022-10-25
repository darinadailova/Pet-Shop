package com.s14.petshop.model.dtos.product;

import com.s14.petshop.model.dtos.brand.BrandDTO;
import com.s14.petshop.model.dtos.discount.DiscountDTO;
import com.s14.petshop.model.dtos.images.ImageWithoutProductDTO;
import com.s14.petshop.model.dtos.subcategory.SubcategoryDTO;

import java.util.List;

public class ProductWithoutImagesDTO {

    private int id;
    private String name;
    private double price;
    private String info;
    private SubcategoryDTO subcategory;
    private BrandDTO brand;
    private DiscountDTO discount;
}
