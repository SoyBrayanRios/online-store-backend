package com.app.store.controller;

import com.app.store.entity.Order;
import com.app.store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{userId}")
    public List<Order> findOrdersByUser(@PathVariable(name = "userId") Long userId) {
        return orderService.getOrdersByUser(userId);
    }

}
