package com.tecsup.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SeguridadController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/acceso-denegado")
    public String accessDeniedPage() {
        return "acceso-denegado";
    }
}