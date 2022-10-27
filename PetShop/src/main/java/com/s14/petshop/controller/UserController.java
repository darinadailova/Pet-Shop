package com.s14.petshop.controller;

import com.s14.petshop.model.dtos.orders.OrderResponseDTO;
import com.s14.petshop.model.dtos.product.ProductResponseDTO;
import com.s14.petshop.model.dtos.user.*;
import com.s14.petshop.model.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController extends AbstractController {
    @PostMapping("/user/auth")
    public ResponseEntity<UserWithoutPasswordDTO> login(@Valid @RequestBody LoginDTO loginDTO, HttpServletRequest req) {
        loginDTO.setEmail(loginDTO.getEmail().trim());
        loginDTO.setPassword(loginDTO.getPassword().trim());

        UserWithoutPasswordDTO resultUser = userService.login(loginDTO);
        loginUser(req, resultUser.getId());
        return new ResponseEntity<>(resultUser, HttpStatus.OK);
    }

    @GetMapping("/users/{uid}")
    public UserWithoutPasswordDTO getUserById(@PathVariable int uid) {
        return userService.getById(uid);
    }

    @PostMapping("/users")
    public ResponseEntity<UserWithoutPasswordDTO> registerUser(@Valid @RequestBody RegisterDTO userForRegistration) {
        userForRegistration.setEmail(userForRegistration.getEmail().trim());
        userForRegistration.setPassword(userForRegistration.getPassword().trim());

        userForRegistration.setConfirmPassword(userForRegistration.getConfirmPassword().trim());
        return new ResponseEntity<>(userService.registerUser(userForRegistration), HttpStatus.CREATED);
    }

    @GetMapping("/user/profile")
    public UserWithoutPasswordDTO showUserProfile(HttpServletRequest request) {
        return getUserById(getLoggedUserId(request));
    }

    @PostMapping("/user/logout")
    public void logout(HttpSession session) {
        session.invalidate();
    }

    @PutMapping("/user/profile")
    public ResponseEntity<UserWithoutPasswordDTO> editProfile(@Valid @RequestBody EditProfileUserDTO user, HttpServletRequest request) {
        UserWithoutPasswordDTO u = getUserById(getLoggedUserId(request));
        return new ResponseEntity<>(userService.editProfile(user, u), HttpStatus.OK);
    }

    @PutMapping("/user/profile/changePassword")
    public ResponseEntity<UserWithoutPasswordDTO> changePassword(@Valid @RequestBody ChangePasswordDTO user, HttpServletRequest request) {
        UserWithoutPasswordDTO currentUser = getUserById(getLoggedUserId(request));
        return new ResponseEntity<>(userService.changePassword(user, currentUser), HttpStatus.OK);
    }

    @PutMapping("/user/profile/newsletter")
    public ResponseEntity<UserWithoutPasswordDTO> subscribe(@RequestParam(name = "is_subscribed") boolean subscribe, HttpServletRequest request) {
        UserWithoutPasswordDTO user = getUserById(getLoggedUserId(request));
        return new ResponseEntity<>(userService.subscribe(subscribe, user), HttpStatus.OK);
    }

    @DeleteMapping("/user/profile")
    public ResponseEntity<UserWithoutPasswordDTO> deleteUser(@Valid @RequestBody DeleteUserDTO userForDeleting, HttpServletRequest request) {
        UserWithoutPasswordDTO currentUser = getUserById(getLoggedUserId(request));
        UserWithoutPasswordDTO result = userService.deleteUser(userForDeleting, currentUser);
        logout(request.getSession());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/products/{pid}/fav")
    public ResponseEntity<UserWithoutPasswordDTO> addProductToFavorites(@PathVariable int pid, HttpServletRequest request) {
        if (pid < 1) {
            throw new NotFoundException("Product not found");
        }
        UserWithoutPasswordDTO currentUser = getUserById(getLoggedUserId(request));
        return new ResponseEntity<>(userService.addProductToFavorites(pid, currentUser), HttpStatus.OK);
    }

    @PostMapping("/user/profile/upload-picture")
    public String uploadProfileImage(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request){
        UserWithoutPasswordDTO currentUser = getUserById(getLoggedUserId(request));
        return userService.uploadProfileImage(file, currentUser);
    }

    @GetMapping("/user/favorites")
    public List<ProductResponseDTO> getFavProducts(HttpServletRequest request) {
        int uid = getLoggedUserId(request);
        return userService.getFavProducts(uid);
    }

    @GetMapping("/user/orders")
    public List<OrderResponseDTO> getOrders(HttpServletRequest request) {
        int uid = getLoggedUserId(request);
        return userService.getOrders(uid);
    }
}
