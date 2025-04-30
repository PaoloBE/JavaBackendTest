package com.company.orders.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("orden")
public class Orden {
    @Id
    private BigInteger id;
    private List<Item> items;
    private String fecha_creacion;
    private Double total;
    private String id_usuario;

    public Orden(List<Item> items, String fecha_creacion, String id_usuario, Double total) {
        this.items = items;
        this.fecha_creacion = fecha_creacion;
        this.id_usuario = id_usuario;
        this.total = total;
    }
}
