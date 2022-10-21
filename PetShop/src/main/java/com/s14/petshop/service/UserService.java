package com.s14.petshop.service;

import com.s14.petshop.model.beans.User;
import com.s14.petshop.model.dtos.AddressWithoutOwnerDTO;
import com.s14.petshop.model.dtos.ReviewWithoutOwnerDTO;
import com.s14.petshop.model.dtos.user.EditProfileUserDTO;
import com.s14.petshop.model.dtos.user.LoginDTO;
import com.s14.petshop.model.dtos.user.RegisterDTO;
import com.s14.petshop.model.dtos.user.UserWithoutPassAndIsAdminDTO;
import com.s14.petshop.model.exceptions.BadRequestException;
import com.s14.petshop.model.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService extends AbstractService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserWithoutPassAndIsAdminDTO login(LoginDTO loginDTO) {
        if (!isValidPassword(loginDTO.getPassword())) {
            throw new BadRequestException("Wrong credentials!");
        }
        User user = userRepository.findByEmail(loginDTO.getEmail()).
                orElseThrow(() -> new BadRequestException("Wrong credentials"));

        if (!bCryptPasswordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new BadRequestException("Wrong credentials!");
        }

        UserWithoutPassAndIsAdminDTO u = modelMapper.map(user, UserWithoutPassAndIsAdminDTO.class);
        u.setAddresses(u.getAddresses().stream()
                .map(a -> modelMapper.map(a, AddressWithoutOwnerDTO.class)).collect(Collectors.toList()));
        u.setReviews(u.getReviews().stream()
                .map(r -> modelMapper.map(r, ReviewWithoutOwnerDTO.class)).collect(Collectors.toList()));
        return u;
    }

    // checks if it is at least 8 characters, has at least
    // one uppercase letter, one lowercase letter and a special symbol
    private boolean isValidPassword(String password) {
        if (password.length() < 8) {
            return false;
        }
        boolean hasUpperCaseLetter = false;
        boolean hasLowercaseLetter = false;
        String specialSymbols = "~`!@#$%^&*()-_+={}[]|/\\:;\"'><,.?";
        boolean hasSpecialSymbol = false;
        for (int i = 0; i < password.length(); i++) {
            char currentChar = password.charAt(i);
            if (!hasUpperCaseLetter) {
                if (currentChar >= 'A' && currentChar <= 'Z') {
                    hasUpperCaseLetter = true;
                }
            }
            if (!hasLowercaseLetter) {
                if (currentChar >= 'a' && currentChar <= 'z') {
                    hasLowercaseLetter = true;
                }
            }
            if (!hasSpecialSymbol) {
                for (int j = 0; j < specialSymbols.length(); j++) {
                    if (currentChar == specialSymbols.charAt(j)) {
                        hasSpecialSymbol = true;
                    }
                }
            }
        }
        if (hasLowercaseLetter && hasUpperCaseLetter && hasSpecialSymbol) {
            return true;
        }
        return false;
    }

    public UserWithoutPassAndIsAdminDTO getById(int uid) {
        User result = userRepository.getById(uid).orElseThrow(() -> new NotFoundException("User not found"));
        return modelMapper.map(result, UserWithoutPassAndIsAdminDTO.class);
    }

    public UserWithoutPassAndIsAdminDTO registerUser(RegisterDTO userForRegistration) {
        if (!isValidPassword(userForRegistration.getPassword())) {
            throw new BadRequestException("The password should be at least 8 characters and have one " +
                    "uppercase letter, one lowercase letter and special symbol");
        }
        if (!userForRegistration.getPassword().equals(userForRegistration.getConfirmPassword())) {
            throw new BadRequestException("The passwords don't match!");
        }
        Optional<User> user = userRepository.findByEmail(userForRegistration.getEmail());
        if (user.isPresent()) {
            throw new BadRequestException("Bad request");
        }

        User userForSavingInDb = modelMapper.map(userForRegistration, User.class);
        userForSavingInDb.setPassword(bCryptPasswordEncoder.encode(userForSavingInDb.getPassword()));
        userRepository.save(userForSavingInDb);
        return modelMapper.map(userForRegistration, UserWithoutPassAndIsAdminDTO.class);
    }
}
