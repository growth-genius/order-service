package com.sgyj.orderservice.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Schema {

    private String type;
    private List<Field> fields;

    private boolean optional;

    private String name;

}
