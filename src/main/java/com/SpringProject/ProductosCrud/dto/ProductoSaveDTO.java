package com.SpringProject.ProductosCrud.dto;


import com.SpringProject.ProductosCrud.entity.Categoria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductoSaveDTO {

    private String nombre;
    private String descripcion;
    private double precio;
    Categoria categoria;

}
