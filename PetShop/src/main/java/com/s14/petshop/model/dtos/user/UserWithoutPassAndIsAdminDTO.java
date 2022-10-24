package com.s14.petshop.model.dtos.user;

import com.s14.petshop.model.dtos.address.AddressWithoutOwnerDTO;
import com.s14.petshop.model.dtos.ReviewWithoutOwnerDTO;
import lombok.Data;

import java.util.List;

@Data
public class UserWithoutPassAndIsAdminDTO {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String gender;
    private boolean isSubscribed;
    private String profilePictureUrl;
    private List<AddressWithoutOwnerDTO> addresses;
    private List<ReviewWithoutOwnerDTO> reviews;
}
