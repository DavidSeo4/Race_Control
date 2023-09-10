package com.campusdual.racecontrol.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CarreraEliminacion extends Carrera {

    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RESET = "\u001B[0m";
    private int vueltasCalentamiento;

    public CarreraEliminacion(String name, ArrayList<Garaje> registroGarajes, int vueltasCalentamiento) {
        super(name, registroGarajes);
        this.vueltasCalentamiento = vueltasCalentamiento;
    }

    @Override
    public void celebrarCarrera(boolean isTorneo) {

        // Comparador personalizado para comparar por la velocidad/distancia total de la carrera//
        Comparator<Coche> comparador = Comparator.comparing(Coche::getVelocidadTotalCarrera);

        //COMPROBACION DE SI LA CARRERA YA SE HA CELEBRADO O NO//

        if (this.isCarreraCelebrada()) {
            System.out.println(ANSI_RED +"El " + this.getName() + " ya se ha celebrado. La clasificación final fue: "+ ANSI_RESET);
            for (int i =0; i<this.getClasificacionCarrera().size(); i++){
                 System.out.println("#"+ (i+1) +"º " + this.getClasificacionCarrera().get(i).getModelo()  +" con pegatina "+ this.getClasificacionCarrera().get(i).getPegatinaCoche());
            }
        } else {

            //COMPROBACION DE SI EL NUMERO DE GARAJES DISPONIBLES DE LA CARRERA ES MAYOR QUE 1//

            if (this.getRegistroGarajes().size() < 2) {
                int aleatorio2 = (int) Math.floor(Math.random() * ((this.getRegistroGarajes().size() - 1) - 0 + 1) + 0);

                //OPCION 1 SOLO GARAJE DISPONIBLE//
                System.out.println(ANSI_RED +"La carrera se celebrará con el unico garaje disponible: " + this.getRegistroGarajes().get(aleatorio2).getPegatinaGaraje() + "\n Los coches participantes son:"+ ANSI_RESET);
                ArrayList<Coche> cochesParticipantes = this.getRegistroGarajes().get(aleatorio2).getCochesGaraje();
                ArrayList<Coche> listaAuxiliar = new ArrayList<>();
                cochesParticipantes.forEach(elemento -> System.out.println(elemento.getModelo() +" con pegatina "+ elemento.getPegatinaCoche()));
                //Reseteo la velocidad de los coches a 0 para que no se acumule en las carreras/torneos
                cochesParticipantes.forEach(e-> e.setVelocidad(0));

                System.out.println(ANSI_YELLOW +"=== Celebrando carrera... ==="+ ANSI_RESET);
                for (int vuelta = 0; vuelta <= (this.getRegistroGarajes().get(aleatorio2).getCochesGaraje().size()+this.vueltasCalentamiento); vuelta++) {

                    for (int i = 0; i<this.getRegistroGarajes().get(aleatorio2).getCochesGaraje().size(); i++) {
                        cochesParticipantes.get(i).acelerar_frenar();
                    }
                    if (cochesParticipantes.size()>=1){
                        cochesParticipantes.sort(comparador);
                        Collections.reverse(cochesParticipantes);
                        if (cochesParticipantes.size()>1){
                            System.out.println(ANSI_RED +"Eliminado: " + cochesParticipantes.get(cochesParticipantes.size()-1).getModelo() + " en la vuelta " + (vuelta +1)+ ANSI_RESET);
                            listaAuxiliar.add(cochesParticipantes.get(cochesParticipantes.size()-1));
                            cochesParticipantes.remove(cochesParticipantes.size()-1);
                        } else {
                            System.out.println(ANSI_GREEN +"- Solo queda un coche en pista: " + cochesParticipantes.get(cochesParticipantes.size()-1).getModelo() + " en la vuelta " + (vuelta +1)+ ANSI_RESET);
                            listaAuxiliar.add(cochesParticipantes.get(cochesParticipantes.size()-1));
                            cochesParticipantes.remove(cochesParticipantes.size()-1);
                        }

                    }
                }

                // Ordenar cochesParticipantes utilizando el comparador al inicio del método//
                listaAuxiliar.sort(comparador);
                Collections.reverse(listaAuxiliar);
                this.getClasificacionCarrera().addAll(listaAuxiliar);
                System.out.println(ANSI_YELLOW +"=== Esta es la clasificacion de la carrera: ==="+ ANSI_RESET);
                for (int i = 0; i<this.getClasificacionCarrera().size(); i++){
                    System.out.println((i+1) +"º. "+ getClasificacionCarrera().get(i).getModelo() +" con pegatina "+ getClasificacionCarrera().get(i).getPegatinaCoche());
                }

                if (isTorneo==true){
                    System.out.println("El podio de la carrera es: \n 1º. " + this.getClasificacionCarrera().get(0).getModelo() + "\n 2º. " + this.getClasificacionCarrera().get(1).getModelo() + "\n 3º. " + this.getClasificacionCarrera().get(2).getModelo());
                    System.out.println("Se llevarán 10, 7 y 5 puntos respectivamente.");
                    this.getClasificacionCarrera().get(0).setPuntuacion(  this.getClasificacionCarrera().get(0).getPuntuacion() + 10);
                    this.getClasificacionCarrera().get(1).setPuntuacion(  this.getClasificacionCarrera().get(1).getPuntuacion() + 7);
                    this.getClasificacionCarrera().get(2).setPuntuacion(  this.getClasificacionCarrera().get(2).getPuntuacion() + 5);

                    for (int i = 0; i<this.getClasificacionCarrera().size(); i++){
                        System.out.println("El "+ this.getClasificacionCarrera().get(i).getModelo() + " recorrio " +  this.getClasificacionCarrera().get(i).getVelocidadTotalCarrera() + " y tiene una puntuacion de " + this.getClasificacionCarrera().get(i).getPuntuacion() ) ;
                    }
                    this.setCarreraCelebrada(true);
                }


            } else {
                //OPCION 2 VARIOS GARAJES DISPONIBLES//

                //RANDOMIZACION DEL NUMERO DE GARAJES PARTICIPANTES DE LA CARRERA (1 O VARIOS)//
                int aleatorio = (int) Math.floor(Math.random() * (2 - 1 + 1) + 1);

                if (aleatorio == 2) {
                    //ALEATORIAMENTE PARTICIPAN VARIOS GARAJES EN LA CARRERA//
                    System.out.println(ANSI_RED +"=== La carrera se celebrará con varios garajes: ==="+ ANSI_RESET);
                    this.getRegistroGarajes().forEach(e -> System.out.println("- " + e.getPegatinaGaraje()));
                    System.out.println(ANSI_YELLOW +"=== Celebrando carrera... ==="+ ANSI_RESET);
                    ArrayList<Coche> cochesParticipantes = new ArrayList<>();
                    ArrayList<Coche> listaAuxiliar = new ArrayList<>();

                    //BUCLE PARA ELEGIR UN COCHE DE CADA GARAJE DE MANERA ALEATORIA
                    for (Garaje g : this.getRegistroGarajes()) {
                        int cocheParticpanteDeGaraje = (int) Math.floor(Math.random() * ((g.getCochesGaraje().size() - 1) - 0 + 1) + 0);
                        //System.out.println(g.getCochesGaraje().get(0).getModelo());
                        cochesParticipantes.add(g.getCochesGaraje().get(0)); //PUEDE DAR FALLO?????// SI EL RANDOM ES 0 DA FALLO???
                    }
                    //Reseteo la velocidad de los coches a 0 para que no se acumule en las carreras/torneos
                    cochesParticipantes.forEach(e-> e.setVelocidad(0));

                    int cantidadCochesCarrera = (cochesParticipantes.size()-1);
                    for (int vuelta = 0; vuelta <= (cantidadCochesCarrera+this.vueltasCalentamiento); vuelta++) {///////PARECE SOLUCIONADO

                        for (int i = 0; i<cantidadCochesCarrera && i < cochesParticipantes.size(); i++) {///////PARECE SOLUCIONADO EL Index 1 out of bounds for length 1// COMPROBAR CON MAS COCHES
                            cochesParticipantes.get(i).acelerar_frenar();
                        }
                        if (cochesParticipantes.size()>=1){
                            cochesParticipantes.sort(comparador);
                            Collections.reverse(cochesParticipantes);
                            if (cochesParticipantes.size()>1){
                                System.out.println(ANSI_RED +"- Eliminado: " + cochesParticipantes.get(cochesParticipantes.size()-1).getModelo() + " en la vuelta " + (vuelta +1)+ ANSI_RESET);
                                listaAuxiliar.add(cochesParticipantes.get(cochesParticipantes.size()-1));
                                cochesParticipantes.remove(cochesParticipantes.size()-1);
                            } else {
                                System.out.println(ANSI_GREEN +"- Solo queda un coche en pista: " + cochesParticipantes.get(cochesParticipantes.size()-1).getModelo() + " es el ganador de la carrera "+ ANSI_RESET);
                                listaAuxiliar.add(cochesParticipantes.get(cochesParticipantes.size()-1));
                                cochesParticipantes.remove(cochesParticipantes.size()-1);
                            }
                        }
                    }

                    // Ordenar cochesParticipantes utilizando el comparador al inicio del método//
                    listaAuxiliar.sort(comparador);
                    Collections.reverse(listaAuxiliar);
                    this.getClasificacionCarrera().addAll(listaAuxiliar);
                    System.out.println(ANSI_YELLOW +"=== Esta es la clasificacion de la carrera: ==="+ ANSI_RESET);
                    for (int i = 0; i<this.getClasificacionCarrera().size(); i++){
                        System.out.println((i+1) +"º. "+ getClasificacionCarrera().get(i).getModelo() +" con pegatina "+ getClasificacionCarrera().get(i).getPegatinaCoche() + " recorrio " +  this.getClasificacionCarrera().get(i).getVelocidadTotalCarrera() + " km");
                    }
                    if (isTorneo==true){
                        this.getClasificacionCarrera().get(0).setPuntuacion(  this.getClasificacionCarrera().get(0).getPuntuacion() + 10);
                        this.getClasificacionCarrera().get(1).setPuntuacion(  this.getClasificacionCarrera().get(1).getPuntuacion() + 7);
                        this.getClasificacionCarrera().get(2).setPuntuacion(  this.getClasificacionCarrera().get(2).getPuntuacion() + 5);
                        System.out.println("El podio de la carrera es: \n 1º. " + this.getClasificacionCarrera().get(0).getModelo() + "\n 2º. " + this.getClasificacionCarrera().get(1).getModelo() + "\n 3º. " + this.getClasificacionCarrera().get(2).getModelo());
                        System.out.println("Se llevarán 10, 7 y 5 puntos respectivamente.");
                        this.setCarreraCelebrada(true);
                    }


                } else {
                    //OPECION 3 ALEATORIAMENTE PARTICIPA SOLO 1 GARAJE EN LA CARRERA//
                    int aleatorio2 = (int) Math.floor(Math.random() * ((this.getRegistroGarajes().size() - 1) - 0 + 1) + 0);
                    System.out.println(ANSI_RED +"=== La carrera se celebrará con un unico garaje: " + this.getRegistroGarajes().get(aleatorio2).getPegatinaGaraje() + " === \n Los coches participantes son: "+ ANSI_RESET);
                    ArrayList<Coche> cochesParticipantes = this.getRegistroGarajes().get(aleatorio2).getCochesGaraje();
                    ArrayList<Coche> listaAuxiliar = new ArrayList<>();
                    //Reseteo la velocidad de los coches a 0 para que no se acumule en las carreras/torneos
                    cochesParticipantes.forEach(e-> e.setVelocidad(0));

                    cochesParticipantes.forEach(elemento -> System.out.println("- " + elemento.getModelo() +" con pegatina "+ elemento.getPegatinaCoche()));

                    System.out.println(ANSI_YELLOW + "=== Celebrando carrera... ==="+ ANSI_RESET);
                    for (int vuelta = 0; vuelta <= (this.getRegistroGarajes().get(aleatorio2).getCochesGaraje().size()+this.vueltasCalentamiento); vuelta++) {

                        for (int i = 0; i<this.getRegistroGarajes().get(aleatorio2).getCochesGaraje().size(); i++) {
                            cochesParticipantes.get(i).acelerar_frenar();
                        }
                        if (cochesParticipantes.size()>=1){
                            cochesParticipantes.sort(comparador);
                            Collections.reverse(cochesParticipantes);
                            if (cochesParticipantes.size()>1){
                                System.out.println(ANSI_RED +"Eliminado: " + cochesParticipantes.get(cochesParticipantes.size()-1).getModelo() + " en la vuelta " + (vuelta +1)+ ANSI_RESET);
                                listaAuxiliar.add(cochesParticipantes.get(cochesParticipantes.size()-1));
                                cochesParticipantes.remove(cochesParticipantes.size()-1);
                            } else {
                                System.out.println(ANSI_GREEN +"Solo queda un coche en pista: " + cochesParticipantes.get(cochesParticipantes.size()-1).getModelo() + " en la vuelta " + (vuelta +1)+ ANSI_RESET);
                                listaAuxiliar.add(cochesParticipantes.get(cochesParticipantes.size()-1));
                                cochesParticipantes.remove(cochesParticipantes.size()-1);
                            }
                        }
                    }

                    // Ordenar cochesParticipantes utilizando el comparador al inicio del método//
                    listaAuxiliar.sort(comparador);
                    Collections.reverse(listaAuxiliar);
                    this.getClasificacionCarrera().addAll(listaAuxiliar);
                    System.out.println(ANSI_YELLOW + "=== Esta es la clasificacion de la carrera: ==="+ ANSI_RESET);
                    for (int i = 0; i<this.getClasificacionCarrera().size(); i++){
                        System.out.println((i+1) +"º. "+ getClasificacionCarrera().get(i).getModelo() +" con pegatina "+ getClasificacionCarrera().get(i).getPegatinaCoche());
                    }

                    System.out.println("El podio de la carrera es: \n 1º. " + this.getClasificacionCarrera().get(0).getModelo() + "\n 2º. " + this.getClasificacionCarrera().get(1).getModelo() + "\n 3º. " + this.getClasificacionCarrera().get(2).getModelo());
                    System.out.println("Se llevarán 10, 7 y 5 puntos respectivamente.");

                    if (isTorneo==true){
                        this.getClasificacionCarrera().get(0).setPuntuacion(  this.getClasificacionCarrera().get(0).getPuntuacion() + 10);
                        this.getClasificacionCarrera().get(1).setPuntuacion(  this.getClasificacionCarrera().get(1).getPuntuacion() + 7);
                        this.getClasificacionCarrera().get(2).setPuntuacion(  this.getClasificacionCarrera().get(2).getPuntuacion() + 5);

                        for (int i = 0; i<this.getClasificacionCarrera().size(); i++){
                            System.out.println("El "+ this.getClasificacionCarrera().get(i).getModelo() + " recorrio " +  this.getClasificacionCarrera().get(i).getVelocidadTotalCarrera() + " y tiene una puntuacion de " + this.getClasificacionCarrera().get(i).getPuntuacion() ) ;
                        }
                        this.setCarreraCelebrada(true);
                    }
                }
            }
        }
    }

    public int getVueltasCalentamiento() {
        return vueltasCalentamiento;
    }

    public void setVueltasCalentamiento(int vueltasCalentamiento) {
        this.vueltasCalentamiento = vueltasCalentamiento;
    }
}
