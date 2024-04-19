package dev.aditya.ecommercejavaproject.service;

import dev.aditya.ecommercejavaproject.Exception.productNotFound;
import dev.aditya.ecommercejavaproject.models.category;
import dev.aditya.ecommercejavaproject.models.product;

import java.util.List;

public interface productService {
    product getSingleProducts(Long productId) throws productNotFound;
    List<product> getAllProducts() throws productNotFound;
    List<String>getAllCategories() throws productNotFound;
    List<product>categoryBasedProducts(String Title) throws productNotFound;
    product addProduct(String title, String description,
                       String Category, double price,
                       String image);
    product updateProduct(Long id, String title, double price, String description,
                          String image, String category);
    void deleteProduct(Long Id);
}
