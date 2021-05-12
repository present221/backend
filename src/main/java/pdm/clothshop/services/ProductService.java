package pdm.clothshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pdm.clothshop.exception.ExceptionResponse;
import pdm.clothshop.exception.clothShopException;
import pdm.clothshop.models.Product;
import pdm.clothshop.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FileStorageService fileStorageService;


    public List<Product> listCatalog(){
        if (productRepository.findAll().isEmpty()){
            throw new clothShopException(ExceptionResponse.ERROR_CODE.CATALOG_IS_EMPTY,"The catalog is empty");
        }
        return productRepository.findAll();
    }

    public Product findById(int id){
        System.out.println(!productRepository.existsById(id));
        if(!productRepository.existsById(id)){
            throw new clothShopException(ExceptionResponse.ERROR_CODE.PRODUCT_NOT_EXISTS,"product id: " + id + " does not exist.");
        }
        return productRepository.findById(id).get();
    }

    public void removeProduct(int id){
        if(!productRepository.existsById(id)){
            throw new clothShopException(ExceptionResponse.ERROR_CODE.PRODUCT_NOT_EXISTS,"CANNOT REMOVE! Product id: " + id + " does not exist.");
        }

        fileStorageService.delete(productRepository.findById(id).get().getImage());
        productRepository.deleteById(id);
    }

    public void editProduct(int id,MultipartFile file,Product editedProduct){
        if(!productRepository.existsById(id)){
            throw new clothShopException(ExceptionResponse.ERROR_CODE.PRODUCT_NOT_EXISTS,"CANNOT EDIT! Product id: " + id + " does not exist.");
        }

        productRepository.findById(id).map(product->{
            if(editedProduct.equals(null)){
                throw new clothShopException(ExceptionResponse.ERROR_CODE.FILE_EMPTY,"Product cannot be EMPTY");
            }

            if(checkDuplicateProductName(editedProduct.getProductName(),id)){
                throw new clothShopException(ExceptionResponse.ERROR_CODE.PRODUCT_NAME_CONFLICT,"CANNOT EDIT! your new product name: "+ editedProduct.getProductName()+" is already exists in catalog.");
            }
            product.setProductId(id);
            product.setProductName(editedProduct.getProductName());
            product.setProductDescription(editedProduct.getProductDescription());
            product.setProductPrice(editedProduct.getProductPrice());



            if (checkDuplicateImageName(file.getOriginalFilename(),id)){
                throw new clothShopException(ExceptionResponse.ERROR_CODE.PRODUCT_IMAGE_FILENAME_CONFILCT,"The image name: "+file.getOriginalFilename()+" already exists please change a name.");
            }
            if(file!=(null)){
                fileStorageService.delete(product.getImage());
                product.setImage(file.getOriginalFilename());
                fileStorageService.store(file);
            }
            product.setImage(file.getOriginalFilename());
            product.setManufactureDate(editedProduct.getManufactureDate());
            product.setColors(editedProduct.getColors());
            product.setBrand(editedProduct.getBrand());
            return productRepository.save(product);

        });
    }

    public void addProductToCatalog(MultipartFile file,Product newProduct){

        if(file.equals(null)||newProduct.equals(null)){
            throw new clothShopException(ExceptionResponse.ERROR_CODE.FILE_EMPTY,"Product or File cannot be EMPTY");
        }

        if (checkDuplicateProductName(newProduct.getProductName(), newProduct.getProductId())){
            throw new clothShopException(ExceptionResponse.ERROR_CODE.PRODUCT_NAME_CONFLICT,"CANNOT ADD! your new product name: "+ newProduct.getProductName()+" is already exists in catalog.");
        }
            if (productRepository.lastedProductId()==null){

                newProduct.setProductId(1);
            }else{

                newProduct.setProductId(productRepository.lastedProductId()+1);
            }
            if (checkDuplicateProductName(newProduct.getProductName(),newProduct.getProductId())){
            throw new clothShopException(ExceptionResponse.ERROR_CODE.PRODUCT_NAME_CONFLICT,"CANNOT ADD! your new product name: "+ newProduct.getProductName()+" is already exists in catalog.");
            }
            if(checkDuplicateImageName(file.getOriginalFilename(), newProduct.getProductId())){
                throw new clothShopException(ExceptionResponse.ERROR_CODE.PRODUCT_IMAGE_FILENAME_CONFILCT,"The image name: "+ file.getOriginalFilename()+" already exists please change a name.");
            }
            newProduct.setImage(file.getOriginalFilename());
            fileStorageService.store(file);
            productRepository.save(newProduct);
    }

    public boolean checkDuplicateImageName(String imageName,int id){
        List<Product> products = productRepository.findAll();
        for (Product p : products){
            if (p.getImage().equals(imageName) && p.getProductId()!=id){
                return true;
            }
        }
        return false;
    }

    public boolean checkDuplicateProductName(String productName,int id){
        List<Product> products = productRepository.findAll();
        for (Product p : products){
            if (p.getProductName().equals(productName) && p.getProductId()!=id){
                return true;
            }
        }
        return false;
    }


}
