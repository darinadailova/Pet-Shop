package com.s14.petshop.model.dtos.category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

public class CategoryWithoutSubsDTO {
    private int id;
    private String name;
}
