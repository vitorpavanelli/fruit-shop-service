package com.devbox.fruitshop.repositories;

import com.devbox.fruitshop.repositories.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {}
