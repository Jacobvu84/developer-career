package jacob.vu.coffee.controllers;

import jacob.vu.coffee.models.Product;
import jacob.vu.coffee.models.ResponseObject;
import jacob.vu.coffee.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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
    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id){

        Optional<Product> product = repository.findById(id);

        if (product.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "Query product successfully", product)
            );
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Failed", "Can't found product with id=" + id, product)
            );
        }
    }
}
