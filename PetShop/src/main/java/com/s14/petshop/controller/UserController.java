package com.s14.petshop.controller;

import com.s14.petshop.model.dtos.user.*;
import com.s14.petshop.model.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
public class UserController extends AbstractController {
    @PostMapping("/user/auth")
    public ResponseEntity<UserWithoutPassAndIsAdminDTO> login(@Valid @RequestBody LoginDTO loginDTO, HttpServletRequest req) {
        loginDTO.setEmail(loginDTO.getEmail().trim());
        loginDTO.setPassword(loginDTO.getPassword().trim());

        UserWithoutPassAndIsAdminDTO resultUser = userService.login(loginDTO);
        loginUser(req, resultUser.getId());
        return new ResponseEntity<>(resultUser, HttpStatus.OK);
    }

    @GetMapping("/users/{uid}")
    public UserWithoutPassAndIsAdminDTO getUserById(@PathVariable int uid) {
        return userService.getById(uid);
    }

    @PostMapping("/users")
    public ResponseEntity<UserWithoutPassAndIsAdminDTO> registerUser(@Valid @RequestBody RegisterDTO userForRegistration) {
        userForRegistration.setEmail(userForRegistration.getEmail().trim());
        userForRegistration.setPassword(userForRegistration.getPassword().trim());

        userForRegistration.setConfirmPassword(userForRegistration.getConfirmPassword().trim());
        return new ResponseEntity<>(userService.registerUser(userForRegistration), HttpStatus.CREATED);
    }

    @GetMapping("/user/profile")
    public UserWithoutPassAndIsAdminDTO showUserProfile(HttpServletRequest request) {
        return getUserById(getLoggedUserId(request));
    }

    @PostMapping("/user/logout")
    public void logout(HttpSession session) {
        session.invalidate();
    }

    @PutMapping("/user/profile")
    public void editProfile(@Valid @RequestBody EditProfileUserDTO user, HttpServletRequest request) {
        System.out.println(user.toString());
        UserWithoutPassAndIsAdminDTO u = getUserById(getLoggedUserId(request));
        userService.editProfile(user, u);
    }

    @PutMapping("/user/profile/changePassword")
    public void changePassword(@Valid @RequestBody ChangePasswordDTO user, HttpServletRequest request) {
        UserWithoutPassAndIsAdminDTO currentUser = getUserById(getLoggedUserId(request));
        System.out.println(user.toString());
        System.out.println(currentUser.toString());
        userService.changePassword(user, currentUser);
    }

    @PutMapping("/user/profile/newsletter")
    public void subscribe(@RequestParam(name = "is_subscribed") boolean subscribe, HttpServletRequest request) {
        UserWithoutPassAndIsAdminDTO user = getUserById(getLoggedUserId(request));
        userService.subscribe(subscribe, user);
    }

    @DeleteMapping("/user/profile")
    public void deleteUser(@Valid @RequestBody DeleteUserDTO userForDeleting, HttpServletRequest request) {
        UserWithoutPassAndIsAdminDTO currentUser = getUserById(getLoggedUserId(request));
        userService.deleteUser(userForDeleting, currentUser);
        logout(request.getSession());
    }

    @GetMapping("/products/{pid}/fav")
    public void addProductToFavorites(@PathVariable int pid, HttpServletRequest request) {
        if (pid < 1) {
            throw new NotFoundException("Product not found");
        }
        UserWithoutPassAndIsAdminDTO currentUser = getUserById(getLoggedUserId(request));
        userService.addProductToFavorites(pid, currentUser);
    }

    @PostMapping("/user/profile/upload-picture")
    public String uploadProfileImage(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request){
        UserWithoutPassAndIsAdminDTO currentUser = getUserById(getLoggedUserId(request));
        return userService.uploadProfileImage(file, currentUser);
    }
}
