package com.s14.petshop.service;

import com.s14.petshop.controller.AbstractController;
import com.s14.petshop.model.beans.Product;
import com.s14.petshop.model.beans.User;
import com.s14.petshop.model.dtos.address.AddressWithoutOwnerDTO;
import com.s14.petshop.model.dtos.ReviewWithoutOwnerDTO;
import com.s14.petshop.model.dtos.user.*;
import com.s14.petshop.model.exceptions.BadRequestException;
import com.s14.petshop.model.exceptions.NotFoundException;
import com.s14.petshop.model.exceptions.UnauthorizedException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UserService extends AbstractService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserWithoutPasswordDTO login(LoginDTO loginDTO) {
        if (!isValidPassword(loginDTO.getPassword())) {
            throw new BadRequestException("Wrong credentials!");
        }
        User user = userRepository.findByEmail(loginDTO.getEmail()).
                orElseThrow(() -> new BadRequestException("Wrong credentials"));

        if (!bCryptPasswordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new BadRequestException("Wrong credentials!");
        }

        UserWithoutPasswordDTO u = modelMapper.map(user, UserWithoutPasswordDTO.class);
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

    public UserWithoutPasswordDTO getById(int uid) {
        User result = userRepository.getById(uid).orElseThrow(() -> new NotFoundException("User not found"));
        return modelMapper.map(result, UserWithoutPasswordDTO.class);
    }

    public UserWithoutPasswordDTO registerUser(RegisterDTO userForRegistration) {
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

        if (isUserAdmin(userForSavingInDb.getEmail())) {
            userForSavingInDb.setAdmin(true);
        }
        userRepository.save(userForSavingInDb);
        return modelMapper.map(userForRegistration, UserWithoutPasswordDTO.class);
    }

    private boolean isUserAdmin(String email) {
        Pattern pattern = Pattern.compile("[a-z,A-Z,0-9]+@petshop.bg$");
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

    public UserWithoutPasswordDTO subscribe(boolean subscribe, UserWithoutPasswordDTO user) {
        User u = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new NotFoundException("user not found"));
        u.setSubscribed(subscribe);
        userRepository.save(u);
        return modelMapper.map(u, UserWithoutPasswordDTO.class);
    }

    public UserWithoutPasswordDTO editProfile(EditProfileUserDTO user, UserWithoutPasswordDTO u) {
        User user2 = userRepository.findByEmail(u.getEmail())
                .orElseThrow(() -> new NotFoundException("User not found"));
        if (!user.getRepeatPassword().equals(user.getPassword())) {
            throw new BadRequestException("Passwords don't match!");
        }
        if (!user.getPassword().equals(user2.getPassword())) {
            throw new BadRequestException("Wrong password");
        }
        modelMapper.map(user, user2);
        userRepository.save(user2);
        return modelMapper.map(user2, UserWithoutPasswordDTO.class);
    }

    public UserWithoutPasswordDTO changePassword(ChangePasswordDTO user, UserWithoutPasswordDTO currentUser) {
        User u = userRepository.findByEmail(currentUser.getEmail())
                .orElseThrow(() -> new NotFoundException("User not found"));
        if (!bCryptPasswordEncoder.matches(user.getPassword(), u.getPassword())) {
            throw new BadRequestException("Bad request");
        }
        if (!user.getNewPassword().equals(user.getRepeatNewPassword())) {
            throw  new BadRequestException("Passwords don't match");
        }
        u.setPassword(bCryptPasswordEncoder.encode(user.getNewPassword()));
        userRepository.save(u);
        return modelMapper.map(u, UserWithoutPasswordDTO.class);
    }

    public UserWithoutPasswordDTO deleteUser(DeleteUserDTO userForDeleting, UserWithoutPasswordDTO currentUser) {
        if (!userForDeleting.getPassword().equals(userForDeleting.getConfirm_password())) {
            throw new UnauthorizedException("Passwords don't match");
        }
        User user = userRepository.findByEmail(currentUser.getEmail())
                .orElseThrow(() -> new NotFoundException("User not found"));
        user.setFirstName("deleted");
        user.setLastName("deleted");
        user.setPassword("deleted");
        user.setEmail("deleted" + user.getId());
        user.setPhoneNumber("deleted" + user.getId());
        userRepository.save(user);
        return modelMapper.map(user, UserWithoutPasswordDTO.class);
    }

    public UserWithoutPasswordDTO addProductToFavorites(int pid, UserWithoutPasswordDTO currentUser) {
        Product product = productRepository.findProductById(pid)
                .orElseThrow(() -> new NotFoundException("Product wasn't found"));
        User user = modelMapper.map(currentUser, User.class);
        user.getLikedProducts().add(product);
        userRepository.save(user);
        return modelMapper.map(user, UserWithoutPasswordDTO.class);
    }

    public String uploadProfileImage(MultipartFile file, UserWithoutPasswordDTO currentUser) {
        try {
            User user = userRepository.getById(currentUser.getId())
                    .orElseThrow(() -> new NotFoundException("User not found"));

            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            if (!checkImageExtension(extension)) {
                throw new BadRequestException("Insert a picture");
            }
            String name = "uploads" + File.separator + System.nanoTime() + "-" + user.getId() + "." + extension;
            File file2 = new File(name);
            if(!file2.exists()) {
                Files.copy(file.getInputStream(), file2.toPath());
            }
            else{
                throw new BadRequestException("The file already exists.");
            }
            if(user.getProfilePictureUrl() != null){
                File old = new File(user.getProfilePictureUrl());
                old.delete();
            }
            user.setProfilePictureUrl(name);
            userRepository.save(user);
            return name;
        } catch (IOException e) {
            throw new BadRequestException(e.getMessage(), e);
        }
    }

    private boolean checkImageExtension(String extension) {
        extension = extension.toLowerCase();
        return (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png")
                || extension.equals("gif") || extension.equals("raw") || extension.equals("svg") ||
                extension.equals("heic"));

    }
}
