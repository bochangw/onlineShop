package com.laioffer.onlineShop.repository;

import com.laioffer.onlineShop.entity.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesOrderRepository extends JpaRepository<SalesOrder, Integer> {
}
