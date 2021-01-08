package com.laioffer.onlineShop.controller;

import com.laioffer.onlineShop.entity.*;
import com.laioffer.onlineShop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    CustomerService customerService;

    @Autowired
    CartItemService cartItemService;

    @Autowired
    CartService cartService;

    @Autowired
    AuthoritiesService authoritiesService;

    @Autowired
    SalesOrderService salesOrderService;

    @GetMapping("/checkout")
    public String checkout(HttpServletRequest request) {
        String emailId = request.getSession().getAttribute("user").toString();
        Customer customer = userService.getAUserByEmailId(emailId).getCustomer();
        SalesOrder salesOrder = new SalesOrder();
        salesOrder.setCart(customer.getCart());
        salesOrder.setCustomer(customer);
        salesOrder.setBillingAddress(customer.getBillingAddress());
        salesOrder.setShippingAddress(customer.getShippingAddress());
        salesOrderService.saveASalesOrder(salesOrder);
        customer.setCart(new Cart());
        customerService.updateACustomerCart(customer);
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String showCart(Model model, HttpServletRequest request) {
        List<Product> listProducts = productService.getAll();
        model.addAttribute("listProducts", listProducts);
        String emailId = request.getSession().getAttribute("user").toString();
        String authoritiesLevel = authoritiesService.getAuthoritiesLevel(emailId);
        if(authoritiesLevel.equals("USER")) {
            model.addAttribute("productUrl", "requestAdmin");
            model.addAttribute("productButtonValue", "Request access");
        } else {
            model.addAttribute("productUrl", "showProducts");
            model.addAttribute("productButtonValue", "Edit products");
        }
        model.addAttribute("firstName", userService.getAUserByEmailId(emailId).getCustomer().getFirstName());
        List<CartItem> cartItems = userService.getAUserByEmailId(emailId).getCustomer().getCart().getCartItem();
        model.addAttribute("cartItems", cartItems);
        double totalPrice = userService.getAUserByEmailId(emailId).getCustomer().getCart().getTotalPrice();
        model.addAttribute("totalPrice", totalPrice);
        return "shoppingCart";
    }

    @GetMapping("/cartData")
    @ResponseBody
    public List<CartItem> showCartData(Model model, HttpServletRequest request) {
        String emailId = request.getSession().getAttribute("user").toString();
        List<CartItem> cartItems = userService.getAUserByEmailId(emailId).getCustomer().getCart().getCartItem();
        return cartItems;
    }

    @PostMapping("/addToCart")
    public String addToCart(Model model, HttpServletRequest request, @RequestParam int id) {
        Product product = productService.getAProductById(id);
        String emailId = request.getSession().getAttribute("user").toString();
        model.addAttribute("firstName", userService.getAUserByEmailId(emailId).getCustomer().getFirstName());
        Customer customer = userService.getAUserByEmailId(emailId).getCustomer();
        Cart cart = customer.getCart();
        List<CartItem> cartItems = cart.getCartItem();
        boolean added = false;
        for(int i = 0;i < cartItems.size();i ++) {
            CartItem cartItem = cartItems.get(i);
            if(cartItem.getProduct().getId() == product.getId()) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                cartItem.setPrice(cartItem.getPrice() + product.getProductPrice());
                added = true;
            }
        }
        if(!added) {
            CartItem cartItem = new CartItem();
            cartItem.setQuantity(1);
            cartItem.setPrice(product.getProductPrice());
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItems.add(cartItem);
        }
        cart.setCartItem(cartItems);
        cart = cartService.calTotalPrice(cart);
        cartService.updateACart(cart);
//        customer.setCart(cart);
//        customerService.updateACustomerCart(customer);
        return "redirect:/cart";
    }

    @PostMapping("/removeFromCart")
    public String removeFromCart(Model model, HttpServletRequest request, @RequestParam int id) {
        Product product = productService.getAProductById(id);
        String emailId = request.getSession().getAttribute("user").toString();
        model.addAttribute("firstName", userService.getAUserByEmailId(emailId).getCustomer().getFirstName());
        Customer customer = userService.getAUserByEmailId(emailId).getCustomer();
        Cart cart = customer.getCart();
        List<CartItem> cartItems = cart.getCartItem();
        List<CartItem> cartItemsUpdated = new ArrayList<>();
        for(int i = 0;i < cartItems.size();i ++) {
            CartItem cartItem = cartItems.get(i);
            if(cartItem.getProduct().getId() == product.getId()) {
                cartItem.setQuantity(cartItem.getQuantity() - 1);
                cartItem.setPrice(cartItem.getPrice() - product.getProductPrice());
            }
            if(cartItem.getQuantity() != 0) {
                cartItemsUpdated.add(cartItem);
            }
        }
        cart.setCartItem(cartItemsUpdated);
        cart = cartService.calTotalPrice(cart);
        customer.setCart(cart);
        customerService.updateACustomerCart(customer);
        cartItemService.removeEmptyCartItem();
        return "redirect:/cart";
    }
}
