package com.s14.petshop.controller;

import com.s14.petshop.model.dtos.product.ProductAddDTO;
import com.s14.petshop.model.dtos.product.ProductDTO;
import com.s14.petshop.model.dtos.product.ProductEditDTO;
import com.s14.petshop.service.ProductService;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
public class ProductController extends AbstractController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products/{pid}")
    public ResponseEntity<ProductDTO> getById(@PathVariable int pid, HttpServletRequest request) {
        checkIfUserIsLogged(request);

        return new ResponseEntity<>(productService.getById(pid), HttpStatus.OK);
    }

    @DeleteMapping("/products/{pid}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable int pid, HttpServletRequest request) {
        checkIfUserIsLogged(request);
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute(USER_ID);
        userService.isUserAdmin(userId);

        return new ResponseEntity<>(productService.deleteProduct(pid), HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDTO> addProduct(@Valid @RequestBody ProductAddDTO dto, HttpServletRequest request) {
        checkIfUserIsLogged(request);
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute(USER_ID);
        userService.isUserAdmin(userId);

        return new ResponseEntity<>(productService.addProduct(dto), HttpStatus.OK);
    }

    @GetMapping("/product/search/{name}")
    public ResponseEntity<ProductDTO> searchProductByName(@PathVariable String name, HttpServletRequest request) {
        checkIfUserIsLogged(request);

        return new ResponseEntity<>(productService.searchWithName(name), HttpStatus.OK);
    }

    @PostMapping("/products/{pid}/discount/{did}")
    public ResponseEntity<ProductDTO> addDiscountToProduct(@PathVariable int pid, @PathVariable int did, HttpServletRequest request) {
        checkIfUserIsLogged(request);
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute(USER_ID);
        userService.isUserAdmin(userId);

        return new ResponseEntity<>(productService.addDiscountToProduct(pid,did), HttpStatus.OK);
    }

    @PutMapping("/products/{pid}")
    public ResponseEntity<ProductDTO> editProduct(@Valid @RequestBody ProductEditDTO dto, @PathVariable int pid, HttpServletRequest request){
        checkIfUserIsLogged(request);
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute(USER_ID);
        userService.isUserAdmin(userId);

        return new ResponseEntity<>(productService.editProduct(dto,pid), HttpStatus.OK);
    }
}