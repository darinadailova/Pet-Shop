package com.s14.petshop.controller;

import com.s14.petshop.model.dtos.brand.BrandAddDTO;
import com.s14.petshop.model.dtos.brand.BrandDTO;
import com.s14.petshop.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BrandController extends AbstractController {

    @Autowired
    private BrandService brandService;

    @PostMapping("/brands")
    public BrandDTO addBrand(@RequestBody BrandAddDTO dto){
        //todo login and admin
        return brandService.addBrand(dto);
    }
}
