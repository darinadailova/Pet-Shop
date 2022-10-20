package com.s14.petshop.controller;

import com.s14.petshop.model.dtos.user.LoginDTO;
import com.s14.petshop.model.dtos.user.RegisterDTO;
import com.s14.petshop.model.dtos.user.UserWithoutPassAndIsAdminDTO;
import com.s14.petshop.model.exceptions.BadRequestException;
import com.s14.petshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController extends AbstractController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/auth")
    public UserWithoutPassAndIsAdminDTO login(@RequestBody LoginDTO loginDTO, HttpServletRequest req) {
        UserWithoutPassAndIsAdminDTO resultUser = userService.login(loginDTO);
        System.out.println("here");
        if(resultUser != null){
            loginUser(req, resultUser.getId());
            return resultUser;
        }
        else{
            throw new BadRequestException("Wrong Credentials");
        }
    }

    @GetMapping("/users/{uid}")
    public UserWithoutPassAndIsAdminDTO getUserById(@PathVariable int uid) {
        return userService.getById(uid);
    }

    @PostMapping("/users")
    public UserWithoutPassAndIsAdminDTO registerUser(@RequestBody RegisterDTO userForRegistration) {
        System.out.println("object" + userForRegistration.toString());
        return userService.registerUser(userForRegistration);
    }
}
