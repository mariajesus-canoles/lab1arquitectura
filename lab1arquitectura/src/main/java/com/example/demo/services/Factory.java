package com.example.demo.services;

public class Factory extends LecturaFactory{
    public Factory(){
    }

    public Lectura crearLectura(String type){
        if (type.equals("text/plain")) {
            return new LecturaTXT();
        } else if (type.equals("application/json")){ //json
            return new LecturaJSON();
        }
        else{
            return new Lectura();
        }
    }
}



