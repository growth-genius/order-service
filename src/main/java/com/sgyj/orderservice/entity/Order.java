package com.sgyj.orderservice.entity;

import com.sgyj.orderservice.dto.OrderDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 120, unique = true)
    private String productId;
    @Column(nullable = false)
    private Integer totalPrice;
    @Column(nullable = false)
    private Integer unitPrice;
    @Column(nullable = false)
    private String accountId;
    @Column(nullable = false, unique = true)
    private String orderId;
    @Column(nullable = false, updatable = false, insertable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    public Order( OrderDto orderDto ) {
        this.productId = orderDto.getProductId();
        this.totalPrice = orderDto.getTotalPrice();
        this.unitPrice = orderDto.getUnitPrice();
        this.accountId = orderDto.getAccountId();
        this.orderId = orderDto.getOrderId();
        this.createdAt = orderDto.getCreatedAt();
    }

}
