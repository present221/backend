package pdm.clothshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdm.clothshop.exception.ExceptionResponse;
import pdm.clothshop.exception.clothShopException;
import pdm.clothshop.models.Color;
import pdm.clothshop.repositories.ColorRepository;

import java.util.List;

@Service
public class ColorService {
    @Autowired
    private ColorRepository colorRepository;

    public List<Color> listColor(){
        return colorRepository.findAll();
    }

    public Color findByColorCode(String colorCode){
        String search = "#"+colorCode;
        if(!colorRepository.existsById(search)){
            throw new clothShopException(ExceptionResponse.ERROR_CODE.COLOR_NOT_EXISTS,"color id: " + search + " does not exist.");
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("color id: " + search + " does not exist."));
        }
        return colorRepository.findById(search).get();
    }
}
