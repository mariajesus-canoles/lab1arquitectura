package com.example.demo.controllers;

import com.example.demo.entities.PersonalEntity;
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

    @GetMapping("/listar")
    public String listar(Model model) {
        ArrayList<PersonalEntity> personales=personalService.obtenerPersonales();
        model.addAttribute("personales",personales);
        return "index";
    }
}

