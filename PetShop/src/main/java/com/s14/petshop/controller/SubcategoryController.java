package com.s14.petshop.controller;

import com.s14.petshop.model.beans.Subcategory;
import com.s14.petshop.model.dtos.category.CategoryDTO;
import com.s14.petshop.model.dtos.subcategory.SubcategoryAddDTO;
import com.s14.petshop.model.dtos.subcategory.SubcategoryDTO;
import com.s14.petshop.service.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
public class SubcategoryController extends AbstractController {

    @Autowired
    private SubcategoryService subcategoryService;

    @GetMapping("subcategories/{sid}")
    public SubcategoryDTO getSubcategoryById(@PathVariable int sid, HttpServletRequest request){
        checkIfUserIsLogged(request);

        return subcategoryService.getById(sid);
    }

    @PostMapping("/subcategories")
    public SubcategoryDTO addSubcategory(@Valid @RequestBody SubcategoryAddDTO dto, HttpServletRequest request){
        checkIfUserIsLogged(request);
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute(USER_ID);
        userService.isUserAdmin(userId);

        return subcategoryService.addSubcategory(dto);
    }

    @GetMapping("/subcategories")
    public List<CategoryDTO> getAllSubcategories(HttpServletRequest request){
        checkIfUserIsLogged(request);

        return subcategoryService.getAllSubcategories();
    }

    @DeleteMapping("/subcategories/{sid}")
    public boolean deleteCategory(@PathVariable int sid, HttpServletRequest request){
        checkIfUserIsLogged(request);
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute(USER_ID);
        userService.isUserAdmin(userId);

       return subcategoryService.deleteSubcategory(sid);
    }
}