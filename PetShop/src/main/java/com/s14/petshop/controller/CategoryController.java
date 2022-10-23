package com.s14.petshop.controller;

import com.s14.petshop.model.dtos.category.CategoryDTO;
import com.s14.petshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController extends AbstractController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("categories/{cid}")
    public CategoryDTO getCategoryById(@PathVariable int cid){
        //todo login admin
        return categoryService.getById(cid);
    }

    @PostMapping("/categories")
    public CategoryDTO addCategory(@RequestParam String name){
        //todo login admin
        return categoryService.addCategory(name);
    }

    @GetMapping("/categories")
    public List<CategoryDTO> getAllCategories (){
        //todo login admin
        return categoryService.getAllCategories();
    }

    @DeleteMapping("categories/{cid}")
    public boolean deleteCategory(@PathVariable int cid){
        //todo login admin
        return categoryService.deleteCategory(cid);
    }
}
