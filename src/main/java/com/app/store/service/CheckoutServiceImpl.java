package com.app.store.service;

import com.app.store.dao.CustomerRepository;
import com.app.store.dto.Purchase;
import com.app.store.dto.PurchaseResponse;
import com.app.store.entity.Customer;
import com.app.store.entity.Order;
import com.app.store.entity.OrderItem;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService{

    private CustomerRepository customerRepository;

    public CheckoutServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
        //Retrieve the order info from dto
        Order order = purchase.getOrder();
        //Generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);
        //Populate with orderItems
        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(item -> order.add(item));
        //Populate order with billing and shipping address
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());
        //Populate customer with order
        Customer customer = purchase.getCustomer();
        customer.add(order);
        //Save to the database
        customerRepository.save(customer);
        //return a response
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        //Generate a random UUID number (UUID version-4)
        return UUID.randomUUID().toString();
    }
}
