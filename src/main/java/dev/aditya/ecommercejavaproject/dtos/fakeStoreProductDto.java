package dev.aditya.ecommercejavaproject.dtos;

import dev.aditya.ecommercejavaproject.models.category;
import dev.aditya.ecommercejavaproject.models.product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class fakeStoreProductDto {
    private Long id;
    private String title;
    private String image;
    private String description;
    private String category;
    private double price;

    public product toProduct(){
        product returnProduct = new product();
        returnProduct.setId(id);
        returnProduct.setDescription(description);
        returnProduct.setTitle(title);
        returnProduct.setPrice(price);
        returnProduct.setImageURL(image);
        category productCategory = new category();
        productCategory.setTitle(category);
        returnProduct.setCategory(productCategory);
        return returnProduct;
    }

}
