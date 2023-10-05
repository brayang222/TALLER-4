package com.brayanvargas.domain;

import javax.swing.*;
import java.util.Random;

public class Guayabita {
   int dado;
   int poteTiene;
   int numeroJugadores;
   int jugadorActual = 1;

   Jugador[] jugadores;

   public Guayabita() {
   }

   public void inicializarJuego() {
      this.numeroJugadores = Integer.parseInt(JOptionPane.showInputDialog(null,
            "Cuantos usuarios jugaran"));
      this.jugadores = new Jugador[numeroJugadores];
      System.out.println(numeroJugadores);
   }

   public void generarJugadores(){
      for (int i = 0; i < numeroJugadores; i++){
         jugadores[i] = new Jugador(jugadorActual);
         System.out.println("nombre jugador" + jugadorActual );
         System.out.println("presupuesto jugador" );

         jugadorActual++;
      }
   }

   public void tirarDados(){
      Random random = new Random();
      int resultadoDados = random.nextInt(6) + 1; // Genera un número aleatorio entre 1 y 6

      String[] opciones = {"Tirar dado", "No apostar"};

         JOptionPane.showMessageDialog(
               null,
               "Resultado del dado: " + resultadoDados,
               "Resultado del Dado",
               JOptionPane.INFORMATION_MESSAGE
         );
   }

   public void avanzarTurno() {
      jugadorActual = (jugadorActual + 1) % numeroJugadores;
      if (jugadorActual < 0) {
         jugadorActual = numeroJugadores - 1; // Vuelve al último jugador si llega al final
      }
   }


   public void jugar() {
      generarJugadores();
      boolean juegoActivo = true; // Variable para controlar si el juego está activo

      while (juegoActivo) {
         Jugador jugador = jugadores[jugadorActual];
         JOptionPane.showMessageDialog(null, "Turno de " + jugador.getNombreJugador());

         // Mostrar las opciones "Tirar dado" y "No apostar"
         String[] opciones = {"Tirar dado", "No apostar"};
         int respuesta = JOptionPane.showOptionDialog(
               null,
               "¿Qué deseas hacer?",
               "Tirar Dado",
               JOptionPane.DEFAULT_OPTION,
               JOptionPane.QUESTION_MESSAGE,
               null,
               opciones,
               opciones[0]
         );

         if (respuesta == 0) {
            tirarDados();
            avanzarTurno();

            // Aquí debes agregar la lógica para verificar si el juego debe continuar o finalizar
            // Por ejemplo, si el pote está vacío o si solo queda un jugador con dinero
            if (poteTiene <= 0 || quedanJugadoresConDinero()) {
               juegoActivo = false; // El juego se detiene
            }
         } else {
            avanzarTurno();
         }
      }
   }

   // Agrega este método para verificar si quedan jugadores con dinero
   private boolean quedanJugadoresConDinero() {
      for (Jugador jugador : jugadores) {
         if (jugador.getPresupuesto() > 0) {
            return true;
         }
      }
      return false;
   }

   public void instrucciones(){
      JOptionPane.showMessageDialog(null,
            "Si el jugador saca 1 o 6 entonces pierde la posibilidad de apostar y por ende cede el turno" +
                  "\nal siguiente jugador. \n\nSi por el contrario saca un número del 2 al 5 tiene la posibilidad de " +
                  "apostar por el pote que \n hay en el juego. Si elige que no quiere apostar cede su turno, pero si" +
                  "quiere hacerlo, el juego \n debe perimitir ingresar el monto por el que desea apostar y luego tirar" +
                  "nuevamente el dado. \n\n El jugador puede apostar por la totalidad del pote o por una parte (por " +
                  "ejemplo, si el pote es de \n $2000 o un valor inferior). Se debe controlar que el jugador si cuente " +
                  "con el valor de la apuesta \n que desea realizar. \n\n" +
                  "Si el jugador saca un numero mayor al que saco en la tirada anterior entonces se lleva el dinero \n" +
                  "del pote (o la parte que aposto). Si por el contrario el jugador saca un numero igual o inferior \n" +
                  "entonces tendra que entregar lo que aposto al pote y asi este ira aumentando. \n\n" +
                  "Despues de esto al siguiente jugador se le pregunta si desea lanzar el dado y comienza su flujo \n" +
                  "nuevamente \n\n" +
                  "Cuando a un jugador se le acabe el dinero para apostar sera eliminado. El juego termina cuando ya \n" +
                  "no quede dinero en el pote y debera mostrar la cantidad de dinero con la que quedo cada usuario. \n");
   }


}
