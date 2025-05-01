package com.company.orders.repository;

import com.company.orders.entity.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    @Query(value = "{id_usuario : ?0}")
    public Usuario findByIdUsuario();
}
