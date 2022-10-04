package com.example.demo.services;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class LecturaJSON extends Lectura{
    public LecturaJSON(){

    }

    @Override
    public ArrayList<ArrayList<String>> LecturaArchivo(String archivo){
        ArrayList<ArrayList<String>> matrizArchivo = new ArrayList<ArrayList<String>>();
        archivo = archivo.replaceAll("\\R", "");
        archivo = archivo.replaceAll(" ", "");
        JSONArray jsonArray = new JSONArray(archivo);
        for(int i = 0; i < jsonArray.length(); i = i + 1){
            ArrayList<String> lineaArhivo = new ArrayList<String>();
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            for(int j = 0; j < jsonObject.length(); j = j + 1){
                lineaArhivo.add(jsonObject.getString("fecha"));
                lineaArhivo.add(jsonObject.getString("hora"));
                lineaArhivo.add(jsonObject.getString("run"));
            }
            matrizArchivo.add(lineaArhivo);
        }
        return matrizArchivo;
    }

    public String description() {
        return "MySQL connection";
    }
}
