package ru.itpark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itpark.domain.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Integer> {


}
