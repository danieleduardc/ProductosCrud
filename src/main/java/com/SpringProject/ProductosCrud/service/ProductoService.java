package com.SpringProject.ProductosCrud.service;

import com.SpringProject.ProductosCrud.dto.ProductoSaveDTO;
import com.SpringProject.ProductosCrud.dto.ProductoUpdateDTO;
import com.SpringProject.ProductosCrud.entity.Producto;
import org.springframework.data.domain.Page;


public interface ProductoService {
    Producto saveProducto(ProductoSaveDTO productoSaveDTO);

    public void delete(int id);

    public Producto findById(int id);

    Producto updateProducto(int id, ProductoUpdateDTO productoUpdateDTO);

    Page<Producto> findAll(int page, int size, String categoria, String nombre);

}
