package com.s14.petshop.model.dtos.images;

import com.s14.petshop.model.dtos.product.ProductWithoutImagesDTO;
import lombok.Data;

@Data
public class ImageResponseDTO {

    private int id;
    private int imageUrl;
    private ProductWithoutImagesDTO owner;
}
