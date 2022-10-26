package com.s14.petshop.controller;

import com.s14.petshop.model.dtos.product.ProductForAddingInCartDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
public class CartController extends AbstractController{

    @PostMapping("/cart")
    public List<ProductForAddingInCartDTO> addProductToCart(@RequestParam(name = "product_id") int productId, @RequestParam int quantity, HttpServletRequest request) {
        checkIfUserIsLogged(request);
        cartService.addProductToCart(request, productId, quantity);
        return (List<ProductForAddingInCartDTO>) request.getSession().getAttribute("cart");
    }
}
