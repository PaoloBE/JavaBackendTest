package com.company.orders.service;

import com.company.orders.entity.Orden;
import com.company.orders.repository.OrdenRepository;
import com.company.orders.utils.RespGenerica;
import com.company.orders.utils.Respuesta;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.company.orders.utils.Constantes.*;

@Service
@Transactional
public class OrdenService {
    private final OrdenRepository repository;

    public OrdenService(OrdenRepository repository) {
        this.repository = repository;
    }

    public RespGenerica<Orden> createOrden(Orden newOrden){
        try {
            repository.insert(newOrden);
            return new RespGenerica<>(TIPO_OPER, ESTADO_RPTA_OK, "Orden creada", newOrden);
        } catch (Exception e) {
            return new RespGenerica<>(TIPO_OPER, ESTADO_RPTA_ERROR, "Error al crear orden - " + e.getMessage(), null);
        }
    }

    public RespGenerica<List<Orden>> listarOrdenes() {
        try {
            List<Orden> lista = repository.findAll();
            if(lista.isEmpty()) {
                return new RespGenerica<>(TIPO_OPER, ESTADO_RPTA_WARNING, "Sin ordenes", lista);
            } else {
                return new RespGenerica<>(TIPO_OPER, ESTADO_RPTA_OK, "Existen órdenes", lista);
            }
        } catch (Exception e) {
            return new RespGenerica<>(TIPO_OPER, ESTADO_RPTA_ERROR, "Error al listar ordenes - " + e.getMessage(), null);
        }
    }
}
