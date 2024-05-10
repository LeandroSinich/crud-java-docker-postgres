package com.sergiecode.apirest.apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sergiecode.apirest.apirest.Entities.Producto;
import com.sergiecode.apirest.apirest.Repositories.ProductoRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    

    @GetMapping
    public List<Producto> getAll() {
        return productoRepository.findAll();
    }

    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto) {
        
        return productoRepository.save(producto);
    }
    
    @GetMapping("/{id}")
    public Producto getProducto(@PathVariable Long id) {

        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el producto ID: " + id));
    }

    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable Long id, @RequestBody Producto producto) {
        
        Producto buscado = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el producto ID: " + id));
        if(producto.getNombre() != null){
            
            buscado.setNombre(producto.getNombre());
        }  
        if(producto.getPrecio() != 0.0) {

            buscado.setPrecio(producto.getPrecio());
        }     

        return productoRepository.save(buscado);            
    }

    @DeleteMapping("/{id}")
    public String deleteProducto(@PathVariable Long id) {

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el producto ID: " + id));
        productoRepository.delete(producto);
        return "El producto con el id "+ id +" ha sido eliminado correctamente";
    }
    
    


}
