package com.example.demo.services;

import com.example.demo.entities.AreaEntity;
import com.example.demo.repositories.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AreaService {
    @Autowired
    AreaRepository areaRepository;

    public ArrayList<AreaEntity> obtenerAreas(){
            return (ArrayList<AreaEntity>) areaRepository.findAll();
    }


    public AreaEntity guardarArea (AreaEntity area){
        return areaRepository.save(area);
    }


    public Optional<AreaEntity> obtenerAreaPorId(Long id){
        return areaRepository.findById(id);
    }


    public boolean eliminarArea(Long id) {
        try{
            areaRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }
}
