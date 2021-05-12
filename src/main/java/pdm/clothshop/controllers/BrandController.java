package pdm.clothshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import pdm.clothshop.exception.ExceptionResponse;
import pdm.clothshop.exception.clothShopException;
import pdm.clothshop.models.Brand;

import pdm.clothshop.services.BrandService;

import java.util.List;

@RestController
@CrossOrigin
public class BrandController {

    @Autowired
    private BrandService brandService;
    @GetMapping("/brands")
    public List<Brand> listBrand(){
        return brandService.listBrand();
    }

    @GetMapping("/brands/{id}")
    public Brand getBrand(@PathVariable int id) {
        return brandService.findById(id);
    }
}
