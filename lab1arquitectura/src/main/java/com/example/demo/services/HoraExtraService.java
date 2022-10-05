package com.example.demo.services;

import com.example.demo.entities.HoraExtraEntity;
import com.example.demo.entities.PersonalEntity;
import com.example.demo.repositories.HoraExtraRepository;
import com.example.demo.repositories.PagoRepository;
import com.example.demo.repositories.PersonalRepository;
import com.example.demo.repositories.RelojRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class HoraExtraService {
    @Autowired
    HoraExtraRepository horaExtraRepository;

    @Autowired
    PagoRepository pagoRepository;

    @Autowired
    RelojRepository relojRepository;

    @Autowired
    PersonalRepository personalRepository;


    public ArrayList<HoraExtraEntity> obtenerHorasExtras(){
        return (ArrayList<HoraExtraEntity>) horaExtraRepository.findAll();
    }


    public HoraExtraEntity guardarHoraExtra (HoraExtraEntity hora_extra){
        return horaExtraRepository.save(hora_extra);
    }


    public Optional<HoraExtraEntity> obtenerHoraExtraPorId(Long id){
        return horaExtraRepository.findById(id);
    }




    public boolean eliminarHoraExtra(Long id) {
        try{
            horaExtraRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }



    public Integer calcularNumeroHorasExtras(Long idPersonal){
        Integer horaSalidaEstablecida = 18;
        Integer numHorasTotales= 0;
        ArrayList<HoraExtraEntity> listaHorasExtras = horaExtraRepository.buscarHorasExtrasPorIdPersonal(idPersonal);
        for (HoraExtraEntity horaExtra: listaHorasExtras){
            LocalDate fecha = horaExtra.getFecha();
            Integer numHorasPermitidas = horaExtra.getNum_horas();
            LocalTime horaSalida = relojRepository.buscarHoraSalida(idPersonal, fecha);
            if (horaSalida != null){
                Integer numHoraSalida = horaSalida.getHour();
                if (numHoraSalida>=(horaSalidaEstablecida+numHorasPermitidas)){
                    numHorasTotales = numHorasTotales + numHorasPermitidas;
                }
            }
        }
        return numHorasTotales;
    }

    public Integer calcularBonificacionHorasExtras(Long idPersonal){
        Integer valorHoraExtra = pagoRepository.buscarValorHoraExtra(idPersonal);
        Integer numHorasTotales = calcularNumeroHorasExtras(idPersonal);
        Integer bonificacionHorasExtras = valorHoraExtra * numHorasTotales;
        return bonificacionHorasExtras;
    }

    public void ingresarHoraExtraEnBD(String fechaAux, String numHorasAux, String rut){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate fecha = LocalDate.parse(fechaAux, df);
        Integer numHoras = Integer.parseInt(numHorasAux);
        Long idPersonal = personalRepository.buscarIdPersonalPorRut(rut);
        //ArrayList<HoraExtraEntity> horaExtra= horaExtraRepository.buscarHorasExtrasPorIdPersonal(idPersonal);
        horaExtraRepository.ingresarQuery(fecha, numHoras, idPersonal);
        System.out.println("hora extra ingresado correctamente");
    }


}
