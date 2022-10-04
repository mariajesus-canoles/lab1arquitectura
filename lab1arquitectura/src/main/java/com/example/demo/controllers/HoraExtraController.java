package com.example.demo.controllers;


import com.example.demo.services.HoraExtraService;
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
public class HoraExtraController {
    @Autowired
    HoraExtraService horaExtraService;

    @GetMapping("/ingresarHoraExtra")
    public String autorizacion(Model model){
        return "ingresarHoraExtra";
    }

    @PostMapping("/ingresarHoraExtra")
    public String autorizacion(@RequestParam("Fecha") String fecha, @RequestParam("Rut") String rut, @RequestParam("Horas") String horas, RedirectAttributes ms){
        horaExtraService.ingresarHoraExtraEnBD(fecha,horas,rut);
        ms.addFlashAttribute("mensaje", "Archivo guardado correctamente!!");
        return "redirect:/ingresarHoraExtra";
    }
}
