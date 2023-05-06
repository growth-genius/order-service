package com.sgyj.orderservice.service;

import com.sgyj.orderservice.dto.OrderDto;
import com.sgyj.orderservice.entity.Order;
import com.sgyj.orderservice.repository.OrderRepository;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public OrderDto createOrder( OrderDto orderDto ) {
        orderDto.setOrderId( UUID.randomUUID().toString() );
        orderDto.setTotalPrice( orderDto.getUnitPrice() * orderDto.getQty() );

        Order order = new Order( orderDto );
        orderRepository.save( order );
        return OrderDto.from(order);
    }

    @Override
    public OrderDto getOrderByOrderId( String orderId ) {
        Optional<Order> byOrderId = orderRepository.findByOrderId( orderId );
        OrderDto orderDto = OrderDto.empty();
        if(byOrderId.isPresent()) {

        }
        return orderDto;
    }

    @Override
    public Iterable<Order> getOrderByUserId( String userId ) {
        return orderRepository.findByAccountId( userId );
    }
}
