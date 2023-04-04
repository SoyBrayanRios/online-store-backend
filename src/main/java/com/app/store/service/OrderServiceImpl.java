package com.app.store.service;

import com.app.store.dao.OrderRepository;
import com.app.store.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getOrdersByUser(Long userId) {
        return orderRepository.findOrdersByUser(userId);
    }
}
