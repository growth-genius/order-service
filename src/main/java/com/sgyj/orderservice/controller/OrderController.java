package com.sgyj.orderservice.controller;

import com.sgyj.orderservice.dto.OrderDto;
import com.sgyj.orderservice.entity.Order;
import com.sgyj.orderservice.form.RequestOrder;
import com.sgyj.orderservice.form.ResponseOrder;
import com.sgyj.orderservice.service.OrderService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order-service")
@RequiredArgsConstructor
public class OrderController {

    private final Environment environment;
    private final OrderService orderService;

    @GetMapping("/health_check")
    private String status() {
        return String.format( "It's Working in Order Service on PORT %s", environment.getProperty("local.server.port") );
    }

    @PostMapping("/account-id/{accountId}/orders")
    public ResponseEntity<ResponseOrder> createOrder( @RequestBody RequestOrder requestOrder, @PathVariable String accountId ) {
        OrderDto orderDto = OrderDto.from(requestOrder);
        orderDto.setAccountId( accountId );
        OrderDto savedOrder = orderService.createOrder( orderDto );
        ResponseOrder responseOrder = ResponseOrder.from(savedOrder);
        return ResponseEntity.status( HttpStatus.CREATED ).body( responseOrder );
    }

    @GetMapping("/user-id/{userId}/orders")
    public ResponseEntity<List<ResponseOrder>> getOrders( @PathVariable String userId) {
        Iterable<Order> orderList = orderService.getOrderByUserId( userId );
        List<ResponseOrder> result = new ArrayList<>();
        orderList.forEach( o -> result.add( ResponseOrder.from(o) ));
        return ResponseEntity.ok(result);
    }

}
