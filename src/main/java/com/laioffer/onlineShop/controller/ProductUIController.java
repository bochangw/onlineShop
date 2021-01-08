package com.laioffer.onlineShop.controller;

import com.laioffer.onlineShop.entity.Product;
import com.laioffer.onlineShop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductUIController {

    @Autowired
    ProductService productService;

    @GetMapping("/showProducts")
    public String viewAllProducts(Model model) {
        List<Product> listProducts = productService.getAll();
        model.addAttribute("listProducts", listProducts);
        return "index";
    }

    @PostMapping("/showProducts")
    public String addProducts(@ModelAttribute Product product, Model model) {
        if(product.getProductPrice() == 0.0) {
            // delete
            productService.delete(product);
        } else {
            if(product.getId() != 0) {
                // update
                if(productService.hasProduct(product)) {
                    productService.update(product);
                }
            } else {
                // add
                productService.add(product);
            }
        }
        List<Product> listProducts = productService.getAll();
        model.addAttribute("listProducts", listProducts);
        return "index";
    }

    @GetMapping("/addNewProduct")
    public String addNewProduct(@ModelAttribute Product product, Model model) {
        return "addNewProduct";
    }

    @GetMapping("/updateProduct")
    public String updateProduct(@RequestParam int id, @ModelAttribute Product product, Model model) {
        product = productService.getAProductById(id);
        model.addAttribute("product", product);
        return "updateProduct";
    }

    @GetMapping("/deleteProduct")
    public String deleteProduct(@ModelAttribute Product product, Model model) {
        return "deleteProduct";
    }
}
