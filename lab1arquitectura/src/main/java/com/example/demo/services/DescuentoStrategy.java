package com.example.demo.services;

import com.example.demo.entities.JustificativoEntity;
import com.example.demo.entities.RelojEntity;

import java.util.ArrayList;

public interface DescuentoStrategy {
    public Integer descuento(Long idPersonal, Integer sueldoBruto, ArrayList<String> listaFechasDelMes, ArrayList<RelojEntity> relojesPersonal, ArrayList<JustificativoEntity> justificativosPersonal);
}
