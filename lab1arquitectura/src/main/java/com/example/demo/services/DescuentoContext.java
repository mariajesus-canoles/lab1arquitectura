package com.example.demo.services;

import com.example.demo.entities.JustificativoEntity;
import com.example.demo.entities.RelojEntity;

import java.util.ArrayList;

public class DescuentoContext {
    private DescuentoStrategy strategy;

    //se setea el metodo
    public void setDescuentoMethod(DescuentoStrategy strategy){
        this.strategy = strategy;
    }

    public DescuentoStrategy getStrategy(){
        return this.strategy;
    }

    public Integer aplicarDescuento(Long idPersonal, Integer sueldoFijo, ArrayList<String> listaFechasDelMes, ArrayList<RelojEntity> relojesPersonal, ArrayList<JustificativoEntity> justificativosPersonal){
        return this.strategy.descuento(idPersonal, sueldoFijo, listaFechasDelMes, relojesPersonal, justificativosPersonal);
    }
}
