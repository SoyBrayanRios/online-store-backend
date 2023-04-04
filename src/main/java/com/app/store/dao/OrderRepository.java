package com.app.store.dao;

import com.app.store.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "select * from orders where user_id=:userId", nativeQuery = true)
    public List<Order> findOrdersByUser(Long userId);
}
