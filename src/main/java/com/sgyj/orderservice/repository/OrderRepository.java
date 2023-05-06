package com.sgyj.orderservice.repository;

import com.sgyj.orderservice.entity.Order;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByOrderId( String orderId);

    Iterable<Order> findByAccountId(String userId);

}
