package com.company.orders.clases;

import com.company.orders.entity.Item;
import lombok.Data;

import java.util.List;

@Data
public class NewOrderRequest {
    private List<Item> items;
    private String id_usuario;
}
