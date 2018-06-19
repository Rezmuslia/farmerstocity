package ru.itpark.service;

import org.springframework.web.multipart.MultipartFile;
import ru.itpark.domain.Product;
import ru.itpark.domain.Product;

import java.util.List;

public interface ProductsService {
    List<Product> findAll();

    Product findById(int id);

    void removeById(int id);

    void add(Product product, MultipartFile image);
}
