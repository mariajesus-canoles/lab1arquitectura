package com.example.demo.services;

import com.example.demo.entities.PersonalEntity;
import com.example.demo.repositories.PersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PersonalService {
    @Autowired
    PersonalRepository personalRepository;


    public ArrayList<PersonalEntity> obtenerPersonales(){
        return (ArrayList<PersonalEntity>) personalRepository.findAll();
    }


    public PersonalEntity guardarPersonal(PersonalEntity personal){
        return personalRepository.save(personal);
    }


    public Optional<PersonalEntity> obtenerPersonalPorId(Long id){
        return personalRepository.findById(id);
    }


    public boolean eliminarPersonal(Long id) {
        try{
            personalRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }
}
