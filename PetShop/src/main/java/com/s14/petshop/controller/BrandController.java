package com.s14.petshop.controller;

import com.s14.petshop.model.dtos.brand.BrandAddDTO;
import com.s14.petshop.model.dtos.brand.BrandDTO;
import com.s14.petshop.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
public class BrandController extends AbstractController {

    @Autowired
    private BrandService brandService;

    @PostMapping("/brands")
    public ResponseEntity<BrandDTO> addBrand(@Valid @RequestBody BrandAddDTO dto, HttpServletRequest request) {
        checkIfUserIsLogged(request);
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute(USER_ID);
        userService.isUserAdmin(userId);

        return new ResponseEntity<>(brandService.addBrand(dto), HttpStatus.OK);
    }
}
