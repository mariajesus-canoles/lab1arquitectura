package com.example.demo.services;

import com.example.demo.entities.RelojEntity;
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
}
