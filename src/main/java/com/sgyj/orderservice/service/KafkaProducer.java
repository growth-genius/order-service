package com.sgyj.orderservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sgyj.orderservice.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public OrderDto send(String kafkaTopic, OrderDto orderDto) throws JsonProcessingException {
        String jsonString = objectMapper.writeValueAsString(orderDto);
        kafkaTemplate.send(kafkaTopic, jsonString);
        log.info("Kafka Producer send data from the Order microservice : " + orderDto);
        return orderDto;
    }


}
