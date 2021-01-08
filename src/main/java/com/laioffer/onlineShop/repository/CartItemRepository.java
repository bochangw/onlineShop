package com.laioffer.onlineShop.repository;

import com.laioffer.onlineShop.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
}
