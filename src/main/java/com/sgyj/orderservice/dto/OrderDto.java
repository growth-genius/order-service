package com.sgyj.orderservice.dto;

import static org.springframework.beans.BeanUtils.copyProperties;

import com.sgyj.orderservice.entity.Order;
import com.sgyj.orderservice.form.RequestOrder;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class OrderDto {

    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;
    private String orderId;
    private String accountId;
    private LocalDateTime createdAt;

    public static OrderDto empty() {
        return new OrderDto();
    }

    private OrderDto() {}

    public static OrderDto from(Order order) {
        OrderDto orderDto = new OrderDto();
        copyProperties(order, orderDto);
        return orderDto;
    }

    public static OrderDto from(RequestOrder requestOrder) {
        OrderDto orderDto = new OrderDto();
        copyProperties(requestOrder, orderDto);
        return orderDto;
    }
}
