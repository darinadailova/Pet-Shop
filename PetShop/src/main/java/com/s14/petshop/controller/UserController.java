package com.s14.petshop.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.s14.petshop.model.dtos.user.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class UserController extends AbstractController {
    @PostMapping("/user/auth")
    public UserWithoutPassAndIsAdminDTO login(@RequestBody LoginDTO loginDTO, HttpServletRequest req) {
        loginDTO.setEmail(loginDTO.getEmail().trim());
        loginDTO.setPassword(loginDTO.getPassword().trim());

        UserWithoutPassAndIsAdminDTO resultUser = userService.login(loginDTO);
        loginUser(req, resultUser.getId());
        return resultUser;
    }

    @GetMapping("/users/{uid}")
    public UserWithoutPassAndIsAdminDTO getUserById(@PathVariable int uid) {
        return userService.getById(uid);
    }

    @PostMapping("/users")
    public UserWithoutPassAndIsAdminDTO registerUser(@RequestBody RegisterDTO userForRegistration) {
        userForRegistration.setEmail(userForRegistration.getEmail().trim());
        userForRegistration.setPassword(userForRegistration.getPassword().trim());

        userForRegistration.setConfirmPassword(userForRegistration.getConfirmPassword().trim());
        return userService.registerUser(userForRegistration);
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
    public void editProfile(@RequestBody EditProfileUserDTO user, HttpServletRequest request) {
        System.out.println(user.toString());
        UserWithoutPassAndIsAdminDTO u = getUserById(getLoggedUserId(request));
        userService.editProfile(user, u);
    }

    @PutMapping("/user/profile/changePassword")
    public void changePassword(@RequestBody ChangePasswordDTO user, HttpServletRequest request) {
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
    public void deleteUser(@RequestBody DeleteUserDTO userForDeleting, HttpServletRequest request) {
        UserWithoutPassAndIsAdminDTO currentUser = getUserById(getLoggedUserId(request));
        userService.deleteUser(userForDeleting, currentUser);
        logout(request.getSession());
    }


}
