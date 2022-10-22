package com.s14.petshop.model.dtos.address;

import lombok.Data;

@Data
public class AddressWithoutOwnerDTO {

    private int id;
    private String city;
    private String postcode;
    private String streetAddress;
    private String apartmentBuilding;
    private String apartmentNumber;
}
