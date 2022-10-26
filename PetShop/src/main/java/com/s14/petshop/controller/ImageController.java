package com.s14.petshop.controller;

import com.s14.petshop.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageController extends AbstractController {

    @Autowired
    private ImageService imageService;



}
