package com.tecsup.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tecsup.model.Producto;
import com.tecsup.repository.IProductoRepository;

@Controller
public class ProductoController {
    
    @Autowired
    private IProductoRepository productoRepo; 
    
   
    @GetMapping("/productos")
    public String addProducto(Model model) {
    	try {
            Producto p = new Producto();
            p.setCodigo(1);
            p.setNombre("Gaseosa");
            p.setStock(12);
            p.setPrecio(3.50);
            productoRepo.save(p);

            model.addAttribute("message", "Producto agregado exitosamente!");
            model.addAttribute("messageType", "success");
        } catch (Exception e) {
            model.addAttribute("message", "Error al agregar el producto.");
            model.addAttribute("messageType", "error");
        }
        return "respuesta";
    }
    
  
    @PostMapping("/productos")
    public String deleteProducto(@RequestParam("codigo") int codigo, Model model) {
        try {
            productoRepo.deleteById(codigo);
            model.addAttribute("message", "Producto eliminado exitosamente!");
            model.addAttribute("messageType", "success");
        } catch (Exception e) {
            model.addAttribute("message", "Error al eliminar el producto.");
            model.addAttribute("messageType", "error");
        }
        return "respuesta";
    }
    
}

