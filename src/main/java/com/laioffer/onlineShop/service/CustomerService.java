package com.laioffer.onlineShop.service;

import com.laioffer.onlineShop.entity.Customer;
import com.laioffer.onlineShop.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public List<Customer> save(Customer customer) {
        customerRepository.save(customer);
        return customerRepository.findAll();
    }

    public List<Customer> deleteAll() {
        customerRepository.deleteAll();
        return customerRepository.findAll();
    }

    public Customer updateACustomerCart(Customer customer) {
        Customer customerToUpdate = customerRepository.getOne(customer.getId());
        customerToUpdate.setCart(customer.getCart());
        customerRepository.save(customer);
        if(customerRepository.findById(customer.getId()).isPresent()) return customerRepository.findById(customer.getId()).get();
        return null;
    }
}
