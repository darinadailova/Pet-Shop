package com.s14.petshop.controller;

import com.s14.petshop.model.dtos.address.AddingAddress;
import com.s14.petshop.model.dtos.user.UserWithoutPassAndIsAdminDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AddressController extends AbstractController{

    @PostMapping("/user/profile/add-address")
    public void addAddress(@RequestBody AddingAddress address, HttpServletRequest request) {
        UserWithoutPassAndIsAdminDTO currentUser = userController.getUserById(getLoggedUserId(request));
        addressService.addAddress(address, currentUser);
    }
}
