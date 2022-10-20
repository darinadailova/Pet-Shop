package com.s14.petshop.service;

import com.s14.petshop.model.beans.User;
import com.s14.petshop.model.dtos.AddressWithoutOwnerDTO;
import com.s14.petshop.model.dtos.user.LoginDTO;
import com.s14.petshop.model.dtos.user.RegisterDTO;
import com.s14.petshop.model.dtos.user.UserWithoutPassAndIsAdminDTO;
import com.s14.petshop.model.exceptions.BadRequestException;
import com.s14.petshop.model.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService extends AbstractService {
    public UserWithoutPassAndIsAdminDTO login(LoginDTO loginDTO) {
        if (!emailExistInDB(loginDTO.getEmail()) || !isCorrectPassword(loginDTO)) {
            throw new BadRequestException("Wrong credentials!");
        }
        Optional<User> user = userRepository.findByEmail(loginDTO.getEmail());
        if (user.isPresent()) {
            UserWithoutPassAndIsAdminDTO u = modelMapper.map(user, UserWithoutPassAndIsAdminDTO.class);
            u.setAddresses(u.getAddresses().stream().
                    map(a -> modelMapper.map(a, AddressWithoutOwnerDTO.class)).collect(Collectors.toList()));
            // todo get reviews
            return u;
        } else {
            throw new BadRequestException("Wrong credentials!");
        }
    }

    private boolean isCorrectPassword(LoginDTO loginDTO) {
        // todo method that checks for upper-case letter, special symbol...
        Optional<User> user = userRepository.findByEmail(loginDTO.getEmail());
        if (loginDTO.getPassword().equals(user.get().getPassword())) {
            return true;
        }
        return false;
    }

    private boolean emailExistInDB(String email) {
        // todo method for validating email with regex
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return true;
        }
        return false;
    }

    public UserWithoutPassAndIsAdminDTO getById(int uid) {
        Optional<User> result = userRepository.getById(uid);
        if (result.isPresent()) {
            return modelMapper.map(result, UserWithoutPassAndIsAdminDTO.class);
        } else {
            throw new NotFoundException("User not found!");
        }
    }

    public UserWithoutPassAndIsAdminDTO registerUser(RegisterDTO userForRegistration) {
        if (!userForRegistration.getPassword().equals(userForRegistration.getConfirmPassword())) {
            throw new BadRequestException("The passwords don't match!");
        }
        if (emailExistInDB(userForRegistration.getEmail())) {
            throw new BadRequestException("Bad request");
        }
        // todo validate if the password has all symbols needed for example has a upper later and special symbol...
        // todo hash password before saving the user in DB
        userRepository.save(modelMapper.map(userForRegistration, User.class));
        return modelMapper.map(userForRegistration, UserWithoutPassAndIsAdminDTO.class);
    }
}
