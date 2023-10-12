package com.brayanvargas.domain;

import javax.swing.*;

public class Jugador {
   private String nombreJugador;
   private int presupuesto;
   private int apuesta;
   private int dadoAnterior;


   public Jugador(int jugador) {
      this.nombreJugador = "Jugador " + jugador;
      this.apuesta = 500;
      this.dadoAnterior = 0;
   }

   public void incrementarPresupuesto(int monto) {
      presupuesto += monto;
   }

   public void decrementarPresupuesto(int monto) {
      presupuesto -= monto;
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

   public int getDadoAnterior() {
      return dadoAnterior;
   }

   public void setDadoAnterior(int dadoAnterior) {
      this.dadoAnterior = dadoAnterior;
   }

   public void setNombreJugador(String nombreJugador) {
      this.nombreJugador = nombreJugador;
   }

   public void setPresupuesto(int presupuesto) {
      this.presupuesto = presupuesto;
   }

}
