package com.s14.petshop.controller;

import com.s14.petshop.model.dtos.products.ProductAddDTO;
import com.s14.petshop.model.dtos.products.ProductDTO;
import com.s14.petshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public boolean deleteProduct(@PathVariable int pid) {
        //todo validate login
        //todo validate admin
        return productService.deleteProduct(pid);
    }

    @PostMapping("/products")
    public ProductDTO addProduct(@RequestBody ProductAddDTO dto) {
        //todo validate login
        //todo validate admin
        return productService.addProduct(dto);
    }

    @GetMapping("/product/search/{name}")
    public ProductDTO searchProductByName(@PathVariable String name) {
        ProductDTO result = productService.searchWithName(name);
        return result;
    }

    @PostMapping("/product/{pid}/discount/{did}")
    public ProductDTO addDiscountToProduct(@PathVariable int pid, @PathVariable int did) {
        //todo validate login and admin

        ProductDTO productDTO = productService.addDiscountToProduct(pid,did);
        return productDTO;
    }

}
