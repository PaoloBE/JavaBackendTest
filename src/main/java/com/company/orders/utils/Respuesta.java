package com.company.orders.utils;

import lombok.Data;

@Data
public class Respuesta {
    private String id_respuesta;
    private String Mensaje;

    public Respuesta(String userDatum, String mensaje) {
        this.id_respuesta = userDatum;
        this.Mensaje = mensaje;
    }
}
