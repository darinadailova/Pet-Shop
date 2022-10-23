package com.s14.petshop.controller;

import com.s14.petshop.model.beans.Subcategory;
import com.s14.petshop.model.dtos.category.CategoryDTO;
import com.s14.petshop.model.dtos.subcategory.SubcategoryAddDTO;
import com.s14.petshop.model.dtos.subcategory.SubcategoryDTO;
import com.s14.petshop.service.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class SubcategoryController extends AbstractController {

    @Autowired
    private SubcategoryService subcategoryService;

    @GetMapping("subcategories/{sid}")
    public SubcategoryDTO getSubcategoryById(@PathVariable int sid, HttpServletRequest request){
        //todo login
        return subcategoryService.getById(sid);
    }

    @PostMapping("/subcategories")
    public SubcategoryDTO addSubcategory(@RequestBody SubcategoryAddDTO dto){
        //todo login admin
        return subcategoryService.addSubcategory(dto);
    }

    @GetMapping("/subcategories")
    public List<CategoryDTO> getAllSubcategories (){
        //todo login
        return subcategoryService.getAllSubcategories();
    }

    @DeleteMapping("/subcategories/{sid}")
    public boolean deleteCategory(@PathVariable int sid){
        //todo login admin
       return subcategoryService.deleteSubcategory(sid);
    }
}
