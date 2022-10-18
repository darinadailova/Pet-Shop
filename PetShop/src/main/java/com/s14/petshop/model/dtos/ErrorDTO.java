package com.s14.petshop.model.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorDTO {

    private String message;
    private int status;
    private LocalDateTime dataAndTime;
}
