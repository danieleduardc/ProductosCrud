package com.SpringProject.ProductosCrud.controller;


import com.SpringProject.ProductosCrud.dto.ProductoSaveDTO;
import com.SpringProject.ProductosCrud.dto.ProductoUpdateDTO;
import com.SpringProject.ProductosCrud.entity.Producto;
import com.SpringProject.ProductosCrud.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("api/v1/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @PostMapping
    public ResponseEntity<Producto> saveProducto(@RequestBody ProductoSaveDTO productoSaveDTO){
        return new ResponseEntity<>( productoService.saveProducto(productoSaveDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable int id, @RequestBody ProductoUpdateDTO productoUpdateDTO) {
        if(productoService.findById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {
            return new ResponseEntity<>(productoService.updateProducto(id, productoUpdateDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable int id) {
        if(productoService.findById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {
            productoService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscarProducto(@PathVariable int id) {
        Producto producto = productoService.findById(id);
        if(producto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    @GetMapping(path = "/page")
    public ResponseEntity<Page<Producto>> listarProductos(@RequestParam int page, @RequestParam int size, @RequestParam(required = false) String categoria, @RequestParam(required = false) String nombre) {
        Page<Producto> productos = productoService.findAll(page, size, categoria, nombre);
        return ResponseEntity.ok(productos);
    }



}
