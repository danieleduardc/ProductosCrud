package com.SpringProject.ProductosCrud.service;

import com.SpringProject.ProductosCrud.dto.CategoriaSaveDTO;
import com.SpringProject.ProductosCrud.dto.CategoriaUpdateDTO;
import com.SpringProject.ProductosCrud.entity.Categoria;
import org.springframework.data.domain.Page;

public interface CategoriaService {

    Categoria saveCategoria(CategoriaSaveDTO categoriaSaveDTO);

    void delete(int id);

    Categoria findById(int id);

    Categoria updateCategoria(int id, CategoriaUpdateDTO categoriaUpdateDTO);

    Page<Categoria> findAll(int page, int size, String nombre);
}
