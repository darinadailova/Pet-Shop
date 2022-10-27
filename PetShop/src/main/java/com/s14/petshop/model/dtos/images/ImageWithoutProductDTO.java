package com.s14.petshop.model.dtos.images;

import com.s14.petshop.model.dtos.product.ProductWithoutImagesDTO;
import lombok.Data;

import java.util.List;

@Data
public class ImageWithoutProductDTO {

    private int id;
    private String imageUrl;
    //private List<ProductWithoutImagesDTO> products;
}
