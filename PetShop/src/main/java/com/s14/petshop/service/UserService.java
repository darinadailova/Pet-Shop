package com.s14.petshop.service;

import com.s14.petshop.model.beans.User;
import com.s14.petshop.model.dtos.user.LoginDTO;
import com.s14.petshop.model.dtos.user.RegisterDTO;
import com.s14.petshop.model.dtos.user.UserWithoutPassAndIsAdminDTO;
import com.s14.petshop.model.exceptions.BadRequestException;
import com.s14.petshop.model.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends AbstractService {
    public UserWithoutPassAndIsAdminDTO login(LoginDTO loginDTO) {
        if (!isValidEmail(loginDTO.getEmail()) || !isValidPassword(loginDTO)) {
            throw new BadRequestException("Wrong credentials!");
        }
        Optional<User> user = userRepository.findByEmail(loginDTO.getEmail());
        if (user.isPresent()) {
            return modelMapper.map(user, UserWithoutPassAndIsAdminDTO.class);
        }
        else {
            throw new BadRequestException("Wrong credentials!");
        }
    }

    private boolean isValidPassword(LoginDTO loginDTO) {
        Optional<User> user = userRepository.findByEmail(loginDTO.getEmail());
        if (loginDTO.getPassword().equals(user.get().getPassword())) {
            return true;
        }
        return false;
    }

    private boolean isValidEmail(String email) {
        // todo validate email with regex
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return true;
        }
        return false;
    }

    public UserWithoutPassAndIsAdminDTO getById(int uid) {
        Optional<User> result = userRepository.getById(uid);
        if (result != null) {
            return modelMapper.map(result, UserWithoutPassAndIsAdminDTO.class);
        }
        else {
            throw new NotFoundException("User not found!");
        }
    }

    public UserWithoutPassAndIsAdminDTO registerUser(RegisterDTO userForRegistration) {
        if (!userForRegistration.getPassword().equals(userForRegistration.getConfirmPassword())) {
            System.out.println(userForRegistration.getPassword() + " " + userForRegistration.getConfirmPassword());
            throw new BadRequestException("The passwords don't match!");
        }
        // todo validate if the password has all symbols needed for example has a upper later and special symbol...
        // todo hash password before saving the user in DB
        userRepository.save(modelMapper.map(userForRegistration, User.class));
        return modelMapper.map(userForRegistration, UserWithoutPassAndIsAdminDTO.class);
    }
}
