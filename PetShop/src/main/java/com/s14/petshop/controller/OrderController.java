package com.s14.petshop.controller;

import com.s14.petshop.model.dtos.product.ProductForAddingInCartDTO;
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
}