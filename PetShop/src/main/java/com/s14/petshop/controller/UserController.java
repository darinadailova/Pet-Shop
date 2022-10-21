package com.s14.petshop.controller;

import com.s14.petshop.model.dtos.user.EditProfileUserDTO;
import com.s14.petshop.model.dtos.user.LoginDTO;
import com.s14.petshop.model.dtos.user.RegisterDTO;
import com.s14.petshop.model.dtos.user.UserWithoutPassAndIsAdminDTO;
import com.s14.petshop.model.exceptions.UnauthorizedException;
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
    public void logout(HttpSession session, HttpServletRequest request) {
        session.invalidate();
    }

    @PutMapping("/user/profile")
    public void editProfile(EditProfileUserDTO user, HttpServletRequest request) {

    }
}
