package com.sgyj.orderservice.dto;

import static org.springframework.beans.BeanUtils.copyProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Payload {

    private String orderId;
    private String accountId;
    private String productId;
    private int qty;
    private int totalPrice;
    private int unitPrice;

    public static Payload from(OrderDto orderDto) {
        Payload payload = new Payload();
        copyProperties(orderDto, payload);
        return payload;
    }
}
