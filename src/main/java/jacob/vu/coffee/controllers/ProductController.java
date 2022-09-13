package jacob.vu.coffee.controllers;

import jacob.vu.coffee.models.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/products")
public class ProductController {

    // This request is: http://localhost:8080/api/v1/products
    @GetMapping("")
    List<Product> getAllProducts(){

        return List.of(
                new Product(1L, "Iphone 14", 2022, 800.0, ""),
                new Product(2L, "VSmart Pro", 2021, 900.0, ""));
    }
}
