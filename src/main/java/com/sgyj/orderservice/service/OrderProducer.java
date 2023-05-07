package com.sgyj.orderservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.sgyj.orderservice.dto.Field;
import com.sgyj.orderservice.dto.KafkaOrderDto;
import com.sgyj.orderservice.dto.OrderDto;
import com.sgyj.orderservice.dto.Payload;
import com.sgyj.orderservice.dto.Schema;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderProducer {

    private final KafkaTemplate<String ,String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final List<Field> fields = Arrays.asList(
        new Field("string", true, "order_id"),
        new Field("string", true, "product_id"),
        new Field("int32", true, "qty"),
        new Field("int32", true, "total_price"),
        new Field("int32", true, "unit_price")
    );

    private final Schema schema = Schema.builder()
        .type("struct")
        .fields(fields)
        .optional(false)
        .name("orders")
        .build();
    public OrderDto send(String kafkaTopic, OrderDto orderDto) throws JsonProcessingException {
        Payload payload = Payload.from(orderDto);
        KafkaOrderDto kafkaOrderDto = KafkaOrderDto.of(schema, payload);
        objectMapper.setPropertyNamingStrategy( PropertyNamingStrategies.SNAKE_CASE );
        String jsonString = objectMapper.writeValueAsString(kafkaOrderDto);
        kafkaTemplate.send(kafkaTopic, jsonString);
        log.info("Order Producer send data from the Order microservice : " + kafkaOrderDto);
        return orderDto;
    }
}
