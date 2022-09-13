package jacob.vu.coffee.controllers;

import jacob.vu.coffee.models.Product;
import jacob.vu.coffee.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/products")
public class ProductController {

    @Autowired
    ProductRepository repository;

    // This request is: http://localhost:8080/api/v1/products
    @GetMapping("")
    List<Product> getAllProducts(){

        return repository.findAll();
    }
}
