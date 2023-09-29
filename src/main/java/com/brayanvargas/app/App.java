package com.brayanvargas.app;

import javax.swing.*;

public class App {
    public static void main(String[] args) {

        ImageIcon icon = new ImageIcon(
        "C:\\Users\\Usuario\\IdeaProjects\\Taller-4\\src\\main\\java\\com\\brayanvargas\\resources\\guayaba.png");

        String[] opciones = {"Jugar", "Ver instrucciones"};

        int resp = JOptionPane.showOptionDialog(null, "Bienbenidx al juego de la Guayabita!" +
                        "\n\nQue quieres hacer?","Guayabita", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, icon, opciones, opciones[0]);

        if (resp == 0) {

        } else {
            JOptionPane.showMessageDialog(null, "Si el jugador");
        }
    }
}
