package com.s14.petshop.controller;

import com.s14.petshop.model.dtos.product.ProductForAddingInCartDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class CartController extends AbstractController{

    @PostMapping("/cart")
    public ResponseEntity<List<ProductForAddingInCartDTO>> addProductToCart(@RequestParam(name = "product_id") int productId,
                                                                            @RequestParam int quantity, HttpServletRequest request) {
        checkIfUserIsLogged(request);
        cartService.addProductToCart(request, productId, quantity);
        return new ResponseEntity<>((List<ProductForAddingInCartDTO>) request.getSession().getAttribute("cart"), HttpStatus.OK);
    }

    @GetMapping("/cart")
    public ResponseEntity<List<ProductForAddingInCartDTO>> getProductsFromCart(HttpServletRequest request){
        checkIfUserIsLogged(request);
        return new ResponseEntity<>(cartService.getProducts(request), HttpStatus.OK);
    }

    @DeleteMapping("/cart")
    public ResponseEntity<List<ProductForAddingInCartDTO>> deleteCart(HttpServletRequest request){
        checkIfUserIsLogged(request);
        return new ResponseEntity<>(cartService.deleteCart(request), HttpStatus.OK);
    }
}
