package com.brayanvargas.domain;

import javax.swing.*;

public class Jugador {
   String nombreJugador;
   int presupuesto;
   int apuesta;


   public Jugador(int jugador) {
      this.nombreJugador = JOptionPane.showInputDialog(null, "Nombre del jugador " + jugador);
      this.presupuesto = Integer.parseInt(JOptionPane.showInputDialog(null, "Presupuesto del jugador" + jugador));

   }

   public void ganaJuego(){

   }

   public void pierdeJuego(){

   }

   public String getNombreJugador() {
      return nombreJugador;
   }

   public int getPresupuesto() {
      return presupuesto;
   }

   public int getApuesta() {
      return apuesta;
   }

   public void setApuesta(int apuesta) {
      this.apuesta = apuesta;
   }


}
