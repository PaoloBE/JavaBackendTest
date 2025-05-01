package com.company.orders.controller;

import com.company.orders.clases.NewOrderRequest;
import com.company.orders.entity.Item;
import com.company.orders.entity.Orden;
import com.company.orders.service.OrdenService;
import com.company.orders.utils.RespGenerica;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/orden")
public class OrdenController {
    private final OrdenService service;
    private final String FIELD = "id";

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
    public RespGenerica<List<Orden>> listarOrdenes(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "3") int size,
                                                   @RequestParam(defaultValue = "DESC") Sort.Direction sortDirection) {
        Pageable pageable = PageRequest.of(page, size, sortDirection, FIELD);
        return this.service.listarOrdenes(pageable);
    }

    @GetMapping("/usuario")
    public RespGenerica<List<Orden>> listarPorUsuario(@RequestParam @Validated String value,
                                                      @RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "3") int size,
                                                      @RequestParam(defaultValue = "DESC") Sort.Direction sortDirection) {
        Pageable pageable = PageRequest.of(page, size, sortDirection, FIELD);
        return this.service.listarPorUsuario(value, pageable);
    }
}
