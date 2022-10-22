package com.s14.petshop.model.dtos.address;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AddingAddress {

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "Postcode is required")
    private String postcode;

    @NotBlank(message = "Street address is required")
    private String streetAddress;

    private String apartmentBuilding;
    private String apartmentNumber;
}
