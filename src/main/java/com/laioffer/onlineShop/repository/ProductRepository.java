package com.laioffer.onlineShop.repository;

import com.laioffer.onlineShop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
