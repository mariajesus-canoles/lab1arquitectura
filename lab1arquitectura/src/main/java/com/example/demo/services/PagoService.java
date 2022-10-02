package com.example.demo.services;

import com.example.demo.entities.PagoEntity;
import com.example.demo.repositories.HoraExtraRepository;
import com.example.demo.repositories.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PagoService {
    @Autowired
    PagoRepository pagoRepository;

    @Autowired
    HoraExtraRepository horaExtraRepository;

    public ArrayList<PagoEntity> obtenerPagos(){
        return (ArrayList<PagoEntity>) pagoRepository.findAll();
    }


    public PagoEntity guardarPago (PagoEntity pago){
        return pagoRepository.save(pago);
    }


    public Optional<PagoEntity> obtenerPagoPorId(Long id){
        return pagoRepository.findById(id);
    }


    public boolean eliminarPago(Long id) {
        try{
            pagoRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }


}
