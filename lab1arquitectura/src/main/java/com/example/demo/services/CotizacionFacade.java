package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class CotizacionFacade {

    @Autowired
    CotizacionPrevisional cotizacionPrevisional;

    @Autowired
    CotizacionSalud cotizacionSalud;

    public ArrayList<Integer> calcularCotizaciones(LocalDate fecha_ingreso, Integer sueldoBruto){
        ArrayList<Integer> cotizaciones = new ArrayList<Integer>();

        Integer cotizacionPrevisionalAux = cotizacionPrevisional.calcularCotizacionPrevisional(fecha_ingreso, sueldoBruto);
        Integer cotizacionSaludAux = cotizacionSalud.calcularCotizacionSalud(fecha_ingreso, sueldoBruto);

        cotizaciones.add(cotizacionPrevisionalAux);
        cotizaciones.add(cotizacionSaludAux);
        return cotizaciones;
    }
}
