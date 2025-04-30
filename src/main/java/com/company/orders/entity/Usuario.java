package com.company.orders.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Usuario {
    private String id_usuario;
    private String nombre;
}
