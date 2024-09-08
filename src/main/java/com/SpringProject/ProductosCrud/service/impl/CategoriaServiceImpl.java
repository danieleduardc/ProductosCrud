package com.SpringProject.ProductosCrud.service.impl;

import com.SpringProject.ProductosCrud.dto.CategoriaSaveDTO;
import com.SpringProject.ProductosCrud.dto.CategoriaUpdateDTO;
import com.SpringProject.ProductosCrud.entity.Categoria;
import com.SpringProject.ProductosCrud.repo.CategoriaRepo;
import com.SpringProject.ProductosCrud.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepo categoriaRepo;

    @Override
    public Categoria saveCategoria(CategoriaSaveDTO categoriaSaveDTO) {
        Categoria categoria = new Categoria(categoriaSaveDTO.getNombre());
        categoriaRepo.save(categoria);
        return categoria;
    }

    @Override
    public void delete(int id) {
        categoriaRepo.deleteById(id);
    }

    @Override
    public Categoria findById(int id) {
        return categoriaRepo.findById(id).orElse(null);
    }

    @Override
    public Categoria updateCategoria(int id, CategoriaUpdateDTO categoriaUpdateDTO) {
        Categoria categoria = findById(id);
        if (categoria != null) {
            categoria.setNombre(categoriaUpdateDTO.getNombre());
            categoriaRepo.save(categoria);
        }
        return categoria;
    }

    @Override
    public Page<Categoria> findAll(int page, int size, String nombre) {
        Pageable pageable = (size == 0) ? Pageable.unpaged() : PageRequest.of(page, size);
        return categoriaRepo.findAll(pageable, nombre);
    }
}
