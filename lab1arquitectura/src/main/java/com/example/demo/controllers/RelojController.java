package com.example.demo.controllers;

import com.example.demo.services.RelojService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class RelojController {

    @Autowired
    RelojService relojService;

    @GetMapping("/subirArchivoMarcaHoraria")
    public ModelAndView subirArchivoMarcaHoraria(Model modelo){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("marcaHoraria");
        return modelAndView;
    }

    @PostMapping("/cargar")
    public ModelAndView cargarMarcaHoraria(@RequestParam("archivos")MultipartFile archivo, ModelMap ms){
        relojService.cargarMarcaHoraria(archivo);
        ms.addAttribute("mensaje", "La carga del archivo ingresado resultó exitosa!");
        return new ModelAndView("redirect:/subirArchivoMarcaHoraria", ms);
    }
}
