package com.s14.petshop.model.dtos.brand;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor

public class BrandAddDTO {

    @NotBlank
    private String name;

    @NotNull
    private String logoUrl;
}
