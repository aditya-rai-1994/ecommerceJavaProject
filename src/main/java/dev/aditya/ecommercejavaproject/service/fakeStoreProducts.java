package dev.aditya.ecommercejavaproject.service;

import dev.aditya.ecommercejavaproject.dtos.fakeStoreProductDto;
import dev.aditya.ecommercejavaproject.models.product;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class fakeStoreProducts implements productService{
    public RestTemplate restTemplate;
    public fakeStoreProducts(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    @Override
    public product getSingleProducts(Long productId) {
        fakeStoreProductDto fakeStore= restTemplate.getForObject(
                "https://fakestoreapi.com/products/"+ productId,
                fakeStoreProductDto.class);
        return fakeStore.toProduct();
    }

    @Override
    public List<product> getAllProducts() {
        fakeStoreProductDto[] fakeStore = restTemplate.getForObject("https://fakestoreapi.com/products", fakeStoreProductDto[].class);
        List<product> allProductData = new ArrayList<>();
        for(fakeStoreProductDto fakeStoreDto:fakeStore){
            allProductData.add(fakeStoreDto.toProduct());
        }
        return allProductData;
    }
    @Override
    public List<String> getAllCategories() {
        String[] categories=restTemplate.getForObject("https://fakestoreapi.com/products/categories",String[].class);
        return Arrays.asList(categories);
    }

    @Override
    public List<product> categoryBasedProducts(String Title) {
        fakeStoreProductDto[] productsResponse = restTemplate.getForObject("https://fakestoreapi.com/products/category/"+Title,
                fakeStoreProductDto[].class);
        List<product> response = new ArrayList<>();
        for(fakeStoreProductDto productCategory: productsResponse){
            response.add(productCategory.toProduct());
        }
        return response;
    }

    @Override
    public product addProduct(String title, String description, String Category, double price, String image) {
        fakeStoreProductDto fakeStore = new fakeStoreProductDto();
        fakeStore.setTitle(title);
        fakeStore.setDescription(description);
        fakeStore.setPrice(price);
        fakeStore.setImage(image);
        fakeStore.setCategory(Category);
        fakeStoreProductDto fakeStoreResponse = restTemplate.postForObject("https://fakestoreapi.com/products",
                fakeStore,
                fakeStoreProductDto.class);
        return fakeStoreResponse.toProduct();
    }

    @Override
    public product updateProduct(Long Id, String title, double price, String description, String image, String category) {
        fakeStoreProductDto fakeStore = new fakeStoreProductDto();
        fakeStore.setTitle(title);
        fakeStore.setDescription(description);
        fakeStore.setPrice(price);
        fakeStore.setImage(image);
        fakeStore.setCategory(category);
        HttpEntity<fakeStoreProductDto> requestEntity = new HttpEntity<>(fakeStore);
        ResponseEntity<fakeStoreProductDto> response= restTemplate.exchange(
                "https://fakestoreapi.com/products/"+Id, HttpMethod.PUT,
                requestEntity,
                fakeStoreProductDto.class);
        fakeStoreProductDto fakeStoreProduct = response.getBody();
        return fakeStoreProduct.toProduct();
    }

    @Override
    public void deleteProduct(Long Id) {
        restTemplate.delete("\"https://fakestoreapi.com/products/"+Id);
    }


}
