package com.s14.petshop.controller;

import com.s14.petshop.model.dtos.brand.BrandResponseDTO;
import com.s14.petshop.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class BrandController extends AbstractController {

    @Autowired
    private BrandService brandService;

    @PostMapping("/brands")
    public ResponseEntity<BrandResponseDTO> addBrand(@RequestParam(value = "file") MultipartFile file, @RequestParam(value = "name") String name,
                                                     HttpServletRequest request){
        checkIfUserIsLogged(request);
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute(USER_ID);
        userService.isUserAdmin(userId);

        return new ResponseEntity<>(brandService.addBrand(file, name), HttpStatus.OK);
    }
}
