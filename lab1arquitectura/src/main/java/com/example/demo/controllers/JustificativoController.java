package com.example.demo.controllers;

import com.example.demo.services.JustificativoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping
public class JustificativoController {

    @Autowired
    JustificativoService justificativoService;

    @GetMapping("/ingresarJustificativo")
    public String justificativo(Model model){
        return "justificativo";
    }

    @PostMapping("/ingresarJustificativo")
    public String justificativo(@RequestParam("Fecha") String fecha, @RequestParam("Rut") String rut, RedirectAttributes ms){
        justificativoService.ingresarJustificativoEnBD(fecha, rut);
        ms.addFlashAttribute("mensaje", "Archivo guardado correctamente!!");
        return "redirect:/ingresarJustificativo";
    }
}
