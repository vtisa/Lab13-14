package com.tecsup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tecsup.repository.IClienteRepository;
import com.tecsup.repository.IProductoRepository;
import com.tecsup.repository.IVentasRepository;

@Controller
public class TiendaController {

    @Autowired
    private IProductoRepository productoRepo;

    @Autowired
    private IClienteRepository clienteRepo;

    @Autowired
    private IVentasRepository ventaRepo;

    @GetMapping("/listar")
    public String listarDatos(Model model) {
        model.addAttribute("productos", productoRepo.findAll());
        model.addAttribute("clientes", clienteRepo.findAll());
        model.addAttribute("ventas", ventaRepo.findAll());
        
        return "tienda";
    }
}
