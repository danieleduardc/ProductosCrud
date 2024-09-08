package com.SpringProject.ProductosCrud.controller;
import com.SpringProject.ProductosCrud.dto.CategoriaSaveDTO;
import com.SpringProject.ProductosCrud.dto.CategoriaUpdateDTO;
import com.SpringProject.ProductosCrud.entity.Categoria;
import com.SpringProject.ProductosCrud.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<Categoria> saveCategoria(@RequestBody CategoriaSaveDTO categoriaSaveDTO){
        return new ResponseEntity<>( categoriaService.saveCategoria(categoriaSaveDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizarCategoria(@PathVariable int id, @RequestBody CategoriaUpdateDTO categoriaUpdateDTO) {
        if(categoriaService.findById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {
            return new ResponseEntity<>(categoriaService.updateCategoria(id, categoriaUpdateDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable int id) {
        if(categoriaService.findById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {
            categoriaService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/page")
    public ResponseEntity<Page<Categoria>> listarCategorias(@RequestParam int page, @RequestParam int size, @RequestParam(required = false) String nombre) {
        Page<Categoria> categorias = categoriaService.findAll(page, size, nombre);
        return ResponseEntity.ok(categorias);
    }
}
