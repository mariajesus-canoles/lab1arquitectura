package com.example.demo.services;

import com.example.demo.entities.JustificativoEntity;
import com.example.demo.entities.RelojEntity;
import com.example.demo.repositories.JustificativoRepository;
import com.example.demo.repositories.RelojRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;

public class DescuentoRetiro implements DescuentoStrategy{


    @Override
    public Integer descuento(Long idPersonal, Integer sueldoBruto, ArrayList<String> listaFechasDelMes, ArrayList<RelojEntity> relojesPersonal, ArrayList<JustificativoEntity> justificativosPersonal){
        Double descuentoRetiro = 0.0;
        for (String fecha: listaFechasDelMes){
            Integer horaEntrada = 0;
            Integer minutoEntrada = 0;
            Integer horaSalida = 0;
            Integer minutoSalida = 0;
            for (RelojEntity reloj: relojesPersonal){
                if (reloj.getFecha() == LocalDate.parse(fecha)){
                    horaEntrada = reloj.getHora_entrada().getHour();
                    minutoEntrada = reloj.getHora_entrada().getMinute();
                    horaSalida = reloj.getHora_salida().getHour();
                    minutoSalida = reloj.getHora_salida().getMinute();
                    break;
                }
            }
            if (horaSalida == 0 && minutoSalida == 0){
                descuentoRetiro = descuentoRetiro + 0; //ya se hizo el descuento
            } else if (((horaSalida < 17 || horaSalida == 17) && minutoSalida < 15)  &&
                    !(horaEntrada > 9 || (horaEntrada == 9 && minutoEntrada > 10))){
                Boolean existeJustificativo = false;
                for (JustificativoEntity justificativo: justificativosPersonal){
                    if (justificativo.getFecha() == LocalDate.parse(fecha)){
                        existeJustificativo = true;
                    }
                }
                //inasistencia
                if (existeJustificativo == false){
                    descuentoRetiro = descuentoRetiro + (sueldoBruto.doubleValue() * 0.15);
                }
            }
            else if (horaSalida == 17 && minutoSalida >= 45 && minutoSalida <= 59){
                descuentoRetiro = descuentoRetiro + (sueldoBruto.doubleValue() * 0.02);
            } else if (horaSalida == 17 && minutoSalida >= 30 && minutoSalida <= 44){
                descuentoRetiro = descuentoRetiro + (sueldoBruto.doubleValue() * 0.04);
            } else if (horaSalida == 17 && minutoSalida >= 15 && minutoSalida <= 29){
                descuentoRetiro = descuentoRetiro + (sueldoBruto.doubleValue() * 0.07);
            }
        }
        return descuentoRetiro.intValue();
    }
}
