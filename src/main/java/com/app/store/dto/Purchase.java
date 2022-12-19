package com.app.store.dto;

import com.app.store.entity.Address;
import com.app.store.entity.Customer;
import com.app.store.entity.Order;
import com.app.store.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;

}
