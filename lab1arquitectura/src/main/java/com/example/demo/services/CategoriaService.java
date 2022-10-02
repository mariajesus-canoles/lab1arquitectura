package com.example.demo.services;

import com.example.demo.entities.AreaEntity;
import com.example.demo.entities.CategoriaEntity;
import com.example.demo.repositories.AreaRepository;
import com.example.demo.repositories.CategoriaRepository;
import com.example.demo.repositories.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    PagoRepository pagoRepository;


    public ArrayList<CategoriaEntity> obtenerCategorias(){
        return (ArrayList<CategoriaEntity>) categoriaRepository.findAll();
    }


    public CategoriaEntity guardarCategoria (CategoriaEntity categoria){
        return categoriaRepository.save(categoria);
    }


    public Optional<CategoriaEntity> obtenerCategoriaPorId(Long id){
        return categoriaRepository.findById(id);
    }


    public boolean eliminarCategoria(Long id) {
        try{
            categoriaRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }


}
