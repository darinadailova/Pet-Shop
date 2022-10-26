package com.s14.petshop.controller;

import com.s14.petshop.model.dtos.ErrorDTO;
import com.s14.petshop.model.dtos.product.ProductForAddingInCartDTO;
import com.s14.petshop.model.exceptions.BadRequestException;
import com.s14.petshop.model.exceptions.NotFoundException;
import com.s14.petshop.model.exceptions.UnauthorizedException;
import com.s14.petshop.service.AddressService;
import com.s14.petshop.service.CartService;
import com.s14.petshop.service.ReviewService;
import com.s14.petshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractController {

    public static final String LOGGED = "LOGGED";
    public static final String USER_ID = "USER_ID";
    public static final String REMOTE_IP = "REMOTE_IP";

    public List<ProductForAddingInCartDTO> cart;

    @Autowired
    public UserService userService;
    @Autowired
    public AddressService addressService;

    @Autowired
    public ReviewService reviewService;

    @Autowired
    public CartService cartService;

    @ExceptionHandler(value = BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDTO handleBadRequest(Exception e) {
        return createError(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorDTO handleNotFound(Exception e) {
        return createError(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ErrorDTO handleUnauthorized(Exception e) {
        return createError(e, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO handleOtherExceptions(Exception e) {
        return createError(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ErrorDTO createError(Exception e, HttpStatus status) {
        e.printStackTrace();
        ErrorDTO error = new ErrorDTO();
        error.setMessage(e.getMessage());
        error.setDataAndTime(LocalDateTime.now());
        error.setStatus(status.value());
        return error;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
    public void loginUser(HttpServletRequest request, int id) {
        HttpSession session = request.getSession();
        session.setAttribute(LOGGED, true);
        session.setAttribute(USER_ID, id);
        session.setAttribute(REMOTE_IP, request.getRemoteAddr());
    }

    public int getLoggedUserId(HttpServletRequest request) {
        HttpSession session = request.getSession();
        checkIfUserIsLogged(request);
        return (int) session.getAttribute(USER_ID);
    }

    public void checkIfUserIsLogged(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String IP = request.getRemoteAddr();

        if(session.isNew() || session.getAttribute(LOGGED) == null
                || !(boolean) session.getAttribute(LOGGED) || !session.getAttribute(REMOTE_IP).equals(IP)){
            throw new UnauthorizedException("You have to login!");
        }
    }
}
