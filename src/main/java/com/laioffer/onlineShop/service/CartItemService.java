package com.laioffer.onlineShop.service;

import com.laioffer.onlineShop.entity.CartItem;
import com.laioffer.onlineShop.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemService {

    @Autowired
    CartItemRepository cartItemRepository;

    public void removeEmptyCartItem() {
        for(CartItem cartItem: cartItemRepository.findAll()) {
            if(cartItem.getQuantity() == 0) {
                cartItemRepository.deleteById(cartItem.getId());
            }
        }
    }
}
