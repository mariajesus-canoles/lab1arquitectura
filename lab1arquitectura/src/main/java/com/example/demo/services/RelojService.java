package com.example.demo.services;

import com.example.demo.entities.RelojEntity;
import com.example.demo.repositories.PagoRepository;
import com.example.demo.repositories.RelojRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class RelojService {
    @Autowired
    RelojRepository relojRepository;


    public ArrayList<RelojEntity> obtenerRelojes(){

        return (ArrayList<RelojEntity>) relojRepository.findAll();
    }

    public RelojEntity guardarReloj(RelojEntity reloj){

        return relojRepository.save(reloj);
    }

    public Optional<RelojEntity> obtenerRelojPorId(Long id){

        return relojRepository.findById(id);
    }

    public boolean eliminarReloj(Long id) {
        try{
            relojRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }

    public ArrayList<Integer> calcularPorcentajePuntualidad(Long idPersonal){
        Integer horaEntrada = 8;
        Integer horaSalida = 18;
        Integer numDiasEntradaPuntual = 0;
        Integer numDiasSalidaPuntual = 0;
        Integer numDiasDelMes = relojRepository.buscarNumDiasDelMes();
        ArrayList<RelojEntity> listaRelojes= relojRepository.buscarRelojesDePersonal(idPersonal);
        for (RelojEntity reloj: listaRelojes){
            System.out.println(reloj.getHora_entrada().getHour());
            System.out.println(reloj.getHora_salida().getHour());
            if (reloj.getHora_entrada().getHour()<horaEntrada ||
                    reloj.getHora_entrada().getHour()==horaEntrada && reloj.getHora_entrada().getMinute()==0){
                numDiasEntradaPuntual++;
            }
            if (reloj.getHora_salida().getHour()>=horaSalida){
                numDiasSalidaPuntual++;
            }
        }
        Double porcentajeDiasEntradaPuntual = numDiasEntradaPuntual.doubleValue() * 100 / numDiasDelMes.doubleValue();
        Double porcentajeDiasSalidaPuntual = numDiasSalidaPuntual.doubleValue() * 100 / numDiasDelMes.doubleValue();
        ArrayList<Integer> porcentajesPuntualidad = new ArrayList<Integer>();
        porcentajesPuntualidad.add(porcentajeDiasEntradaPuntual.intValue());
        porcentajesPuntualidad.add(porcentajeDiasSalidaPuntual.intValue());
        return porcentajesPuntualidad;
    }

    public Integer calcularBonificacionPuntualidad(Long idPersonal, Integer sueldoFijoMensual){
        Double bonificacionPuntualidad = 0.0;
        ArrayList<Integer> porcentajesPuntualidad = this.calcularPorcentajePuntualidad(idPersonal);
        System.out.println("Nueva prueba");
        System.out.println(idPersonal);
        System.out.println(porcentajesPuntualidad.get(0));
        System.out.println(porcentajesPuntualidad.get(1));
        System.out.println("Fin prueba");
        if (porcentajesPuntualidad.get(0)>80.0 && porcentajesPuntualidad.get(1)>80.0 &&
                porcentajesPuntualidad.get(0)<=90.0 && porcentajesPuntualidad.get(1)<=90.0){
            bonificacionPuntualidad = sueldoFijoMensual * 0.05;
        }
        if (porcentajesPuntualidad.get(0)>90.0 && porcentajesPuntualidad.get(1)>90.0){
            bonificacionPuntualidad = sueldoFijoMensual * 0.08;
        }
        return bonificacionPuntualidad.intValue();
    }


}
