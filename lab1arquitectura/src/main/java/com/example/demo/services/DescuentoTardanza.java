package com.example.demo.services;

import com.example.demo.entities.JustificativoEntity;
import com.example.demo.entities.RelojEntity;
import com.example.demo.repositories.JustificativoRepository;
import com.example.demo.repositories.RelojRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;

public class DescuentoTardanza implements DescuentoStrategy {

    @Override
    public Integer descuento(Long idPersonal, Integer sueldoBruto, ArrayList<String> listaFechasDelMes, ArrayList<RelojEntity> relojesPersonal, ArrayList<JustificativoEntity> justificativosPersonal){
        Double descuentoTardanza = 0.0;
        for (String fecha: listaFechasDelMes){
            //buscar reloj segun fecha
            Integer horaEntrada = 0;
            Integer minutoEntrada = 0;
            for (RelojEntity reloj: relojesPersonal){
                if (reloj.getFecha() == LocalDate.parse(fecha)){
                    horaEntrada = reloj.getHora_entrada().getHour();
                    minutoEntrada = reloj.getHora_entrada().getMinute();
                    break;
                }
            }
            if ((horaEntrada == 0 && minutoEntrada == 0) || horaEntrada > 9 || (horaEntrada == 9 && minutoEntrada > 10)){
                //buscar justificativo
                Boolean existeJustificativo = false;
                for (JustificativoEntity justificativo: justificativosPersonal){
                    if (justificativo.getFecha() == LocalDate.parse(fecha)){
                        existeJustificativo = true;
                    }
                }
                //inasistencia
                if (existeJustificativo == false){
                descuentoTardanza = descuentoTardanza + (sueldoBruto.doubleValue() * 0.15);
                }
            } else if (horaEntrada == 8 && minutoEntrada > 10 && minutoEntrada <= 25){
                descuentoTardanza = descuentoTardanza + (sueldoBruto.doubleValue() * 0.01);
            } else if (horaEntrada == 8 && minutoEntrada > 25 && minutoEntrada <= 45){
                descuentoTardanza = descuentoTardanza + (sueldoBruto.doubleValue() * 0.03);
            } else if (horaEntrada == 8 && minutoEntrada > 45 || horaEntrada == 9 && minutoEntrada >= 10){
                descuentoTardanza = descuentoTardanza + (sueldoBruto.doubleValue() * 0.06);
            }
        }
        return descuentoTardanza.intValue();
    }
}
