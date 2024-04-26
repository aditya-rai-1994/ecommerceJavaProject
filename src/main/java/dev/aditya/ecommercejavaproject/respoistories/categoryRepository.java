package dev.aditya.ecommercejavaproject.respoistories;

import dev.aditya.ecommercejavaproject.models.category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface categoryRepository extends JpaRepository<category, Long> {
    category findByTitle(String title);

    @Override
    List<category> findAll();
}
