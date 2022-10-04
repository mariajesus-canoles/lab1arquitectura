package com.example.demo.services;

public abstract class LecturaFactory {
    public LecturaFactory(){

    }

    protected abstract Lectura crearLectura(String var);
}
