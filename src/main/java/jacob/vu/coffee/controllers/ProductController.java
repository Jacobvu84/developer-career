package jacob.vu.coffee.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/products")
public class ProductController {

    // This request is: http://localhost:8080/api/v1/products
    @GetMapping("")
    List<String> getAllProducts(){
        return List.of("Iphone 14", "Samsung");
    }
}
