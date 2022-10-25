package com.s14.petshop.controller;

import com.s14.petshop.model.dtos.category.CategoryDTO;
import com.s14.petshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class CategoryController extends AbstractController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("categories/{cid}")
    public CategoryDTO getCategoryById(@PathVariable int cid, HttpServletRequest request){
        checkIfUserIsLogged(request);

        return categoryService.getById(cid);
    }

    @PostMapping("/categories")
    public CategoryDTO addCategory(@RequestParam String name, HttpServletRequest request){
        checkIfUserIsLogged(request);
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute(USER_ID);
        userService.isUserAdmin(userId);

        return categoryService.addCategory(name);
    }

    @GetMapping("/categories")
    public List<CategoryDTO> getAllCategories (HttpServletRequest request){
        checkIfUserIsLogged(request);

        return categoryService.getAllCategories();
    }

    @DeleteMapping("categories/{cid}")
    public boolean deleteCategory(@PathVariable int cid, HttpServletRequest request){
        checkIfUserIsLogged(request);
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute(USER_ID);
        userService.isUserAdmin(userId);

        return categoryService.deleteCategory(cid);
    }
}
