package com.s14.petshop.controller;

import com.s14.petshop.model.dtos.order.OrderResponseDTO;
import com.s14.petshop.model.dtos.product.ProductForAddingInCartDTO;
import com.s14.petshop.model.dtos.product.ProductResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class OrderController extends AbstractController {
    @PostMapping("/cart/order")
    public void makeAnOrder(HttpServletRequest request) {
        int currentUserId = getLoggedUserId(request);
        orderService.makeAnOrder(currentUserId, request.getSession());
    }

    @GetMapping("/orders/{oid}/products")
    public ResponseEntity<List<ProductResponseDTO>> getProducts(@PathVariable int oid, HttpServletRequest request) {

        int uid = getLoggedUserId(request);
        return new ResponseEntity<>(orderService.getProducts(uid, oid), HttpStatus.OK);
    }

    @GetMapping("orders/{oid}" )
    public  ResponseEntity<OrderResponseDTO> getOrder(@PathVariable int oid, HttpServletRequest request) {

        int uid = getLoggedUserId(request);
        return new ResponseEntity<>(orderService.getOrder(uid, oid), HttpStatus.OK);
    }


}