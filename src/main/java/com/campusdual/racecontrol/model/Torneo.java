package com.campusdual.racecontrol.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

    public  void mostrarClasificacion(){
        // Comparador personalizado para comparar por la velocidad/distancia total de la carrera//
        Comparator<Coche> comparador = Comparator.comparing(Coche::getPuntuacion);
        clasificacion.sort(comparador);
        Collections.reverse(clasificacion);

        for (int i =0; i<this.getClasificacion().size(); i++){
            System.out.println("#"+ (i+1) +"º " + this.getClasificacion().get(i).getModelo()  +" con pegatina "+ this.getClasificacion().get(i).getPegatinaCoche() + "\n Puntos: " +
                    this.getClasificacion().get(i).getPuntuacion());
        }
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

    @Override
    public String toString() {
        return "Torneo{" +
                "nombreTorneo='" + nombreTorneo + '\'' +
                ", carrerasTorneo=" + carrerasTorneo +
                ", ganadorTorneo=" + ganadorTorneo +
                ", clasificacion=" + clasificacion +
                '}';
    }
}
