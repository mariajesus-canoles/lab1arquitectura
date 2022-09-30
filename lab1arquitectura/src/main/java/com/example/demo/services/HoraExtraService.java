package com.example.demo.services;

import com.example.demo.entities.HoraExtraEntity;
import com.example.demo.repositories.HoraExtraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class HoraExtraService {
    @Autowired
    HoraExtraRepository horaExtraRepository;

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
}
