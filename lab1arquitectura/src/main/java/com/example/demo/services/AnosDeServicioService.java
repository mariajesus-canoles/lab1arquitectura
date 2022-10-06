package com.example.demo.services;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
@Service
public class AnosDeServicioService {
    public Integer calcularAnosServicios(LocalDate fechaIngreso){
        LocalDate fechaActual = LocalDate.now();
        Integer diferenciaAnos = fechaActual.getYear() - fechaIngreso.getYear();
        return diferenciaAnos;
    }

    public Integer calcularBonificacionTiempoServicio(Integer anosServicioEmpresa, Integer sueldoFijo){
        Double bonificacionTiempoServicio;
        if (anosServicioEmpresa<5){
            return 0;
        }
        else if (anosServicioEmpresa>=5 && anosServicioEmpresa<10) {
            bonificacionTiempoServicio = sueldoFijo*0.05;
        }
        else if (anosServicioEmpresa>=10 && anosServicioEmpresa<15) {
            bonificacionTiempoServicio = sueldoFijo*0.08;
        }
        else if(anosServicioEmpresa>=15 && anosServicioEmpresa<20) {
            bonificacionTiempoServicio = sueldoFijo*0.11;
        }
        else if(anosServicioEmpresa>=20){
            bonificacionTiempoServicio = sueldoFijo*0.14;
        }
        else {
            bonificacionTiempoServicio = sueldoFijo*0.17;
        }
        Integer bonificacionTiempoServicioInteger = bonificacionTiempoServicio.intValue();
        return bonificacionTiempoServicioInteger;
    }
}
