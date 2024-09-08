package com.SpringProject.ProductosCrud.repo;

import com.SpringProject.ProductosCrud.entity.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoriaRepo extends JpaRepository<Categoria, Integer> {

    @Query("SELECT c FROM Categoria c " + "WHERE (:nombre IS NULL OR c.nombre LIKE %:nombre%)")
    Page<Categoria> findAll(Pageable pageable, String nombre);
}
