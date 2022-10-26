package com.s14.petshop.model.dtos.product;

import lombok.Data;

@Data
public class ProductForAddingInCartDTO {

    private int id;

    private String name;

    private double price;

    private String info;

    private String brandName;

    private int requestedQuantity;
}
