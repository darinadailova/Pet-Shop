package com.s14.petshop.controller;

import com.s14.petshop.model.dtos.discount.DiscountAddDTO;
import com.s14.petshop.model.dtos.discount.DiscountDTO;
import com.s14.petshop.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DiscountController extends AbstractController {

    @Autowired
    private DiscountService discountService;

    @PostMapping("/discounts")
    public DiscountDTO addDiscount(@RequestBody DiscountAddDTO dto){
        //todo login and admin

        return discountService.addDiscount(dto);
    }

    @GetMapping("/discounts/{did}")
    public DiscountDTO getDiscountById(@PathVariable int did ){
        // todo login

        return discountService.getById(did);
    }

    @PutMapping("/discounts/{did}")
    public boolean editDiscount(@RequestBody DiscountAddDTO dto, @PathVariable int did){
        // todo login admin

        return discountService.editDiscount(dto, did);
    }

}
