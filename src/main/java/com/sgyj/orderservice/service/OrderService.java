package com.sgyj.orderservice.service;

import com.sgyj.orderservice.dto.OrderDto;
import com.sgyj.orderservice.entity.Order;

public interface OrderService {

    OrderDto createOrder(OrderDto orderDto);

    OrderDto getOrderByOrderId(String orderId);

    Iterable<Order> getOrderByUserId(String userId);

}
