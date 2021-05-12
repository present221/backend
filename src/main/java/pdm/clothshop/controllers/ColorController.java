package pdm.clothshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pdm.clothshop.models.Color;
import pdm.clothshop.services.ColorService;

import java.util.List;


@RestController
@CrossOrigin
public class ColorController {
    @Autowired
    private ColorService colorService;

    @GetMapping("/colors")
    public List<Color> listColor(){
        return colorService.listColor();
    }

    @GetMapping("/colors/{colorCode}")
    public Color getColor(@PathVariable String colorCode) {
    return  colorService.findByColorCode(colorCode);
    }
}
