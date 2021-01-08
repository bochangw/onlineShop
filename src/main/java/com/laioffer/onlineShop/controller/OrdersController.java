package com.laioffer.onlineShop.controller;

import com.laioffer.onlineShop.entity.SalesOrder;
import com.laioffer.onlineShop.service.SalesOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrdersController {

    @Autowired
    SalesOrderService salesOrderService;

    @GetMapping("/orders")
    public String getOrders(Model model, HttpServletRequest request) {
        String emailId = request.getSession().getAttribute("user").toString();
        List<SalesOrder> salesOrders = salesOrderService.getAll();
        List<SalesOrder> userOrders = new ArrayList<>();
        for(SalesOrder salesOrder: salesOrders) {
            if(salesOrder.getCustomer().getUser().getEmailId().equals(emailId)) {
                userOrders.add(salesOrder);
            }
        }
        model.addAttribute("listOrders", userOrders);
        return "orders";
    }
}
