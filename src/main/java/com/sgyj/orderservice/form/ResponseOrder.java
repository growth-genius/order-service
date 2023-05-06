package com.sgyj.orderservice.form;

import static org.springframework.beans.BeanUtils.copyProperties;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sgyj.orderservice.dto.OrderDto;
import com.sgyj.orderservice.entity.Order;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseOrder {

    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;
    private LocalDateTime createdAt;

    private String orderId;

    public static ResponseOrder from( Order order ) {
        ResponseOrder responseOrder = new ResponseOrder();
        copyProperties(order, responseOrder);
        return responseOrder;
    }

    public static ResponseOrder from (OrderDto orderDto) {
        ResponseOrder responseOrder = new ResponseOrder();
        copyProperties(orderDto, responseOrder);
        return responseOrder;
    }

}
