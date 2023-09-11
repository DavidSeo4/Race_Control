package com.campusdual.racecontrol.model;

import java.util.*;

public class CarreraEstandar extends Carrera {

    private int duracionHoras;

    public CarreraEstandar(String name, ArrayList<Garaje> registroGarajes, int duracionHoras) {
        super(name, registroGarajes);
        this.duracionHoras = duracionHoras;
    }

    @Override
    public void celebrarCarrera(boolean isTorneo, Torneo torneo) {

        // Comparador personalizado para comparar por la velocidad/distancia total de la carrera//
        Comparator<Coche> comparador = Comparator.comparing(Coche::getVelocidadTotalCarrera);

        //COMPROBACION DE SI LA CARRERA YA SE HA CELEBRADO O NO//

        if (this.isCarreraCelebrada()) {
            System.out.println("El " + this.getName() + " ya se ha celebrado. La clasificación final fue: ");
            this.getClasificacionCarrera().forEach(elemento -> System.out.println(elemento.getModelo() +" con pegatina "+ elemento.getPegatinaCoche()));
        } else {

            //COMPROBACION DE SI EL NUMERO DE GARAJES DISPONIBLES DE LA CARRERA ES MAYOR QUE 1//

            if (this.getRegistroGarajes().size() < 2) {
                //OPCION 1 SOLO GARAJE DISPONIBLE//
                carrera1Garaje(isTorneo,comparador, torneo);

            } else {
                //OPCION 2 VARIOS GARAJES DISPONIBLES//

                //RANDOMIZACION DEL NUMERO DE GARAJES PARTICIPANTES DE LA CARRERA (1 O VARIOS)//
                int aleatorio = (int) Math.floor(Math.random() * (2 - 1 + 1) + 1);

                if (aleatorio == 2) {
                  carreraVariosGarajes(isTorneo,comparador, torneo);

                } else {
                  carrera1Garaje(isTorneo,comparador, torneo);
                }
            }
        }
    }

    public void carrera1Garaje(boolean isTorneo, Comparator comparador, Torneo torneo){
        //PARTICIPA SOLO 1 GARAJE EN LA CARRERA//
        int aleatorio2 = (int) Math.floor(Math.random() * ((this.getRegistroGarajes().size() - 1) - 0 + 1) + 0);
        System.out.println("La carrera se celebrará con un unico garaje: " + this.getRegistroGarajes().get(aleatorio2).getPegatinaGaraje() + "\n Los coches participantes son: ");
        ArrayList<Coche> cochesParticipantes = this.getRegistroGarajes().get(aleatorio2).getCochesGaraje();
        cochesParticipantes.forEach(elemento -> System.out.println(elemento.getModelo() +" con pegatina "+ elemento.getPegatinaCoche()));
        //Reseteo la velocidad de los coches a 0 para que no se acumule en las carreras/torneos
        cochesParticipantes.forEach(e-> e.setVelocidad(0));
        cochesParticipantes.forEach(e-> e.setVelocidadTotalCarrera(0));

        System.out.println("=== Celebrando carrera... ===");
        for (int minuto = 0; minuto <= (this.duracionHoras*60); minuto++) {
            for (Coche c : cochesParticipantes) {
                c.acelerar_frenar();
            }
        }

        // Ordenar cochesParticipantes utilizando el comparador al inicio del método//
        cochesParticipantes.sort(comparador);
        Collections.reverse(cochesParticipantes);
        this.getClasificacionCarrera().addAll(cochesParticipantes); //DEBO RESETEAR EN ALGUN MOMENTO LA CLASIFICACION???
        System.out.println("=== Esta es la clasificacion de la carrera: ===");
        for (int i = 0; i<this.getClasificacionCarrera().size(); i++){
            System.out.println((i+1) +"º. "+ getClasificacionCarrera().get(i).getModelo() +" con pegatina "+ getClasificacionCarrera().get(i).getPegatinaCoche());
        }

        if (isTorneo==true){
            this.setCarreraCelebrada(true);

            System.out.println("El podio de la carrera es: \n 1º. " + this.getClasificacionCarrera().get(0).getModelo() + "\n 2º. " + this.getClasificacionCarrera().get(1).getModelo() + "\n 3º. " + this.getClasificacionCarrera().get(2).getModelo());
            System.out.println("Se llevarán 10, 7 y 5 puntos respectivamente.");
            this.getClasificacionCarrera().get(0).setPuntuacion(  this.getClasificacionCarrera().get(0).getPuntuacion() + 10);
            this.getClasificacionCarrera().get(1).setPuntuacion(  this.getClasificacionCarrera().get(1).getPuntuacion() + 7);
            this.getClasificacionCarrera().get(2).setPuntuacion(  this.getClasificacionCarrera().get(2).getPuntuacion() + 5);

            for (int i = 0; i<this.getClasificacionCarrera().size(); i++){
                System.out.println("El "+ this.getClasificacionCarrera().get(i).getModelo() + " recorrio " +  this.getClasificacionCarrera().get(i).getVelocidadTotalCarrera() + " y tiene una puntuacion de " + this.getClasificacionCarrera().get(i).getPuntuacion() ) ;
            }
            //Gestion participantes del torneo
            if (torneo.getClasificacion().isEmpty()){
                torneo.getClasificacion().addAll(this.getClasificacionCarrera());
            }
            for (Coche c: this.getClasificacionCarrera() ){
                if (!torneo.getClasificacion().contains(c)){
                    torneo.getClasificacion().add(c);
                }
            }
        }

    }

    public void carreraVariosGarajes(boolean isTorneo, Comparator comparador, Torneo torneo){
        //PARTICIPAN VARIOS GARAJES EN LA CARRERA//
        System.out.println("=== La carrera se celebrará con varios garajes: ===");
        this.getRegistroGarajes().forEach(e -> System.out.println("- " + e.getPegatinaGaraje()));
        ArrayList<Coche> cochesParticipantes = new ArrayList<>();

        //BUCLE PARA ELEGIR UN COCHE DE CADA GARAJE DE MANERA ALEATORIA
        for (Garaje g : this.getRegistroGarajes()) {
            int cocheParticpanteDeGaraje = (int) Math.floor(Math.random() * ((g.getCochesGaraje().size() - 1) - 0 + 1) + 0);
            cochesParticipantes.add(g.getCochesGaraje().get(cocheParticpanteDeGaraje));
        }

        //Reseteo la velocidad de los coches a 0 para que no se acumule en las carreras/torneos
        cochesParticipantes.forEach(e-> e.setVelocidad(0));
        cochesParticipantes.forEach(e-> e.setVelocidadTotalCarrera(0));

        System.out.println("=== Celebrando carrera... ===");
        for (int minuto = 0; minuto <= (this.duracionHoras*60); minuto++) {
            for (Coche c : cochesParticipantes) {
                c.acelerar_frenar();
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
        if (isTorneo==true){
            this.setCarreraCelebrada(true);
            System.out.println("El podio de la carrera es: \n 1º. " + this.getClasificacionCarrera().get(0).getModelo() + "\n 2º. " + this.getClasificacionCarrera().get(1).getModelo() + "\n 3º. " + this.getClasificacionCarrera().get(2).getModelo());
            System.out.println("Se llevarán 10, 7 y 5 puntos respectivamente.");
            this.getClasificacionCarrera().get(0).setPuntuacion(  this.getClasificacionCarrera().get(0).getPuntuacion() + 10);
            this.getClasificacionCarrera().get(1).setPuntuacion(  this.getClasificacionCarrera().get(1).getPuntuacion() + 7);
            this.getClasificacionCarrera().get(2).setPuntuacion(  this.getClasificacionCarrera().get(2).getPuntuacion() + 5);

            for (int i = 0; i<this.getClasificacionCarrera().size(); i++){
                System.out.println("El "+ this.getClasificacionCarrera().get(i).getModelo() + " recorrio " +  this.getClasificacionCarrera().get(i).getVelocidadTotalCarrera() + " y tiene una puntuacion de " + this.getClasificacionCarrera().get(i).getPuntuacion() ) ;
            }
            //Gestion participantes del torneo
            if (torneo.getClasificacion().isEmpty()){
                torneo.getClasificacion().addAll(this.getClasificacionCarrera());
            }
            for (Coche c: this.getClasificacionCarrera() ){
                if (!torneo.getClasificacion().contains(c)){
                    torneo.getClasificacion().add(c);
                }
            }
        }

    }

    @Override
    public String toString() {
        return super.toString()+ "CarreraEstandar{" +
                "duracionHoras=" + duracionHoras +
                '}';
    }

    public int getDuracionHoras() {
        return duracionHoras;
    }

    public void setDuracionHoras(int duracionHoras) {
        this.duracionHoras = duracionHoras;
    }
}
