package com.s14.petshop.controller;

import com.s14.petshop.model.beans.Subcategory;
import com.s14.petshop.model.dtos.category.CategoryDTO;
import com.s14.petshop.model.dtos.subcategory.SubcategoryAddDTO;
import com.s14.petshop.model.dtos.subcategory.SubcategoryDTO;
import com.s14.petshop.service.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<SubcategoryDTO> getSubcategoryById(@PathVariable int sid, HttpServletRequest request){
        checkIfUserIsLogged(request);

        return new ResponseEntity<>(subcategoryService.getById(sid), HttpStatus.OK);
    }

    @PostMapping("/subcategories")
    public ResponseEntity<SubcategoryDTO> addSubcategory(@Valid @RequestBody SubcategoryAddDTO dto, HttpServletRequest request){
        checkIfUserIsLogged(request);
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute(USER_ID);
        userService.isUserAdmin(userId);

        return new ResponseEntity<>(subcategoryService.addSubcategory(dto), HttpStatus.OK);
    }

    @GetMapping("/subcategories")
    public ResponseEntity<List<CategoryDTO>> getAllSubcategories(HttpServletRequest request){
        checkIfUserIsLogged(request);

        return new ResponseEntity<>(subcategoryService.getAllSubcategories(), HttpStatus.OK);
    }

    @DeleteMapping("/subcategories/{sid}")
    public ResponseEntity<SubcategoryDTO> deleteCategory(@PathVariable int sid, HttpServletRequest request){
        checkIfUserIsLogged(request);
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute(USER_ID);
        userService.isUserAdmin(userId);

       return new ResponseEntity<>(subcategoryService.deleteSubcategory(sid), HttpStatus.OK);
    }
}