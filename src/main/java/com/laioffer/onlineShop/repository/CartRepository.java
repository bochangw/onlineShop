package com.laioffer.onlineShop.repository;

import com.laioffer.onlineShop.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
