package com.example.demo.services;

import com.example.demo.entities.JustificativoEntity;
import com.example.demo.entities.RelojEntity;
import com.example.demo.repositories.JustificativoRepository;
import com.example.demo.repositories.RelojRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;


@Service
public class JustificativoService {
    @Autowired
    JustificativoRepository justificativoRepository;

    @Autowired
    RelojRepository relojRepository;

    public ArrayList<JustificativoEntity> obtenerJustificativos(){
        return (ArrayList<JustificativoEntity>) justificativoRepository.findAll();
    }


    public JustificativoEntity guardarJustificado(JustificativoEntity justificativo){
        return justificativoRepository.save(justificativo);
    }


    public Optional<JustificativoEntity> obtenerJustificativoPorId(Long id){
        return justificativoRepository.findById(id);
    }


    public boolean eliminarJustificativo(Long id) {
        try{
            justificativoRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }

    Integer calcularNumInasistencia(Long idPersonal){
        Integer numInasistencia = 0;
        ArrayList<LocalDate> listaFechasDelMes = relojRepository.buscarFechasDelMes();
        for (LocalDate fecha: listaFechasDelMes){
            RelojEntity reloj = relojRepository.buscarRelojDePersonal(idPersonal, fecha);
            if (reloj == null){
                numInasistencia++;
            } else if (reloj.getHora_entrada().getHour() > 9
                    || (reloj.getHora_entrada().getHour() == 9 && reloj.getHora_entrada().getMinute() > 10)) {
                numInasistencia++;
            } else if (reloj.getHora_salida().getHour() < 17
                    || (reloj.getHora_salida().getHour() == 17 && reloj.getHora_salida().getMinute() < 15)) {
                numInasistencia++;
            }
        }
        return numInasistencia;
    }

    Integer calcularDescuentoPorInasistencia(Long idPersonal, Integer sueldoFijo){
        Integer numInasistencia = this.calcularNumInasistencia(idPersonal);
        Double descuentoInasistencia = sueldoFijo * 0.15;
        return descuentoInasistencia.intValue();
    }
}
