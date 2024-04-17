package dev.aditya.ecommercejavaproject.controller;

import dev.aditya.ecommercejavaproject.dtos.productRequestDto;
import dev.aditya.ecommercejavaproject.models.product;
import dev.aditya.ecommercejavaproject.service.productService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class productController {
    private productService prodService;
    // Dependency Injection
    public productController(productService prodService){
        this.prodService = prodService;
    }
    // Get Particular product Details based on id
    @GetMapping("/products/{id}")
    public product getProductDetails(@PathVariable("id") Long productId){
        return prodService.getSingleProducts(productId);
    }
    // Get All Products
    @GetMapping("/products")
    public List<product> getAllProducts(){
        return prodService.getAllProducts();
    }
    // Get All categories
    @GetMapping("/products/categories")
    public List<String> getAllCategories(){
        return prodService.getAllCategories();
    }
    // Get products in specific category
    @GetMapping("/products/category/{Title}")
    public List<product> getSpecificCategoryProducts(@PathVariable("Title") String Title){
        return prodService.categoryBasedProducts(Title);
    };
    // Add new product
    @PostMapping("/products")
    public product addProduct(@RequestBody productRequestDto request){
        return prodService.addProduct(request.getTitle(),
                request.getDescription(),
                request.getCategory(),
                request.getPrice(),
                request.getImage()
        );
    }
    // update Products
    @PutMapping("products/{Id}")
    public product updateProducts(@PathVariable("Id") Long Id, @RequestBody productRequestDto request){
        return prodService.updateProduct(Id, request.getTitle(), request.getPrice(), request.getDescription()
        , request.getImage(),request.getCategory());
    }
    // Delete Products
    @DeleteMapping("/products/{Id}")
    public void deleteProduct(@PathVariable("Id") Long Id){
        prodService.deleteProduct(Id);
    }
}
