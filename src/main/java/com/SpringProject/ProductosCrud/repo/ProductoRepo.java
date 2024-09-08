package com.SpringProject.ProductosCrud.repo;

import com.SpringProject.ProductosCrud.entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductoRepo extends JpaRepository<Producto, Integer> {

    @Query("SELECT p FROM Producto p " +
            "WHERE (:categoria IS NULL OR p.categoria.nombre LIKE %:categoria%) " +
            "AND (:nombre IS NULL OR p.nombre LIKE %:nombre%)")
    Page<Producto> findAll(
            Pageable pageable,
            @Param("categoria") String categoria,
            @Param("nombre") String nombre);

}
