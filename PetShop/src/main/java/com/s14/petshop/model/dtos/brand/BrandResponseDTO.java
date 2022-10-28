package com.s14.petshop.model.dtos.brand;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor

public class BrandResponseDTO {

    private int id;
    private String name;
    private String logoUrl;
}
