package pdm.clothshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdm.clothshop.repositories.ProductRepository;
import pdm.clothshop.services.FileStorageService;

@RestController
@CrossOrigin
public class ImageController {
    @Autowired
    public FileStorageService fileStorageService;

    @Autowired
    public ProductRepository productRepository;

    @GetMapping("/image/{filename}")
    public ResponseEntity<byte[]> getImage(@PathVariable String filename){
//        String filename = productRepository.findById(id).get().getImage();
    return fileStorageService.readImage(filename);
    }
}
