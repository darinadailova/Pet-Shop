package com.s14.petshop.controller;

import com.s14.petshop.model.dtos.discount.DiscountAddDTO;
import com.s14.petshop.model.dtos.discount.DiscountResponseDTO;
import com.s14.petshop.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
public class DiscountController extends AbstractController {

    @Autowired
    private DiscountService discountService;

    @PostMapping("/discounts")
    public ResponseEntity<DiscountResponseDTO> addDiscount(@Valid @RequestBody DiscountAddDTO dto, HttpServletRequest request) {
        checkIfUserIsLogged(request);
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute(USER_ID);
        userService.isUserAdmin(userId);

        return new ResponseEntity<>(discountService.addDiscount(dto), HttpStatus.OK);
    }

    @GetMapping("/discounts/{did}")
    public ResponseEntity<DiscountResponseDTO> getDiscountById(@PathVariable int did, HttpServletRequest request) {
        checkIfUserIsLogged(request);

        return new ResponseEntity<>(discountService.getById(did), HttpStatus.OK);
    }

    @PutMapping("/discounts/{did}")
    public ResponseEntity<DiscountResponseDTO> editDiscount(@Valid @RequestBody DiscountAddDTO dto, @PathVariable int did, HttpServletRequest request) {
        checkIfUserIsLogged(request);
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute(USER_ID);
        userService.isUserAdmin(userId);

        return new ResponseEntity<>(discountService.editDiscount(dto, did), HttpStatus.OK);
    }
}