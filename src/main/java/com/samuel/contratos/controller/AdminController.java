package com.samuel.contratos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    //‘Login’ do administrador
    @GetMapping("/loginAdm")
    public String loginAdm(){
        return "loginAdm";
    }
}
