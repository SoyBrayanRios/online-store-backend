package com.app.store.service;

import com.app.store.dao.*;
import com.app.store.dto.Purchase;
import com.app.store.dto.PurchaseResponse;
import com.app.store.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService{

    private CustomerRepository customerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private AddressRepository addressRepository;

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
        Address billingAddress = addressRepository.findByMainAddressComplement(purchase.getBillingAddress().getMainAddress(), purchase.getBillingAddress().getComplement());
        if (billingAddress != null) {
            order.setBillingAddress(billingAddress);
        } else {
            order.setBillingAddress(addressRepository.save(purchase.getBillingAddress()));
        }

        Address shippingAddress = addressRepository.findByMainAddressComplement(purchase.getShippingAddress().getMainAddress(), purchase.getShippingAddress().getComplement());
        if (shippingAddress != null) {
            order.setShippingAddress(shippingAddress);
        } else {
            order.setShippingAddress(addressRepository.save(purchase.getShippingAddress()));
        }

        //Populate customer with order
        Customer customer = purchase.getCustomer();
        //Check if this is an existing customer
        String email = customer.getEmail();
        Customer customerFromDB = customerRepository.findByEmail(email);
        if (customerFromDB != null) {
            customer = customerFromDB;
        }
        User user = userRepository.getById(purchase.getUserId());
        //Save to the database
        customerRepository.save(customer);
        userRepository.save(user);
        order.setUser(user);
        order.setCustomer(customer);
        order.setStatus(statusRepository.getById(1L));
        orderRepository.save(order);
        //return a response
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        //Generate a random UUID number (UUID version-4)
        return UUID.randomUUID().toString();
    }
}
