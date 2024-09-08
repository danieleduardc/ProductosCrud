package com.SpringProject.ProductosCrud.service.impl;

import com.SpringProject.ProductosCrud.dto.ProductoSaveDTO;
import com.SpringProject.ProductosCrud.dto.ProductoUpdateDTO;
import com.SpringProject.ProductosCrud.entity.Producto;
import com.SpringProject.ProductosCrud.repo.ProductoRepo;
import com.SpringProject.ProductosCrud.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepo productoRepo;

    @Override
    public Producto saveProducto(ProductoSaveDTO productoSaveDTO) {
        Producto producto = new Producto(productoSaveDTO.getNombre(), productoSaveDTO.getDescripcion(), productoSaveDTO.getPrecio(), productoSaveDTO.getCategoria()
        );
        productoRepo.save(producto);
        return producto;
    }

    @Override
    public void delete(int id) {
        productoRepo.deleteById(id);
    }

    @Override
    public Producto findById(int id) {
        return productoRepo.findById(id).orElse(null);
    }

    @Override
    public Producto updateProducto(int id, ProductoUpdateDTO productoUpdateDTO) {
        Producto producto = findById(id);
        if (producto != null) {
            producto.setNombre(productoUpdateDTO.getNombre());
            producto.setDescripcion(productoUpdateDTO.getDescripcion());
            producto.setPrecio(productoUpdateDTO.getPrecio());
            producto.setCategoria(productoUpdateDTO.getCategoria());
            productoRepo.save(producto);
        }
        return producto;
    }

    @Override
    public Page<Producto> findAll(int page, int size, String categoria,String nombre) {
        Pageable pageable = (size == 0) ? Pageable.unpaged() : PageRequest.of(page, size);
        return productoRepo.findAll(pageable, categoria, nombre);
    }

}


