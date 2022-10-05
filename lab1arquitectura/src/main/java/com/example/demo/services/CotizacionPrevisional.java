package com.example.demo.services;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class CotizacionPrevisional {

    Integer calcularCotizacionPrevisional(LocalDate fecha_ingreso, Integer sueldoBruto){
        Integer ano_ingreso = fecha_ingreso.getYear();
        Double cotizacionPrevisional = 0.0;

        if (ano_ingreso < 1980){
            cotizacionPrevisional = sueldoBruto * 0.07;
        } else if (ano_ingreso >= 1980 && ano_ingreso < 2000) {
            cotizacionPrevisional = sueldoBruto * 0.09;
        }
        else {
            cotizacionPrevisional = sueldoBruto * 0.1;
        }
        return cotizacionPrevisional.intValue();
    }
}
