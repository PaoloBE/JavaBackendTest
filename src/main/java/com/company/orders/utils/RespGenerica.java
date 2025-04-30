package com.company.orders.utils;

import lombok.Data;

@Data
public class RespGenerica<T> {
    private String type;
    private int estado;
    private String message;
    private T body;
    public RespGenerica() {
        type = "";
        estado = 0;
        message = "";
        body = null;
    }
    public RespGenerica(String type, int estado, String message, T body) {
        this.type = type;
        this.estado = estado;
        this.message = message;
        this.body = body;
    }

}
