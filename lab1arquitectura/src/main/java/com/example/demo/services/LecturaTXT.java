package com.example.demo.services;

import com.example.demo.entities.RelojEntity;

import java.util.ArrayList;

public class LecturaTXT extends Lectura{

    public LecturaTXT(){

    }

    @Override
    public ArrayList<ArrayList<String>> LecturaArchivo(String archivo){
        archivo = archivo.replaceAll(" ", "");
        ArrayList<ArrayList<String>> matrizArchivo = new ArrayList<ArrayList<String>>();

        String[] lineas = archivo.split("\n");
        for (String linea: lineas){
            //linea = linea.replaceAll(" ", "");
            ArrayList<String> lineaArhivo = new ArrayList<String>();
            String[] lineaFragmentada= linea.split(";");
            lineaArhivo.add(lineaFragmentada[0].strip());
            lineaArhivo.add(lineaFragmentada[1].strip());
            lineaArhivo.add(lineaFragmentada[2].strip());
            matrizArchivo.add(lineaArhivo);
        }
        return matrizArchivo;
    }

    public String description() {
        return "MySQL connection";
    }
}
