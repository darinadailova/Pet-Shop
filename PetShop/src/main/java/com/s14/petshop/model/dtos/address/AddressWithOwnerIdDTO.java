package com.s14.petshop.model.dtos.address;

import com.s14.petshop.model.beans.User;
import lombok.Data;

import javax.persistence.*;

@Data
public class AddressWithOwnerIdDTO {

    private int id;
    private String city;
    private String postcode;
    private String streetAddress;
    private String apartmentBuilding;
    private String apartmentNumber;
    private int ownerId;
}
