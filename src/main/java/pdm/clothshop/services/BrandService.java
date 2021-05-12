package pdm.clothshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdm.clothshop.exception.ExceptionResponse;
import pdm.clothshop.exception.clothShopException;
import pdm.clothshop.models.Brand;
import pdm.clothshop.repositories.BrandRepository;

import java.util.List;


@Service
public class BrandService {
    @Autowired
    private BrandRepository brandRepository;

    public List<Brand> listBrand(){
        return brandRepository.findAll();
    }

    public Brand findById(int id){
        System.out.println(brandRepository.existsById(id));
        if(!brandRepository.existsById(id)){
            throw new clothShopException(ExceptionResponse.ERROR_CODE.BRAND_NOT_EXISTS,"brand id : " +id +" does not exists.");
        }

        return brandRepository.findById(id).get();
    }
}
