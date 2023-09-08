package com.campusdual.racecontrol.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CarreraEliminacion extends Carrera {

    private int vueltasCalentamiento;

    public CarreraEliminacion(String name, ArrayList<Garaje> registroGarajes, int vueltasCalentamiento) {
        super(name, registroGarajes);
        this.vueltasCalentamiento = vueltasCalentamiento;
    }

    @Override
    public void celebrarCarrera() {

        // Comparador personalizado para comparar por la velocidad/distancia total de la carrera//
        Comparator<Coche> comparador = Comparator.comparing(Coche::getVelocidadTotalCarrera);

        //COMPROBACION DE SI LA CARRERA YA SE HA CELEBRADO O NO//

        if (this.isCarreraCelebrada()) {
            System.out.println("El " + this.getName() + " ya se ha celebrado. La clasificación final fue: ");
            for (int i =0; i<this.getClasificacionCarrera().size(); i++){
                 System.out.println("#"+ (i+1) +"º " + this.getClasificacionCarrera().get(i).getModelo()  +" con pegatina "+ this.getClasificacionCarrera().get(i).getPegatinaCoche());
            }
        } else {

            //COMPROBACION DE SI EL NUMERO DE GARAJES DISPONIBLES DE LA CARRERA ES MAYOR QUE 1//

            if (this.getRegistroGarajes().size() < 2) {
                int aleatorio2 = (int) Math.floor(Math.random() * ((this.getRegistroGarajes().size() - 1) - 0 + 1) + 0);

                //OPCION 1 SOLO GARAJE DISPONIBLE//
                System.out.println("La carrera se celebrará con el unico garaje disponible: " + this.getRegistroGarajes().get(aleatorio2).getPegatinaGaraje() + "\n Los coches participantes son:");
                ArrayList<Coche> cochesParticipantes = this.getRegistroGarajes().get(aleatorio2).getCochesGaraje();
                ArrayList<Coche> listaAuxiliar = new ArrayList<>();
                listaAuxiliar.addAll(cochesParticipantes);
                cochesParticipantes.forEach(elemento -> System.out.println(elemento.getModelo() +" con pegatina "+ elemento.getPegatinaCoche()));
                System.out.println("=== Celebrando carrera... ===");
                for (int vuelta = 0; vuelta <= (cochesParticipantes.size()+this.vueltasCalentamiento); vuelta++) {
                    for (int i = 0; i<listaAuxiliar.size(); i++) {
                       listaAuxiliar.get(i).acelerar_frenar();
                       cochesParticipantes.get(i).acelerar_frenar();
                    }
                    if (listaAuxiliar.size()>=2){
                        listaAuxiliar.sort(comparador);
                        Collections.reverse(listaAuxiliar);
                        System.out.println("Eliminado: " + listaAuxiliar.get(listaAuxiliar.size()-1).getModelo() + " en la vuelta " + (vuelta +1));
                        listaAuxiliar.remove(listaAuxiliar.size()-1);
                    }
                }

                // Ordenar cochesParticipantes utilizando el comparador al inicio del método//
                cochesParticipantes.sort(comparador);
                Collections.reverse(cochesParticipantes);
                this.getClasificacionCarrera().addAll(cochesParticipantes);
                System.out.println("=== Esta es la clasificacion de la carrera: ===");

                for (int i = 0; i<this.getClasificacionCarrera().size(); i++){
                    System.out.println((i+1) +"º. "+ getClasificacionCarrera().get(i).getModelo() +" con pegatina "+ getClasificacionCarrera().get(i).getPegatinaCoche());
                }

                this.setCarreraCelebrada(true);

                System.out.println("El podio de la carrera es: \n 1º. " + this.getClasificacionCarrera().get(0).getModelo() + "\n 2º. " + this.getClasificacionCarrera().get(1).getModelo() + "\n 3º. " + this.getClasificacionCarrera().get(2).getModelo());
                System.out.println("Se llevarán 10, 7 y 5 puntos respectivamente.");
                this.getClasificacionCarrera().get(0).setPuntuacion(  this.getClasificacionCarrera().get(0).getPuntuacion() + 10);
                this.getClasificacionCarrera().get(1).setPuntuacion(  this.getClasificacionCarrera().get(1).getPuntuacion() + 7);
                this.getClasificacionCarrera().get(2).setPuntuacion(  this.getClasificacionCarrera().get(2).getPuntuacion() + 5);

                for (int i = 0; i<this.getClasificacionCarrera().size(); i++){
                    System.out.println("El "+ this.getClasificacionCarrera().get(i).getModelo() + " recorrio " +  this.getClasificacionCarrera().get(i).getVelocidadTotalCarrera() + " y tiene una puntuacion de " + this.getClasificacionCarrera().get(i).getPuntuacion() ) ;
                }

            } else {
                //OPCION VARIOS GARAJES DISPONIBLES//

                //RANDOMIZACION DEL NUMERO DE GARAJES PARTICIPANTES DE LA CARRERA (1 O VARIOS)//
                int aleatorio = (int) Math.floor(Math.random() * (2 - 1 + 1) + 1);

                if (aleatorio == 2) {
                    //ALEATORIAMENTE PARTICIPAN VARIOS GARAJES EN LA CARRERA//
                    System.out.println("=== La carrera se celebrará con varios garajes: ===");
                    this.getRegistroGarajes().forEach(e -> System.out.println("- " + e.getPegatinaGaraje()));
                    System.out.println("=== Celebrando carrera... ===");
                    ArrayList<Coche> cochesParticipantes = new ArrayList<>();
                    ArrayList<Coche> listaAuxiliar = new ArrayList<>();
                    listaAuxiliar.addAll(cochesParticipantes);

                    for (Garaje g : this.getRegistroGarajes()) {
                        int cocheParticpanteDeGaraje = (int) Math.floor(Math.random() * ((g.getCochesGaraje().size() - 1) - 0 + 1) + 0);
                        cochesParticipantes.add(g.getCochesGaraje().get(cocheParticpanteDeGaraje));
                    }

                    for (int vuelta = 0; vuelta <= (cochesParticipantes.size()+this.vueltasCalentamiento); vuelta++) {
                        for (int i = 0; i<listaAuxiliar.size(); i++) {
                            listaAuxiliar.get(i).acelerar_frenar();
                            cochesParticipantes.get(i).acelerar_frenar();
                        }
                        if (listaAuxiliar.size()>=2){
                            listaAuxiliar.sort(comparador);
                            Collections.reverse(listaAuxiliar);
                            System.out.println("Eliminado: " + listaAuxiliar.get(listaAuxiliar.size()-1).getModelo() + " en la vuelta " + (vuelta +1));
                            listaAuxiliar.remove(listaAuxiliar.size()-1);
                        }
                    }

                    // Ordenar cochesParticipantes utilizando el comparador al inicio del método//
                    cochesParticipantes.sort(comparador);
                    Collections.reverse(cochesParticipantes);
                    this.getClasificacionCarrera().addAll(cochesParticipantes);
                    System.out.println("=== Esta es la clasificacion de la carrera: ===");
                    for (int i = 0; i<this.getClasificacionCarrera().size(); i++){
                        System.out.println((i+1) +"º. "+ getClasificacionCarrera().get(i).getModelo() +" con pegatina "+ getClasificacionCarrera().get(i).getPegatinaCoche());
                    }
                    this.setCarreraCelebrada(true);

                    System.out.println("El podio de la carrera es: \n 1º. " + this.getClasificacionCarrera().get(0).getModelo() + "\n 2º. " + this.getClasificacionCarrera().get(1).getModelo() + "\n 3º. " + this.getClasificacionCarrera().get(2).getModelo());
                    System.out.println("Se llevarán 10, 7 y 5 puntos respectivamente.");
                    this.getClasificacionCarrera().get(0).setPuntuacion(  this.getClasificacionCarrera().get(0).getPuntuacion() + 10);
                    this.getClasificacionCarrera().get(1).setPuntuacion(  this.getClasificacionCarrera().get(1).getPuntuacion() + 7);
                    this.getClasificacionCarrera().get(2).setPuntuacion(  this.getClasificacionCarrera().get(2).getPuntuacion() + 5);

                    for (int i = 0; i<this.getClasificacionCarrera().size(); i++){
                        System.out.println("El "+ this.getClasificacionCarrera().get(i).getModelo() + " recorrio " +  this.getClasificacionCarrera().get(i).getVelocidadTotalCarrera() + " y tiene una puntuacion de " + this.getClasificacionCarrera().get(i).getPuntuacion() ) ;
                    }


                } else {
                    //ALEATORIAMENTE PARTICIPA SOLO 1 GARAJE EN LA CARRERA//
                    int aleatorio2 = (int) Math.floor(Math.random() * ((this.getRegistroGarajes().size() - 1) - 0 + 1) + 0);
                    System.out.println("La carrera se celebrará con un unico garaje: " + this.getRegistroGarajes().get(aleatorio2).getPegatinaGaraje() + "\n Los coches participantes son: ");
                    ArrayList<Coche> cochesParticipantes = this.getRegistroGarajes().get(aleatorio2).getCochesGaraje();
                    ArrayList<Coche> listaAuxiliar = new ArrayList<>();
                    listaAuxiliar.addAll(cochesParticipantes);
                    cochesParticipantes.forEach(elemento -> System.out.println(elemento.getModelo() +" con pegatina "+ elemento.getPegatinaCoche()));

                    System.out.println("=== Celebrando carrera... ===");
                    for (int vuelta = 0; vuelta <= (cochesParticipantes.size()+this.vueltasCalentamiento); vuelta++) {
                        for (int i = 0; i<listaAuxiliar.size(); i++) {
                            listaAuxiliar.get(i).acelerar_frenar();
                            cochesParticipantes.get(i).acelerar_frenar();
                        }
                        if (listaAuxiliar.size()>=2){
                            listaAuxiliar.sort(comparador);
                            Collections.reverse(listaAuxiliar);
                            System.out.println("Eliminado: " + listaAuxiliar.get(listaAuxiliar.size()-1).getModelo() + " en la vuelta " + (vuelta +1));
                            listaAuxiliar.remove(listaAuxiliar.size()-1);
                        }
                    }

                    // Ordenar cochesParticipantes utilizando el comparador al inicio del método//
                    cochesParticipantes.sort(comparador);
                    Collections.reverse(cochesParticipantes);
                    this.getClasificacionCarrera().addAll(cochesParticipantes);
                    System.out.println("=== Esta es la clasificacion de la carrera: ===");
                    for (int i = 0; i<this.getClasificacionCarrera().size(); i++){
                        System.out.println((i+1) +"º. "+ getClasificacionCarrera().get(i).getModelo() +" con pegatina "+ getClasificacionCarrera().get(i).getPegatinaCoche());
                    }
                    this.setCarreraCelebrada(true);

                    System.out.println("El podio de la carrera es: \n 1º. " + this.getClasificacionCarrera().get(0).getModelo() + "\n 2º. " + this.getClasificacionCarrera().get(1).getModelo() + "\n 3º. " + this.getClasificacionCarrera().get(2).getModelo());
                    System.out.println("Se llevarán 10, 7 y 5 puntos respectivamente.");
                    this.getClasificacionCarrera().get(0).setPuntuacion(  this.getClasificacionCarrera().get(0).getPuntuacion() + 10);
                    this.getClasificacionCarrera().get(1).setPuntuacion(  this.getClasificacionCarrera().get(1).getPuntuacion() + 7);
                    this.getClasificacionCarrera().get(2).setPuntuacion(  this.getClasificacionCarrera().get(2).getPuntuacion() + 5);

                    for (int i = 0; i<this.getClasificacionCarrera().size(); i++){
                        System.out.println("El "+ this.getClasificacionCarrera().get(i).getModelo() + " recorrio " +  this.getClasificacionCarrera().get(i).getVelocidadTotalCarrera() + " y tiene una puntuacion de " + this.getClasificacionCarrera().get(i).getPuntuacion() ) ;
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
