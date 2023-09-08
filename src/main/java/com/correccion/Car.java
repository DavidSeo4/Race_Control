package com.correccion;

public class Car {

    private String marca;
    private String modelo;
    private static final int maxVelocidad = 100;
    private String pegatinaCoche;

    public Car(){
        this.marca = com.campusdual.racecontrol.controller.util.Input.string("Marca del coche");
        this.modelo = com.campusdual.racecontrol.controller.util.Input.string("Modelo del coche");
    }

    @Override
    public String toString() {
        return "Car{" +
                "marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", pegatinaCoche='" + pegatinaCoche + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Car coche = new Car();
    }


}
