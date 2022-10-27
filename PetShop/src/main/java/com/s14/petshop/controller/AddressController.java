package com.s14.petshop.controller;

import com.s14.petshop.model.dtos.address.AddingAddress;
import com.s14.petshop.model.dtos.address.AddressWithOwnerIdDTO;
import com.s14.petshop.model.dtos.user.UserWithoutPasswordDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
public class AddressController extends AbstractController{

    @Autowired
    UserController userController;

    @PostMapping("/user/profile/add-address")
    public ResponseEntity<AddressWithOwnerIdDTO> addAddress(@Valid @RequestBody AddingAddress address, HttpServletRequest request) {
        UserWithoutPasswordDTO currentUser = userController.getUserById(getLoggedUserId(request));
        return new ResponseEntity<>(addressService.addAddress(address, currentUser), HttpStatus.OK);
    }

    @GetMapping("/users/{uid}/get-all-addresses")
    public List<AddingAddress> getAllAddressesByUserId(@PathVariable int uid) {
        return addressService.getAllAddressesByUserId(uid);
    }
}
