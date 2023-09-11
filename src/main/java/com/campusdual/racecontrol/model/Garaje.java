package com.campusdual.racecontrol.model;

import java.util.ArrayList;

public class Garaje {

    private ArrayList<Coche> cochesGaraje;
    private String pegatinaGaraje;

    public Garaje() {

    }
    public Garaje( String pegatinaGaraje) {
        cochesGaraje = new ArrayList<>();
        this.pegatinaGaraje = pegatinaGaraje;
    }

    public void añadirAlGaraje(Coche coche){
        this.cochesGaraje.add(coche);
        coche.setPegatinaCoche(this.pegatinaGaraje);
    }


    public ArrayList<Coche> getCochesGaraje() {
        return cochesGaraje;
    }

    public void setCochesGaraje(ArrayList<Coche> cochesGaraje) {
        this.cochesGaraje = cochesGaraje;
    }

    public String getPegatinaGaraje() {
        return pegatinaGaraje;
    }

    public void setPegatinaGaraje(String pegatinaGaraje) {
        this.pegatinaGaraje = pegatinaGaraje;
    }

    @Override
    public String toString() {
        return "Garaje{" +
                "cochesGaraje=" + cochesGaraje +
                ", pegatinaGaraje='" + pegatinaGaraje + '\'' +
                '}';
    }
}
