package com.sgyj.orderservice.controller;

import static com.sgyj.orderservice.utils.ApiUtil.success;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sgyj.orderservice.dto.OrderDto;
import com.sgyj.orderservice.entity.Order;
import com.sgyj.orderservice.form.RequestOrder;
import com.sgyj.orderservice.form.ResponseOrder;
import com.sgyj.orderservice.service.KafkaProducer;
import com.sgyj.orderservice.service.OrderService;
import com.sgyj.orderservice.utils.ApiUtil.ApiResult;
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

    private final OrderService orderService;
    private final KafkaProducer kafkaProducer;

    @PostMapping("/account-id/{accountId}/orders")
    public ApiResult<ResponseOrder> createOrder( @RequestBody RequestOrder requestOrder, @PathVariable String accountId ) throws JsonProcessingException {
        OrderDto orderDto = OrderDto.from(requestOrder);
        orderDto.setAccountId( accountId );
        OrderDto savedOrder = orderService.createOrder( orderDto );
        ResponseOrder responseOrder = ResponseOrder.from(savedOrder);
        kafkaProducer.send("example-order-topic", orderDto);
        return success( responseOrder, HttpStatus.CREATED );
    }

    @GetMapping("/user-id/{userId}/orders")
    public ApiResult<List<ResponseOrder>> getOrders( @PathVariable String userId) {
        Iterable<Order> orderList = orderService.getOrderByUserId( userId );
        List<ResponseOrder> result = new ArrayList<>();
        orderList.forEach( o -> result.add( ResponseOrder.from(o) ));
        return success(result);
    }

}
