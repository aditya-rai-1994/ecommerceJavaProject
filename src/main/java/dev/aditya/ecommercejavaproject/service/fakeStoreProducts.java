package dev.aditya.ecommercejavaproject.service;

import dev.aditya.ecommercejavaproject.Exception.productNotFound;
import dev.aditya.ecommercejavaproject.dtos.fakeStoreProductDto;
import dev.aditya.ecommercejavaproject.models.product;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
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
    public product getSingleProducts(Long productId) throws productNotFound {
        ResponseEntity<fakeStoreProductDto> fakeStoreResponse= restTemplate.getForEntity(
                "https://fakestoreapi.com/products/"+ productId,
                fakeStoreProductDto.class);
        fakeStoreProductDto fakeStore = fakeStoreResponse.getBody();
        if(fakeStore == null){
            throw new productNotFound("Product with id "+ productId + "doesn't exist. Retry other products");
        }
        return fakeStore.toProduct();
    }

    @Override
    public  List<product> getAllProducts() throws productNotFound {
        ResponseEntity<fakeStoreProductDto[]> fakeStoreResponse = restTemplate.getForEntity(
                "https://fakestoreapi.com/products", fakeStoreProductDto[].class);
        fakeStoreProductDto[] fakeStore = fakeStoreResponse.getBody();
        if(fakeStoreResponse.getStatusCode()!= HttpStatus.OK){
            throw new productNotFound("Failed to fetch the products from the API");
        }
        List<product> allProductData = new ArrayList<>();
        if(fakeStore!= null) {
            for (fakeStoreProductDto fakeStoreDto : fakeStore) {
                allProductData.add(fakeStoreDto.toProduct());
            }
        }
        return allProductData;
    }
    @Override
    public List<String> getAllCategories() throws productNotFound {
        String[] categories=restTemplate.getForObject("https://fakestoreapi.com/products/categories",String[].class);
        if(categories==null){
            throw new productNotFound("Categories is empty. please add category");
        }
        return Arrays.asList(categories);
    }

    @Override
    public List<product> categoryBasedProducts(String Title) throws productNotFound {
        ResponseEntity<fakeStoreProductDto[]> productsCategoryResponse = restTemplate.getForEntity("https://fakestoreapi.com/products/category/"+Title,
                fakeStoreProductDto[].class);
        fakeStoreProductDto [] productsResponse = productsCategoryResponse.getBody();
        if(productsResponse == null){
            throw new productNotFound(Title+" Category is not available, please try another category");
        }
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
