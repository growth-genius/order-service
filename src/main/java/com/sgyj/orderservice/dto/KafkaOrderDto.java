package com.sgyj.orderservice.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class KafkaOrderDto implements Serializable {
    private Schema schema;
    private Payload payload;

    public static KafkaOrderDto of(Schema schema, Payload payload) {
        return new KafkaOrderDto(schema, payload);
    }

}
