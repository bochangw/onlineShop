package com.laioffer.onlineShop.controller;

import com.laioffer.onlineShop.entity.Customer;
import com.laioffer.onlineShop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Component
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/customer")
    public List<Customer> getAll() {
        return customerService.getAll();
    }

    @PostMapping("/customer")
    public List<Customer> save(@RequestBody Customer customer) {
        return customerService.save(customer);
    }

    @DeleteMapping("/customer")
    public List<Customer> deleteAll() {
        return customerService.deleteAll();
    }
}
