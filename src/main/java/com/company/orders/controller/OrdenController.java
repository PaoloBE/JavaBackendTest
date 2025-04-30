package com.company.orders.controller;

import com.company.orders.clases.NewOrderRequest;
import com.company.orders.entity.Item;
import com.company.orders.entity.Orden;
import com.company.orders.service.OrdenService;
import com.company.orders.utils.RespGenerica;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/orden")
public class OrdenController {
    private final OrdenService service;

    public OrdenController(OrdenService service) {
        this.service = service;
    }

    @PostMapping
    public RespGenerica<Orden> crearOrden(@RequestBody NewOrderRequest request) {
        Double totalOrden = 0.00;
        for(Item one : request.getItems()) {
            totalOrden = totalOrden + (one.getCantidad() * one.getPrecio_unitario());
        }
        Orden newOrden = new Orden(request.getItems(), LocalDateTime.now().toString(), request.getId_usuario(), totalOrden);
        return this.service.createOrden(newOrden);
    }

    @GetMapping("/all")
    public RespGenerica<List<Orden>> listarOrdenes() {
        return  this.service.listarOrdenes();
    }
}
