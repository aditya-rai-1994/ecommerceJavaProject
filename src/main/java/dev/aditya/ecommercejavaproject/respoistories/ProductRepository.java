package dev.aditya.ecommercejavaproject.respoistories;

import dev.aditya.ecommercejavaproject.models.category;
import dev.aditya.ecommercejavaproject.models.product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<product,Long> {
    product save(product p);

    @Override
    List<product> findAll();

    product findByIdEquals(Long Id);
    List<product> findAllByCategory(category category);
    List<product> findAllByCategory_TitleLike(String category);

};
