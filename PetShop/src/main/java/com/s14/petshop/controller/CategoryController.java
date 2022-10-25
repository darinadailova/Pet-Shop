package com.s14.petshop.controller;

import com.s14.petshop.model.dtos.category.CategoryDTO;
import com.s14.petshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class CategoryController extends AbstractController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("categories/{cid}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable int cid, HttpServletRequest request){
        checkIfUserIsLogged(request);

        return new ResponseEntity<>(categoryService.getById(cid), HttpStatus.OK);
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryDTO> addCategory(@RequestParam String name, HttpServletRequest request){
        checkIfUserIsLogged(request);
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute(USER_ID);
        userService.isUserAdmin(userId);

        return new ResponseEntity<>(categoryService.addCategory(name), HttpStatus.OK);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> getAllCategories (HttpServletRequest request){
        checkIfUserIsLogged(request);

        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @DeleteMapping("categories/{cid}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable int cid, HttpServletRequest request){
        checkIfUserIsLogged(request);
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute(USER_ID);
        userService.isUserAdmin(userId);

        return new ResponseEntity<>(categoryService.deleteCategory(cid), HttpStatus.OK);
    }
}
