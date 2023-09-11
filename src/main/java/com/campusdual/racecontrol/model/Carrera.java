package com.campusdual.racecontrol.model;

import java.util.ArrayList;

public abstract class Carrera {

    private String name;
    private ArrayList<Garaje> registroGarajes;
    private ArrayList<Coche> clasificacionCarrera;
    private boolean carreraCelebrada;


    public Carrera(String name, ArrayList<Garaje> registroGarajes) {
        this.name = name;
        this.registroGarajes = registroGarajes;
        carreraCelebrada = false;
        clasificacionCarrera = new ArrayList<>();
    }

    public void celebrarCarrera(boolean isTorneo, Torneo torneo){

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Garaje> getRegistroGarajes() {
        return registroGarajes;
    }

    public void setRegistroGarajes(ArrayList<Garaje> registroGarajes) {
        this.registroGarajes = registroGarajes;
    }

    public ArrayList<Coche> getClasificacionCarrera() {
        return clasificacionCarrera;
    }

    public void setClasificacionCarrera(ArrayList<Coche> clasificacionCarrera) {
        this.clasificacionCarrera = clasificacionCarrera;
    }

    @Override
    public String toString() {
        return "Carrera{" +
                "name='" + name + '\'' +
                ", registroGarajes=" + registroGarajes +
                ", clasificacionCarrera=" + clasificacionCarrera +
                ", carreraCelebrada=" + carreraCelebrada +
                '}';
    }

    public boolean isCarreraCelebrada() {
        return carreraCelebrada;
    }

    public void setCarreraCelebrada(boolean carreraCelebrada) {
        this.carreraCelebrada = carreraCelebrada;
    }
}
