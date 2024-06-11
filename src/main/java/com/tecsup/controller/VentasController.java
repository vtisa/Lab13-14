package com.tecsup.controller;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tecsup.model.Cliente;
import com.tecsup.model.Producto;
import com.tecsup.model.Ventas;
import com.tecsup.repository.IClienteRepository;
import com.tecsup.repository.IProductoRepository;
import com.tecsup.repository.IVentasRepository;

@Controller
public class VentasController {
    
    @Autowired
    private IVentasRepository ventasRepo;
    
    @Autowired
    private IClienteRepository clienteRepo;

    @Autowired
    private IProductoRepository productoRepo;
    
   
    @GetMapping("/ventas")
    public String addVenta(Model model) {
    try {
    	 Ventas v = new Ventas();
         v.setCodigo(1);
         v.setFecha(new Date());
         Cliente cliente = clienteRepo.findById(1).orElseThrow();
         Producto producto = productoRepo.findById(1).orElseThrow();
         v.setCliente(cliente);
         v.setProducto(producto);
         v.setCantidad(2);
         v.setSubtotal(7.00);
         v.setIgv(1.26);
         v.setTotal(7.26);
         ventasRepo.save(v);

        model.addAttribute("message", "Venta registrada exitosamente!");
        model.addAttribute("messageType", "success");
    } catch (Exception e) {
        model.addAttribute("message", "Error al agregar el registro de venta.");
        model.addAttribute("messageType", "error");
    }
    return "respuesta";
    }
    
    
    @PostMapping("/ventas")
    public String deleteVenta(@RequestParam("codigo") int codigo, Model model) {
        try {
            ventasRepo.deleteById(codigo);
            model.addAttribute("message", "Venta eliminada exitosamente!");
            model.addAttribute("messageType", "success");
        } catch (Exception e) {
            model.addAttribute("message", "Error al eliminar la venta.");
            model.addAttribute("messageType", "error");
        }
        return "respuesta";
    }
}
