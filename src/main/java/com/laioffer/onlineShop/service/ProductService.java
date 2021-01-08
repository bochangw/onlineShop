package com.laioffer.onlineShop.service;

import com.laioffer.onlineShop.entity.Product;
import com.laioffer.onlineShop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public List<Product> add(Product product) {
        productRepository.save(product);
        return productRepository.findAll();
    }

    public List<Product> delete(Product product) {
        productRepository.deleteById(product.getId());
        return productRepository.findAll();
    }

    public List<Product> update(Product product) {
        Product productToUpdate = productRepository.getOne(product.getId());
        productToUpdate.update(product);
        productRepository.save(productToUpdate);
        return productRepository.findAll();
    }

    public Product getAProductById(int id) {
        Optional<Product> productEntity = productRepository.findById(id);
        if(productEntity.isPresent()) return productEntity.get();
        return new Product();
    }

    public Boolean hasProduct(Product product) {
        return productRepository.existsById(product.getId());
    }
}
