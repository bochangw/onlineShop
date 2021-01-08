package com.laioffer.onlineShop.repository;

import com.laioffer.onlineShop.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
