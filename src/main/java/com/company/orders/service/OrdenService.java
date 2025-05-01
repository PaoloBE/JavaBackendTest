package com.company.orders.service;

import com.company.orders.config.KafkaSend;
import com.company.orders.entity.Orden;
import com.company.orders.repository.OrdenRepository;
import com.company.orders.utils.RespGenerica;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.company.orders.utils.Constantes.*;

@Service
@Transactional
public class OrdenService {
    private final OrdenRepository repository;
    private KafkaSend sendHelper;

    public OrdenService(OrdenRepository repository, KafkaSend sendHelper) {
        this.repository = repository;
        this.sendHelper = sendHelper;
    }

    public RespGenerica<Orden> createOrden(Orden newOrden){
        try {
            repository.insert(newOrden);
            sendHelper.sendMessage("ORDEN CREADA" + newOrden.getId());
            return new RespGenerica<>(TIPO_OPER, ESTADO_RPTA_OK, "Orden creada", newOrden);
        } catch (Exception e) {
            return new RespGenerica<>(TIPO_OPER, ESTADO_RPTA_ERROR, "Error al crear orden - " + e.getMessage(), null);
        }
    }

    public RespGenerica<List<Orden>> listarOrdenes(Pageable pageable) {
        try {
            List<Orden> lista = repository.findAll(pageable).getContent();
            if(lista.isEmpty()) {
                return new RespGenerica<>(TIPO_OPER, ESTADO_RPTA_WARNING, "Sin ordenes", lista);
            } else {
                return new RespGenerica<>(TIPO_OPER, ESTADO_RPTA_OK, "Existen órdenes", lista);
            }
        } catch (Exception e) {
            return new RespGenerica<>(TIPO_OPER, ESTADO_RPTA_ERROR, "Error al listar ordenes - " + e.getMessage(), null);
        }
    }

    public RespGenerica<List<Orden>> listarPorUsuario(String idUsuario, Pageable pageable) {
        try {
            List<Orden> lista = repository.findByIdUsuario(idUsuario, pageable).getContent();
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
