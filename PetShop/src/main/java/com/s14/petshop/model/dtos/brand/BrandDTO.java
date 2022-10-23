package com.s14.petshop.model.dtos.brand;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

public class BrandDTO {
    private int id;
    private String name;
    private String logoUrl;
}
