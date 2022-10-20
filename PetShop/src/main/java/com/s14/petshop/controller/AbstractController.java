package com.s14.petshop.controller;

import com.s14.petshop.model.dtos.ErrorDTO;
import com.s14.petshop.model.exceptions.BadRequestException;
import com.s14.petshop.model.exceptions.NotFoundException;
import com.s14.petshop.model.exceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

public abstract class AbstractController {

    public static final String LOGGED = "LOGGED";
    public static final String USER_ID = "USER_ID";
    public static final String REMOTE_IP = "REMOTE_IP";

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

    public void loginUser(HttpServletRequest request, int id) {
        HttpSession session = request.getSession();
        session.setAttribute(LOGGED, true);
        session.setAttribute(USER_ID, id);
        session.setAttribute(REMOTE_IP, request.getRemoteAddr());
    }

    public int getUserId(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String IP = request.getRemoteAddr();

        boolean isLoggedOut = session.getAttribute(LOGGED) == null;
        boolean isLoggedFromTheSameIP = false;
        if (session.getAttribute(REMOTE_IP) != null) {
            isLoggedFromTheSameIP = session.getAttribute(REMOTE_IP).equals(IP);
        }
        if(session.isNew() || isLoggedOut || !isLoggedFromTheSameIP){
            throw new UnauthorizedException("You have to login!");
        }
        return (int) session.getAttribute(USER_ID);
    }
}
