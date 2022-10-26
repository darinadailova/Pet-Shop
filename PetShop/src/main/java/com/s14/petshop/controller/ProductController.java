package com.s14.petshop.controller;

import com.s14.petshop.model.dtos.product.ProductAddDTO;
import com.s14.petshop.model.dtos.product.ProductFilterDTO;
import com.s14.petshop.model.dtos.product.ProductResponseDTO;
import com.s14.petshop.model.dtos.product.ProductEditDTO;
import com.s14.petshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
public class ProductController extends AbstractController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products/{pid}")
    public ResponseEntity<ProductResponseDTO> getById(@PathVariable int pid, HttpServletRequest request) {
        checkIfUserIsLogged(request);

        return new ResponseEntity<>(productService.getById(pid), HttpStatus.OK);
    }

    @DeleteMapping("/products/{pid}")
    public ResponseEntity<ProductResponseDTO> deleteProduct(@PathVariable int pid, HttpServletRequest request) {
        checkIfUserIsLogged(request);
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute(USER_ID);
        userService.isUserAdmin(userId);

        return new ResponseEntity<>(productService.deleteProduct(pid), HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<ProductResponseDTO> addProduct(@Valid @RequestBody ProductAddDTO dto, HttpServletRequest request) {
        checkIfUserIsLogged(request);
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute(USER_ID);
        userService.isUserAdmin(userId);

        return new ResponseEntity<>(productService.addProduct(dto), HttpStatus.OK);
    }

    @GetMapping("/product/search/{name}")
    public ResponseEntity<ProductResponseDTO> searchProductByName(@PathVariable String name, HttpServletRequest request) {
        checkIfUserIsLogged(request);

        return new ResponseEntity<>(productService.searchWithName(name), HttpStatus.OK);
    }

    @PostMapping("/products/{pid}/discount/{did}")
    public ResponseEntity<ProductResponseDTO> addDiscountToProduct(@PathVariable int pid, @PathVariable int did, HttpServletRequest request) {
        checkIfUserIsLogged(request);
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute(USER_ID);
        userService.isUserAdmin(userId);

        return new ResponseEntity<>(productService.addDiscountToProduct(pid,did), HttpStatus.OK);
    }

    @PutMapping("/products/{pid}")
    public ResponseEntity<ProductResponseDTO> editProduct(@Valid @RequestBody ProductEditDTO dto, @PathVariable int pid, HttpServletRequest request){
        checkIfUserIsLogged(request);
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute(USER_ID);
        userService.isUserAdmin(userId);

        return new ResponseEntity<>(productService.editProduct(dto,pid), HttpStatus.OK);
    }

    @GetMapping("/products/filter")
    public ResponseEntity<List<ProductResponseDTO>> filterProducts(@RequestBody ProductFilterDTO dto, HttpServletRequest request){
        checkIfUserIsLogged(request);

        return new ResponseEntity<>(productService.filterProducts(dto), HttpStatus.OK);
    }
}