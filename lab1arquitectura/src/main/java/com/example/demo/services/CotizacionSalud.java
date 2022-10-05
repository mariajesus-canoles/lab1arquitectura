package com.example.demo.services;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CotizacionSalud {

    Integer calcularCotizacionSalud(LocalDate fecha_ingreso, Integer sueldoBruto){
        Integer ano_ingreso = fecha_ingreso.getYear();
        Double cotizacionPlanSalud = 0.0;
        if (ano_ingreso < 1980){
            cotizacionPlanSalud = sueldoBruto * 0.07;
        } else if (ano_ingreso >= 1980 && ano_ingreso < 2000) {
            cotizacionPlanSalud = sueldoBruto * 0.08;
        }
        else {
            cotizacionPlanSalud = sueldoBruto * 0.08;
        }
        return cotizacionPlanSalud.intValue();
    }
}
