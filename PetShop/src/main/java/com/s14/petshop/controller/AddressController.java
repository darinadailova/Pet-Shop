package com.s14.petshop.controller;

import com.s14.petshop.model.dtos.address.AddingAddress;
import com.s14.petshop.model.dtos.address.AddressWithOwnerIdDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
public class AddressController extends AbstractController{

    @PostMapping("/user/profile/add-address")
    public ResponseEntity<AddressWithOwnerIdDTO> addAddress(@Valid @RequestBody AddingAddress address, HttpServletRequest request) {
        int currentUserId = getLoggedUserId(request);
        return new ResponseEntity<>(addressService.addAddress(address, currentUserId), HttpStatus.CREATED);
    }

    @GetMapping("/users/{uid}/get-all-addresses")
    public ResponseEntity<List<AddingAddress>> getAllAddressesByUserId(@PathVariable int uid) {
        return new ResponseEntity<>(addressService.getAllAddressesByUserId(uid), HttpStatus.OK);
    }
}
