package jacob.vu.coffee.controllers;

import jacob.vu.coffee.models.Product;
import jacob.vu.coffee.models.ResponseObject;
import jacob.vu.coffee.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/products")
public class ProductController {

    @Autowired
    ProductRepository repository;

    // This request is: GET: http://localhost:8080/api/v1/products
    @GetMapping("")
    List<Product> getAllProducts(){

        return repository.findAll();
    }

    // This request is: GET:  http://localhost:8080/api/v1/products/{id}
    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id){

        Optional<Product> product = repository.findById(id);

        return product.isPresent() ?
             ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "Query product successfully", product)
             ):
             ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Failed", "Can't found product with id=" + id, product)
             );
    }

    // This request is: POST: http://localhost:8080/api/v1/products/insert
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertNewProduct(@RequestBody Product newProduct){

        List<Product> products = repository.findByName(newProduct.getName().trim());

        if(products.size()>0){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "product name is already taken", ""));
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Insert new product successfully!", repository.save(newProduct))
        );
    }

    // This request is: PUT: http://localhost:8080/api/v1/products/{id}
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct, @PathVariable Long id){

        Optional<Product> foundProduct = repository.findById(id).map(
                product -> {
                    product.setName(newProduct.getName());
                    product.setPrice(newProduct.getPrice());
                    product.setYearOfManufacture(newProduct.getYearOfManufacture());
                    product.setUrl(newProduct.getUrl());
                    return repository.save(product);
                }
        );

        return foundProduct.isPresent() ?
            ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Update product successfully", foundProduct)
            ):
            ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "product not found", "")
            );
        }

      /* List<Product> products = repository.findByProductId(id);

        for(Product product: products) {
            if(product.getProductId()==id){
                product.setName(newProduct.getName());
                product.setYearOfManufacture(newProduct.getYearOfManufacture());
                product.setPrice(newProduct.getPrice());
                product.setUrl(newProduct.getUrl());
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "update successfully", repository.save(product))
                );
            }

        }
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                new ResponseObject("failed", "product id not found", ""));*/

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id){
       boolean exist = repository.existsById(id);

       if(exist){
           repository.deleteById(id);
           return ResponseEntity.status(HttpStatus.OK).body(
                   new ResponseObject("ok", "the product is deleted!", "")
           );
       }

       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
               new ResponseObject("failed", "product not found!", "")
       );
    }

}
