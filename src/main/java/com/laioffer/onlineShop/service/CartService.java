package com.laioffer.onlineShop.service;

import com.laioffer.onlineShop.entity.Cart;
import com.laioffer.onlineShop.entity.CartItem;
import com.laioffer.onlineShop.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    public Cart updateACart(Cart cart) {
        Cart cartToUpdate = cartRepository.getOne(cart.getId());
        cartToUpdate.setCartItem(cart.getCartItem());
        cartToUpdate.setTotalPrice(cart.getTotalPrice());
        cartRepository.save(cartToUpdate);
        if(cartRepository.findById(cart.getId()).isPresent()) return cartRepository.findById(cart.getId()).get();
        return null;
    }

    public Cart calTotalPrice(Cart cart) {
        double total = 0.0;
        for(CartItem cartItem: cart.getCartItem()) {
            total += cartItem.getPrice();
        }
        cart.setTotalPrice(total);
        return cart;
    }

    public void deleteById(int id) {
        cartRepository.deleteById(id);
    }
}
