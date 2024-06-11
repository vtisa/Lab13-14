package com.tecsup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tecsup.model.Ventas;

@Repository
public interface IVentasRepository extends JpaRepository<Ventas, Integer> {

}
