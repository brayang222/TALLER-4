package com.brayanvargas.app;

import com.brayanvargas.domain.Guayabita;

import javax.swing.*;
import java.awt.*;
public class App {
    public static void main(String[] args) {

        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 14));

        ImageIcon icon = new ImageIcon(
                "C:\\Users\\braya\\IdeaProjects\\TALLER-4\\src\\main\\java\\com\\brayanvargas\\resources\\guayaba.png");

        String[] opciones = {"Jugar", "Ver instrucciones", "Salir"};
        Guayabita guayabita = new Guayabita();

        while (true) {

            int resp = JOptionPane.showOptionDialog(null, "Bienbenidx al juego de la Guayabita!" +
                            "\n\nQue quieres hacer?", "Guayabita", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, icon, opciones, opciones[0]);

            if (resp == 0) {
                guayabita.inicializarJuego();
                guayabita.jugar();
            } else if (resp == 1){
                guayabita.instrucciones();
            } else {
                System.exit(0);
            }
        }
    }
}
