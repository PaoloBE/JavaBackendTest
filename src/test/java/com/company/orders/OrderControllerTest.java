package com.company.orders;

import com.company.orders.config.KafkaSend;
import com.company.orders.controller.OrdenController;
import com.company.orders.entity.Item;
import com.company.orders.entity.Orden;
import com.company.orders.repository.OrdenRepository;
import com.company.orders.service.OrdenService;
import com.company.orders.utils.RespGenerica;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class OrderControllerTest {

    private OrdenController controller;

    @MockitoBean
    private OrdenService service;

    @MockitoBean
    private OrdenRepository repository;

    @MockitoBean
    private KafkaSend sender;

    @BeforeEach
    void setup() {
        service = new OrdenService(repository, sender);
        controller = new OrdenController(this.service);
        ReflectionTestUtils.setField(this.controller, "FIELD", "id");

    }

    @Test
    void returnAllOrders() throws Exception {
        when(this.repository.findAll((Pageable) any())).thenReturn(new PageImpl<>(respuestaFull()));
        RespGenerica<List<Orden>> listaResp = this.controller.listarOrdenes(0,3, Sort.Direction.DESC);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(listaResp.getBody());
        System.out.println("lista de tamaño: " + listaResp.getBody().size() + " contenido: " + jsonString);

        Assertions.assertEquals(listaResp.getMessage(), "Existen órdenes");
    }
    @Test
    void returnAllOrdersByUser() throws Exception {
        when(this.repository.findByIdUsuario(any(), (Pageable) any())).thenReturn(new PageImpl<>(respuestaFull()));
        RespGenerica<List<Orden>> listaResp = this.controller.listarPorUsuario("usuario",0,3, Sort.Direction.DESC);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(listaResp.getBody());
        System.out.println("lista de tamaño: " + listaResp.getBody().size() + " contenido: " + jsonString);

        Assertions.assertEquals(listaResp.getMessage(), "Existen órdenes");
    }

    private List<Orden> respuestaFull() {
        List<Orden> respuesta = new ArrayList<>();
        List<Item> items = new ArrayList<>();
        List<Item> items2 = new ArrayList<>();
        items.add(new Item("001",1,16.00));
        items.add(new Item("002",3,2.10));
        items.add(new Item("003",2,5.30));
        items2.add(new Item("001",1,8.00));
        items2.add(new Item("002",3,2.10));
        respuesta.add(new Orden(items, LocalDateTime.now().toString(), "usuario", 30.5));
        respuesta.add(new Orden(items2, LocalDateTime.now().toString(), "usuario", 10.5));
        return respuesta;
    }
}
