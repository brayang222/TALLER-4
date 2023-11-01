package com.brayanvargas.domain;

import javax.swing.*;
import java.util.Random;

public class Guayabita {
   private int dado = 0;
   private int poteTiene;
   private int numeroJugadores;
   private int jugadorActual = 0;
   private Jugador[] jugadores;
   private Random random = new Random();

   public Guayabita() {
      this.poteTiene = 0;
   }

   public void inicializarJuego() {
      this.numeroJugadores = Integer.parseInt(JOptionPane.showInputDialog(null,
            "Cuantos usuarios jugaran"));
      this.jugadores = new Jugador[numeroJugadores];

      generarJugadores();
   }

   public void generarJugadores() {
      for (int i = 0; i < numeroJugadores; i++) {
         jugadores[i] = new Jugador(i + 1);
         jugadores[i].setPresupuesto(obtenerPresupuesto());
         poteTiene += 500;
      }
   }

   public int obtenerResultadoDados() {
      return random.nextInt(6) + 1;
   }

   public int obtenerApuesta(Jugador jugador) {
      int apuesta = 0;
      boolean apuestaValida = false;

      while (!apuestaValida) {
         String input = JOptionPane.showInputDialog(null, "Ingresa la cantidad que deseas apostar " +
               "(su presupuesto es:" + jugador.getPresupuesto() + ") y el presupuesto del pote es: " + getPoteTiene());

         try {
            apuesta = Integer.parseInt(input);
            if (apuesta >= 1 && apuesta <= jugador.getPresupuesto() && apuesta <= poteTiene) {
               apuestaValida = true;
            } else {
               JOptionPane.showMessageDialog(null, "Apuesta no valida. Debes apostar un valor entre 1 y tu presupuesto actual.");
            }
         } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingresa un valor numerico valido.");
         }
      }

      return apuesta;
   }

   public int obtenerPresupuesto() {
      int presupuesto = 0;
      boolean presupuestoValido = false;

      Jugador jugador = jugadores[jugadorActual];
      jugadorActual = (jugadorActual + 1) % jugadores.length;

      while (!presupuestoValido) {
         String input = JOptionPane.showInputDialog(null, "Ingresa el presupuesto del "
               + jugador.getNombreJugador());

         try {
            presupuesto = Integer.parseInt(input);
            if (presupuesto >= 550) {
               presupuestoValido = true;
            } else {
               JOptionPane.showMessageDialog(null, "Presupuesto no valido. Debe ser un valor mayor o igual a 550. \n" +
                     "Para que aportes los 500 para ingresar y tengas minimo 50 para apostar");
            }
         } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingresa un valor numerico valido.");
         }
      }

      return presupuesto;
   }



   public void jugar() {
      boolean juegoActivo = true;
      boolean alguienGano = false;

      while (juegoActivo) {
         Jugador jugador = jugadores[jugadorActual];
         JOptionPane.showMessageDialog(null, "Turno de " + jugador.getNombreJugador());

         int resultadoDados = obtenerResultadoDados();
         String mensaje = "Resultado del dado: " + resultadoDados;
         ImageIcon iconoDado = new ImageIcon("src/main/java/com/brayanvargas/resources/dado" + resultadoDados + ".png");
         JOptionPane.showMessageDialog(null, mensaje, "Resultado del Dado", JOptionPane.PLAIN_MESSAGE, iconoDado);

         // Verificar si el jugador puede apostar
         if (resultadoDados != 1 && resultadoDados != 6) {
            boolean quiereApostar = JOptionPane.showConfirmDialog(
                  null,
                  "Deseas apostar?",
                  "Apostar",
                  JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

            if (quiereApostar) {
               int apuesta = obtenerApuesta(jugador);
               int resultadoSegundaTirada = obtenerResultadoDados();
               ImageIcon iconoDadoSegunda = new ImageIcon("src/main/java/com/brayanvargas/resources/dado" + resultadoSegundaTirada + ".png");
               JOptionPane.showMessageDialog(null, mensaje, "Resultado del Dado", JOptionPane.PLAIN_MESSAGE, iconoDadoSegunda);

               // Verificar si el jugador gana o pierde la apuesta
               if (resultadoSegundaTirada > resultadoDados) {
                  jugador.incrementarPresupuesto(apuesta);
                  poteTiene -= apuesta;
                  JOptionPane.showMessageDialog(null, "Ganaste!");
               } else {
                  poteTiene += apuesta;
                  jugador.decrementarPresupuesto(apuesta);
                  JOptionPane.showMessageDialog(null, "Perdiste!");
               }
            }
         }

         if (jugador.getPresupuesto() <= 0) {
            JOptionPane.showMessageDialog(null, jugador.getNombreJugador() +
                  " se quedo sin fondos y ha sido eliminado del juego.");
            eliminarJugador(jugadorActual);
         }

         if (!quedanJugadoresConDinero() || poteTiene <= 0) {
            juegoActivo = false;
         } else {
            jugadorActual = (jugadorActual + 1) % jugadores.length;
         }
      }

      for (int i = 0; i < jugadores.length; i++) {
         if (jugadores[i].getPresupuesto() > 0) {
            alguienGano = true;
            break;
         }
      }

      if (alguienGano && poteTiene <= 0) {
         Jugador jugador = jugadores[jugadorActual];
         JOptionPane.showMessageDialog(null, jugador.getNombreJugador() + " gano el pote.");
      } else if (alguienGano) {
         JOptionPane.showMessageDialog(null, "Gano la casa!");
      }
   }

   private void eliminarJugador(int indice) {
      Jugador[] nuevosJugadores = new Jugador[jugadores.length - 1];
      int contador = 0;
      for (int i = 0; i < jugadores.length; i++) {
         if (i != indice) {
            nuevosJugadores[contador] = jugadores[i];
            contador++;
         }
      }
      jugadores = nuevosJugadores;
      numeroJugadores--;
      jugadorActual = jugadorActual % numeroJugadores;
   }

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

   public int getDado() {
      return dado;
   }

   public int getPoteTiene() {
      return poteTiene;
   }

   public int getNumeroJugadores() {
      return numeroJugadores;
   }

   public int getJugadorActual() {
      return jugadorActual;
   }

   public Jugador[] getJugadores() {
      return jugadores;
   }

   public void setDado(int dado) {
      this.dado = dado;
   }

   public void setPoteTiene(int poteTiene) {
      this.poteTiene = poteTiene;
   }
}
