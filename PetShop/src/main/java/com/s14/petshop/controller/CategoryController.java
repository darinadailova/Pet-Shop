package com.s14.petshop.controller;

import com.s14.petshop.model.dtos.category.CategoryDTO;
import com.s14.petshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class CategoryController extends AbstractController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("category/{id}")
    public CategoryDTO getCategoryById(@PathVariable int cid){
        return categoryService.getById(cid);
    }
}
