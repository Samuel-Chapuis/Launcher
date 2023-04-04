package fr.samuel;

import javafx.application.Application;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        try{
            Class.forName("javafx.application.Application");
            Application.launch(fr.samuel.Launcher.class, args);
        }catch (Exception e){
            JOptionPane.showMessageDialog(
                    null,
                    "Erreur \n" + e.getMessage() + "not found",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}