package com.s14.petshop.model.dtos.user;

import com.s14.petshop.model.dtos.address.AddressWithoutOwnerDTO;
import com.s14.petshop.model.dtos.reviews.ReviewWithoutOwnerDTO;
import lombok.Data;

import java.util.List;

@Data
public class UserWithoutPasswordDTO {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String gender;
    private boolean isSubscribed;
    private boolean isAdmin;
    private String profilePictureUrl;
    private List<AddressWithoutOwnerDTO> addresses;
//    private List<ReviewWithoutOwnerDTO> reviews;
}
