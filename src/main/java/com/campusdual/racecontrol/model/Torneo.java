package com.campusdual.racecontrol.model;

import java.util.ArrayList;

public class Torneo {

    private String nombreTorneo;
    private ArrayList<Carrera> carrerasTorneo;
    private Coche ganadorTorneo;
    private ArrayList<Coche> clasificacion;


    public Torneo(String nombreTorneo, ArrayList<Carrera> carrerasTorneo) {
        clasificacion = new ArrayList<>();
        this.nombreTorneo = nombreTorneo;
        this.carrerasTorneo = carrerasTorneo;
    }


    public String getNombreTorneo() {
        return nombreTorneo;
    }

    public void setNombreTorneo(String nombreTorneo) {
        this.nombreTorneo = nombreTorneo;
    }

    public ArrayList<Carrera> getCarrerasTorneo() {
        return carrerasTorneo;
    }

    public void setCarrerasTorneo(ArrayList<Carrera> carrerasTorneo) {
        this.carrerasTorneo = carrerasTorneo;
    }

    public Coche getGanadorTorneo() {
        return ganadorTorneo;
    }

    public void setGanadorTorneo(Coche ganadorTorneo) {
        this.ganadorTorneo = ganadorTorneo;
    }

    public ArrayList<Coche> getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(ArrayList<Coche> clasificacion) {
        this.clasificacion = clasificacion;
    }
}
