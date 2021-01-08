package com.laioffer.onlineShop.service;

import com.laioffer.onlineShop.entity.SalesOrder;
import com.laioffer.onlineShop.repository.SalesOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesOrderService {

    @Autowired
    SalesOrderRepository salesOrderRepository;

    public List<SalesOrder> saveASalesOrder(SalesOrder salesOrder) {
        salesOrderRepository.save(salesOrder);
        return salesOrderRepository.findAll();
    }

    public List<SalesOrder> getAll() {
        return salesOrderRepository.findAll();
    }
}
