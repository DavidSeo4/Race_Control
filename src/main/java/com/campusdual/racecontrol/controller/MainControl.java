package com.campusdual.racecontrol.controller;

import com.campusdual.racecontrol.model.*;

import java.util.ArrayList;

public class MainControl {

    static ArrayList<Torneo> listaTorneos = new ArrayList<>();
    static ArrayList<Garaje> listaGarajesDisponibles = new ArrayList<>();
    static int torneoActivo = 0;

    public static void main(String[] args) {

        rellenarDatos();
        lanzarAplicacion();


    }


    public static void lanzarAplicacion() {
        boolean salirAplicacion= false;
        boolean salirSubmenu1= false;

        do {
        System.out.println("Bienvenid@ a Race Control. Que deseas hacer? \n1 Gestionar torneos  \n2 Gestionar garajes \n3 Gestionar carreras \n4 Crear torneo \n5 Crear carrera \n6 Salir de la aplicacion");
        int menuPrincipal = com.campusdual.racecontrol.controller.util.Input.integer();
        switch (menuPrincipal) {

            case 1:
                System.out.println("Selecciona un torneo:");
                mostrarAlgo("Torneos");
                torneoActivo = com.campusdual.racecontrol.controller.util.Input.integer() - 1;
                do {

                System.out.println("=== " +listaTorneos.get(torneoActivo).getNombreTorneo() +" ===" +" \n1 Ver clasificacion \n2 Ver carreras \n3 Ver garajes participantes \n4 Salir al menu principal");
                int menuTorneos = com.campusdual.racecontrol.controller.util.Input.integer();
                switch (menuTorneos){
                    case 1:
                        if (listaTorneos.get(torneoActivo).getClasificacion().isEmpty()){
                            System.out.println("Todavía no se ha celebrado ninguna carrera del torneo.");
                        } else {
                            for (int i=0; i< listaTorneos.get(torneoActivo).getClasificacion().size(); i++){
                                System.out.println(i +"º el coche" +listaTorneos.get(torneoActivo).getClasificacion().get(i).getModelo()+ " de la marca "+listaTorneos.get(torneoActivo).getClasificacion().get(i).getMarca() + " . Garaje: "+ listaTorneos.get(torneoActivo).getClasificacion().get(i).getPegatinaCoche());
                            }
                        }
                        break;
                    case 2:
                        mostrarAlgo("Carreras");
                        int carreraSelecionada = (com.campusdual.racecontrol.controller.util.Input.integer() -1);
                        System.out.println(listaTorneos.get(torneoActivo).getCarrerasTorneo().get(carreraSelecionada).getName() + ". ¿Que deseas hacer? \n1. Ejecutar carrera \n2. Ver clasificacion ");
                        int menuCarrera = com.campusdual.racecontrol.controller.util.Input.integer();
                        switch (menuCarrera){
                            case 1:
                                listaTorneos.get(torneoActivo).getCarrerasTorneo().get(carreraSelecionada).celebrarCarrera();
                                break;
                            case 2:
                                if (listaTorneos.get(torneoActivo).getCarrerasTorneo().get(carreraSelecionada).getClasificacionCarrera().isEmpty()){
                                    System.out.println("Todavía no se ha celebrado esta carrera.");
                                } else {
                                    System.out.println("- La clasificación de la carrera fue:");
                                    listaTorneos.get(torneoActivo).getCarrerasTorneo().get(carreraSelecionada).getClasificacionCarrera().forEach(elemento -> System.out.println(elemento.getModelo() +" con pegatina "+ elemento.getPegatinaCoche())); //
                                    //AÑADIR PUNTUACIONES AL GLOBAL DEL TORNEO//
                                }
                                break;
                        }
                        break;
                    case 3:
                        break;
                    case 4:
                        salirSubmenu1=true;
                        break;
                }
                } while (salirSubmenu1==false);
                break;

            case 2:
                System.out.println("Selecciona un garaje:");
                break;
            case 3:
                break;
            case 6:
                //LOS DATOS DEBEN GUARDARSE//
                salirAplicacion=true;
                break;

        }
        }
        while (salirAplicacion==false);
    }



    public static void mostrarAlgo(String cosaAMostrar) {
        if (cosaAMostrar.equals("Torneos")) {
            int contador = 1;
            for (Torneo t : listaTorneos) {
                System.out.println(contador + "." + t.getNombreTorneo());
                contador++;
            }
        }
        if (cosaAMostrar.equals("Carreras")) {
            int contador = 1;
            for (Carrera carrera : listaTorneos.get(torneoActivo).getCarrerasTorneo()) {
                System.out.println(contador + "." + carrera.getName() );
                contador++;
            }
        }
    }

    public static void rellenarDatos() {

        Coche coche1 = new Coche("Citroen", "C2");
        Coche coche2 = new Coche("Seat", "Rucula");
        Coche coche3 = new Coche("Ferrari", "Poster");
        Coche coche4 = new Coche("BMW", "C5");
        Coche coche5 = new Coche("Alpine404", "Laptop");
        Coche coche6 = new Coche("Golf", "Gti");
        Coche coche7 = new Coche("Gallo", "Gallo456");
        Coche coche8 = new Coche("David", "M2023");
        Coche coche9 = new Coche("Nike", "CTR360");

        Garaje garaje1 = new Garaje("Garaje Ramon Hermida Comesaña");
        garaje1.añadirAlGaraje(coche1);
        garaje1.añadirAlGaraje(coche2);
        garaje1.añadirAlGaraje(coche7);
        Garaje garaje2 = new Garaje("Garaje Motorola 35");
        garaje2.añadirAlGaraje(coche3);
        garaje2.añadirAlGaraje(coche4);
        garaje2.añadirAlGaraje(coche8);
        Garaje garaje3 = new Garaje("Garaje Sevilla Union Automovilistica");
        garaje3.añadirAlGaraje(coche5);
        garaje3.añadirAlGaraje(coche6);
        garaje3.añadirAlGaraje(coche9);
        ArrayList<Garaje> arrayList = new ArrayList<>();
        arrayList.add(garaje1);
        arrayList.add(garaje2);
        arrayList.add(garaje3);
        listaGarajesDisponibles.addAll(arrayList);

        //POR AHORA TODAS LAS CARRERAS TIENE LOS MISMOS GARAJES PARTICIPANDO. MODIFICAR ESO//
        Carrera carrera1 = new CarreraEstandar("Gp de Palermo", arrayList, 3);
        Carrera carrera2 = new CarreraEstandar("Gp de Mariano Delgado", arrayList, 4);
        Carrera carrera3 = new CarreraEstandar("Gp de Asus", arrayList, 3);
        Carrera carrera4 = new CarreraEliminacion("Gp de los Arrays Elim", arrayList, 4);
        Carrera carrera5 = new CarreraEliminacion("Gp de Juan Cuesta Elim", arrayList, 2);
        Carrera carrera6 = new CarreraEliminacion("Gp de PgAdmin Elim", arrayList, 3);
        ArrayList<Carrera> carreras = new ArrayList<>();
        carreras.add(carrera1);
        carreras.add(carrera6);
        carreras.add(carrera3);
        ArrayList<Carrera> carreras2 = new ArrayList<>();
        carreras2.add(carrera4);
        carreras2.add(carrera2);
        carreras2.add(carrera5);

        Torneo torneo1 = new Torneo("World Championship", carreras);
        Torneo torneo2 = new Torneo("Campeonato Internacional de Algeciras", carreras2);
        listaTorneos.add(torneo1);
        listaTorneos.add(torneo2);
    }


}
