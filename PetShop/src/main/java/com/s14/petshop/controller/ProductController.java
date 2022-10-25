package com.s14.petshop.controller;

import com.s14.petshop.model.dtos.product.ProductAddDTO;
import com.s14.petshop.model.dtos.product.ProductDTO;
import com.s14.petshop.model.dtos.product.ProductEditDTO;
import com.s14.petshop.service.ProductService;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
public class ProductController extends AbstractController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products/{pid}")
    public ProductDTO getById(@PathVariable int pid, HttpServletRequest request) {
        checkIfUserIsLogged(request);

        return productService.getById(pid);
    }

    @DeleteMapping("/products/{pid}")
    public boolean deleteProduct(@PathVariable int pid, HttpServletRequest request) {
        checkIfUserIsLogged(request);
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute(USER_ID);
        userService.isUserAdmin(userId);

        return productService.deleteProduct(pid);
    }

    @PostMapping("/products")
    public ProductDTO addProduct(@Valid @RequestBody ProductAddDTO dto, HttpServletRequest request) {
        checkIfUserIsLogged(request);
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute(USER_ID);
        userService.isUserAdmin(userId);

        return productService.addProduct(dto);
    }

    @GetMapping("/product/search/{name}")
    public ProductDTO searchProductByName(@PathVariable String name, HttpServletRequest request) {
        checkIfUserIsLogged(request);

        return productService.searchWithName(name);
    }

    @PostMapping("/products/{pid}/discount/{did}")
    public ProductDTO addDiscountToProduct(@PathVariable int pid, @PathVariable int did, HttpServletRequest request) {
        checkIfUserIsLogged(request);
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute(USER_ID);
        userService.isUserAdmin(userId);

        return productService.addDiscountToProduct(pid,did);
    }

    @PutMapping("/products/{pid}")
    public ProductDTO editProduct(@Valid @RequestBody ProductEditDTO dto, @PathVariable int pid, HttpServletRequest request){
        checkIfUserIsLogged(request);
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute(USER_ID);
        userService.isUserAdmin(userId);

        return productService.editProduct(dto,pid);
    }
}