package com.example.demo.controllers;

import com.example.demo.entities.PlanillaEntity;
import com.example.demo.entities.PersonalEntity;
import com.example.demo.services.ObtenerPlanillaService;
import com.example.demo.services.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.services.PersonalService;

import java.util.ArrayList;

@Controller
@RequestMapping
public class PersonalController {
    @Autowired
    PersonalService personalService;

    @Autowired
    ObtenerPlanillaService obtenerPlanillaService;



    @GetMapping("/listar")
    public String listar(Model model) {
        //ArrayList<PlanillaEntity> personales=personalService.obtenerPlanillaPersonales();
        ArrayList<PlanillaEntity> personales = obtenerPlanillaService.calcularPlanillaPersonales();
        model.addAttribute("personales",personales);
    return "index";

    }
}

