package dev.aditya.ecommercejavaproject.controller;

import dev.aditya.ecommercejavaproject.Exception.productNotFound;
import dev.aditya.ecommercejavaproject.dtos.productRequestDto;
import dev.aditya.ecommercejavaproject.models.product;
import dev.aditya.ecommercejavaproject.service.productService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Collections;
import java.util.List;

@RestController
public class productController {
    private productService prodService;
        // Dependency Injection
    public productController(@Qualifier("dbProductService") productService prodService) {
        this.prodService = prodService;
    }
        // Get Particular product Details based on id
        @GetMapping("/products/{id}")
        public product getProductDetails (@PathVariable("id") Long productId) throws productNotFound {
        return prodService.getSingleProducts(productId);
    }
        // Get All Products
        @GetMapping("/products")
        public ResponseEntity getAllProducts () {
        try {
            List<product> products = prodService.getAllProducts();
            return new ResponseEntity<>(products, HttpStatusCode.valueOf(200));
        }catch( HttpClientErrorException.NotFound e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.EMPTY_LIST);
        } catch (productNotFound e) {
            throw new RuntimeException(e);
        }
        }
        // Get All categories
        @GetMapping("/products/categories")
        public List<String> getAllCategories () throws productNotFound {
        return prodService.getAllCategories();
    }
        // Get products in specific category
        @GetMapping("/products/category/{Title}")
        public List<product> getSpecificCategoryProducts (@PathVariable("Title") String Title) throws productNotFound {
        return prodService.categoryBasedProducts(Title);
    }
        ;
        // Add new product
        @PostMapping("/products")
        public product addProduct (@RequestBody productRequestDto request){
        return prodService.addProduct(request.getTitle(),
                request.getDescription(),
                request.getCategory(),
                request.getPrice(),
                request.getImage()
        );
    }
        // update Products
        @PutMapping("products/{Id}")
        public product updateProducts (@PathVariable("Id") Long Id, @RequestBody productRequestDto request){
        return prodService.updateProduct(Id, request.getTitle(), request.getPrice(), request.getDescription()
                , request.getImage(), request.getCategory());
    }
        // Delete Products
        @DeleteMapping("/products/{Id}")
        public void deleteProduct (@PathVariable("Id") Long Id){
        prodService.deleteProduct(Id);
    }
}
