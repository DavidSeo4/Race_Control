package com.campusdual.racecontrol.model;

public class Coche implements Comparable<Coche>{

    private String marca;
    private String modelo;
    private int velocidad;
    private static final int maxVelocidad = 200;
    private String pegatinaCoche;
    private int puntuacion;
    private int velocidadTotalCarrera;

    public Coche(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
    }

    public int acelerar_frenar(){

        int aleatorio = (int)Math.floor(Math.random()*(3-1+1)+1);
        if (aleatorio>1){
            int aceleracion = (int)Math.floor(Math.random()*(Coche.maxVelocidad-1+1)+1);
            this.velocidad += aceleracion;
            // Limitar la velocidad máxima a 100
            if (this.velocidad > Coche.maxVelocidad) {
                this.velocidad = 200;
            }
            velocidadTotalCarrera+=this.velocidad;
            return this.velocidad;
        } else {
            int deceleracion = (int)Math.floor(Math.random()*(Coche.maxVelocidad-1+1)+1);
            this.velocidad -= deceleracion;
            // Limitar la velocidad minima a 0
            if (this.velocidad < 0) {
                this.velocidad = 0;
            }
            velocidadTotalCarrera-=this.velocidad;
            return this.velocidad;
        }
    }

    @Override
    public int compareTo(Coche o) {
        return 0;
    }


    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public String getPegatinaCoche() {
        return pegatinaCoche;
    }

    public void setPegatinaCoche(String pegatinaCoche) {
        this.pegatinaCoche = pegatinaCoche;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public int getVelocidadTotalCarrera() {
        return velocidadTotalCarrera;
    }

    public void setVelocidadTotalCarrera(int velocidadTotalCarrera) {
        this.velocidadTotalCarrera = velocidadTotalCarrera;
    }

    @Override
    public String toString() {
        return "Coche{" +
                "marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", velocidad=" + velocidad +
                ", pegatinaCoche='" + pegatinaCoche + '\'' +
                ", puntuacion=" + puntuacion +
                ", velocidadTotalCarrera=" + velocidadTotalCarrera +
                '}';
    }
}
