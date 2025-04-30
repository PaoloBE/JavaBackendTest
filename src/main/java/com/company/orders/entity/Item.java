package com.company.orders.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Item {
    private String id_producto;
    private Integer cantidad;
    private Double precio_unitario;
}
