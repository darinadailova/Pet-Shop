package com.s14.petshop.model.dtos.address;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class AddingAddress {

    @NotBlank(message = "City is required")
    @Length(min = 2, max = 45, message = "City name should be between 2 and 45 characters")
    private String city;

    @NotBlank(message = "Postcode is required")
    private String postcode;

    @NotBlank(message = "Street address is required")
    @Length(min = 2, max = 45, message = "Street address should be between 2 and 45 characters")
    private String streetAddress;

    private String apartmentBuilding;
    private String apartmentNumber;
}
