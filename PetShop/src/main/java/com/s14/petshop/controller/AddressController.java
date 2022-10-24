package com.s14.petshop.controller;

import com.s14.petshop.model.dtos.address.AddingAddress;
import com.s14.petshop.model.dtos.user.UserWithoutPasswordDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class AddressController extends AbstractController{

    @Autowired
    UserController userController;

    @PostMapping("/user/profile/add-address")
    public void addAddress(@Valid @RequestBody AddingAddress address, HttpServletRequest request) {
        UserWithoutPasswordDTO currentUser = userController.getUserById(getLoggedUserId(request));
        addressService.addAddress(address, currentUser);
    }
}
