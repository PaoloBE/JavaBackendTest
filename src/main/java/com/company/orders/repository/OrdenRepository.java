package com.company.orders.repository;

import com.company.orders.entity.Orden;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrdenRepository extends MongoRepository<Orden, String> {
    public Optional<Orden> findById(String idOrden);
    @Query(value = "{id_usuario : ?0}")
    public Page<Orden> findByIdUsuario(String idUsuario, Pageable pageable);
}
