package com.s14.petshop.controller;

import com.s14.petshop.model.dtos.discount.DiscountAddDTO;
import com.s14.petshop.model.dtos.discount.DiscountDTO;
import com.s14.petshop.model.dtos.discount.DiscountWithProductsDTO;
import com.s14.petshop.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
public class DiscountController extends AbstractController {

    @Autowired
    private DiscountService discountService;

    @PostMapping("/discounts")
    public DiscountDTO addDiscount(@Valid @RequestBody DiscountAddDTO dto, HttpServletRequest request) {
        checkIfUserIsLogged(request);
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute(USER_ID);
        userService.isUserAdmin(userId);

        return discountService.addDiscount(dto);
    }

    @GetMapping("/discounts/{did}")
    public DiscountDTO getDiscountById(@PathVariable int did, HttpServletRequest request) {
        checkIfUserIsLogged(request);

        return discountService.getById(did);
    }

    @PutMapping("/discounts/{did}")
    public DiscountDTO editDiscount(@Valid @RequestBody DiscountAddDTO dto, @PathVariable int did, HttpServletRequest request) {
        checkIfUserIsLogged(request);
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute(USER_ID);
        userService.isUserAdmin(userId);

        return discountService.editDiscount(dto, did);
    }
}