package pdm.clothshop.controllers;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pdm.clothshop.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import pdm.clothshop.repositories.ProductRepository;
import pdm.clothshop.services.ProductService;

import java.util.List;

@CrossOrigin
@RestController
public class ProductController {
    @Autowired
    private ProductRepository productRepository;


    @Autowired
    ProductService productService;

    @GetMapping("/clothes")
    public List<Product> catalog() {
        return productService.listCatalog();
    }


    @GetMapping("/clothes/{id}")
    public Product findById(@PathVariable int id) {

        return productService.findById(id);
    }

    @DeleteMapping("/clothes/{id}")
    public void removeProduct(@PathVariable int id) {
        productService.removeProduct(id);
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product newProduct){
        newProduct.setImage("test");
        productRepository.save(newProduct);
        return newProduct;
    }

    @PutMapping("/clothes/{id}")
    public void  editProduct(@PathVariable int id, @RequestPart(value = "imageFile",required = false)MultipartFile file, @RequestPart Product editedProduct){
        productService.editProduct(id,file,editedProduct);
    }

    @PostMapping("/clothes")
    public void addProductWithImage(@RequestParam("imageFile")MultipartFile file, @RequestPart Product newProduct){
        productService.addProductToCatalog(file,newProduct);
    }
}
