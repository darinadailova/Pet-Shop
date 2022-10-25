package com.s14.petshop.model.dtos.category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor

public class CategoryWithoutSubsDTO {

    @NotNull
    private int id;

    @NotBlank
    private String name;
}
