package com.s14.petshop.controller;

import com.s14.petshop.model.beans.Product;
import com.s14.petshop.model.dtos.ProductDTO;
import com.s14.petshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController extends AbstractController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products/{pid}")
    public ProductDTO getById(@PathVariable int pid) {
        //todo validate login
        return productService.getById(pid);
    }

    @DeleteMapping("/products/{pid}")
    public void deleteProduct(@PathVariable int pid) {
        //todo validate login
        //todo validate admin

    }

    @PostMapping("/products")
    public ProductDTO addProduct(@RequestBody ProductDTO dto) {
        //todo validate login
        //todo validate admin

        return productService.addProduct(dto);
    }
}
