package com.tecsup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tecsup.model.Usuario;

@Repository
public interface IUsuario extends JpaRepository<Usuario, Integer> {
    Usuario findByNombre(String nombre);
}