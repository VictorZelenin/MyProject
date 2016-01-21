package main;

import java.awt.*;
import view.View;
import model.Model;
import controller.Controller;


public class Main {
    private static final Model model = new Model();
    private static final View view = new View();


    public static void main(String[] args) {
        // створюємо окремий потік для виконання
        EventQueue.invokeLater(() -> {

            // викликаємо контролер
            new Controller(model, view);


        });

    }
}
