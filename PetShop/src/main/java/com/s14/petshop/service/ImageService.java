package com.s14.petshop.service;

import com.s14.petshop.model.beans.Image;
import com.s14.petshop.model.beans.Product;
import com.s14.petshop.model.exceptions.BadRequestException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


@Service
public class ImageService extends AbstractService {


    public Image addImage(MultipartFile file, Product owner) {
        try {
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());

            if (!checkImageExtension(extension)) {
                throw new BadRequestException("Insert an image");
            }

            String name = "product" + File.separator + System.nanoTime() + "-" + owner.getId() + "." + extension;
            File file2 = new File(name);
            if (!file2.exists()) {
                Files.copy(file.getInputStream(), file2.toPath());
            } else {
                throw new BadRequestException("The file already exists.");
            }

            Image image = new Image();
            image.setImageUrl(name);
            image.setOwner(owner);
            imageRepository.save(image);

            return image;

        }catch (IOException e) {
            throw new BadRequestException(e.getMessage(), e);
        }
    }
}
