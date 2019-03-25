package ru.itpark.service;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itpark.domain.Product;
import ru.itpark.exception.ProductNotFoundException;
import ru.itpark.repository.ProductsRepository;


import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ProductsServiceImpl implements ProductsService {
    private final ProductsRepository productsRepository;
    private final Environment environment;

    public ProductsServiceImpl(ProductsRepository productsRepository, Environment environment) {
        this.productsRepository = productsRepository;
        this.environment = environment;
    }

    @Override
    public List<Product> findAll() {
        return productsRepository.findAll();
    }

    @Override
    public Product findById(int id) {
        return productsRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id + " not found"));  // ссылка на конструктор
    }

    @Override
    public void removeById(int id) {
        // FIXME: файл тоже нужно удалять
        productsRepository.deleteById(id);
    }

    @Override
    public void add(Product product, MultipartFile image) {
        Product saved = productsRepository.save(product);

        Path path = Paths.get(
                environment.getProperty("user.dir"),
                "upload",
                "static"
        );
        try {
            image.transferTo(
                    path.resolve(saved.getId() + ".jpg").toFile()
            );
        } catch (IOException e) {
            // TODO: выкидывать собственный exception
            e.printStackTrace();
        }
    }

    @Override
    public void buy(Product product) {
        Product buy1 = productsRepository.save(product);
        Path path = Paths.get(
                environment.getProperty("user.dir"),
                "upload",
                "static"
        );
    }



    // TODO: 1. либо конвертировать всё в JPG
    // TODO: 2. выбирать нужные расширения (либо в заметке хранить расширение картинки, либо могу настроить сервер)


    // FIXME: фильтровать плохие файлы

}


