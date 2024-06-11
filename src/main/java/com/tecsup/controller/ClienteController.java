package com.tecsup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tecsup.model.Cliente;
import com.tecsup.repository.IClienteRepository;

@Controller
public class ClienteController {
    
    @Autowired
    private IClienteRepository clienteRepo; 
    
 
    @GetMapping("/clientes")
    public String addCliente(Model model) {
    	try {
            Cliente c = new Cliente();
            c.setCodigo(1);
            c.setNombres("Zamir");
            c.setApellidos("Arbulo");
            c.setDni("87456332");
            c.setTelefono("934567434");
            c.setDireccion("Av. sur 456");
            clienteRepo.save(c);

            model.addAttribute("message", "Cliente agregado exitosamente!");
            model.addAttribute("messageType", "success");
        } catch (Exception e) {
            model.addAttribute("message", "Error al agregar el cliente.");
            model.addAttribute("messageType", "error");
        }
        return "respuesta";
    }
    
    

    @PostMapping("/clientes")
    public String deleteCliente(@RequestParam("codigo") int codigo, Model model) {
    	try {
	        clienteRepo.deleteById(codigo);
	        model.addAttribute("message", "Cliente eliminado exitosamente!");
	        model.addAttribute("messageType", "success");
    	} catch (Exception e) {
            model.addAttribute("message", "Error al agregar el cliente.");
            model.addAttribute("messageType", "error");
        }
        return "respuesta";
    }
}

