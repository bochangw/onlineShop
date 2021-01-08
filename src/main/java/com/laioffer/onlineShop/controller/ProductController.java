package com.laioffer.onlineShop.controller;

import com.laioffer.onlineShop.entity.Product;
import com.laioffer.onlineShop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/product")
    public List<Product> getAll() {
        return productService.getAll();
    }

    @PostMapping("/product")
    public List<Product> add(@RequestBody Product product) {
        return productService.add(product);
    }

    @PutMapping("/product")
    public List<Product> update(@RequestBody Product product) {
        return productService.update(product);
    }
}
