package dev.aditya.ecommercejavaproject.service;

import dev.aditya.ecommercejavaproject.Exception.productNotFound;
import dev.aditya.ecommercejavaproject.models.category;
import dev.aditya.ecommercejavaproject.models.product;
import dev.aditya.ecommercejavaproject.respoistories.ProductRepository;
import dev.aditya.ecommercejavaproject.respoistories.categoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("dbProductService")
public class dbProductService implements productService{
    private categoryRepository categoryRepository;
    private ProductRepository productRepository;

    public dbProductService(categoryRepository categoryRepository, ProductRepository productRepository){
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }
    @Override
    public product getSingleProducts(Long productId) throws productNotFound {
        return productRepository.findByIdEquals(productId);
    }

    @Override
    public List<product> getAllProducts() throws productNotFound {
        return productRepository.findAll();
    }

    @Override
    public List<String> getAllCategories() throws productNotFound {
        List<category> categories = categoryRepository.findAll();
        return categories.stream().map(category::getTitle).collect(Collectors.toList());
    }

    @Override
    public List<product> categoryBasedProducts(String Title) throws productNotFound {
        return null;
    }

    @Override
    public product addProduct(String title, String description, String Category, double price, String image) {
        product newProduct = new product();
        newProduct.setPrice(price);
        newProduct.setImageURL(image);
        newProduct.setDescription(description);
        newProduct.setTitle(title);
        category findCategory = categoryRepository.findByTitle(Category);
        if(findCategory== null){
            findCategory = new category();
            findCategory.setTitle(Category);
        }
        newProduct.setCategory(findCategory);
        product saveProduct = productRepository.save(newProduct);
      return saveProduct;
    }

    @Override
    public product updateProduct(Long id, String title, double price, String description, String image, String category) {
        return null;
    }

    @Override
    public void deleteProduct(Long Id) {

    }
}
