package com.app.store.service;

import com.app.store.entity.Order;

import java.util.List;

public interface OrderService {

    public List<Order> getOrdersByUser(Long userId);
}
