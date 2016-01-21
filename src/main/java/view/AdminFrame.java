package view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by User on 13.10.2015.
 * * @Version 1.0
 */
public class AdminFrame extends JFrame {
    private JButton listButton;
    private JButton reportButton;


    public AdminFrame() {

        listButton = new JButton("Показати список");
        reportButton = new JButton("Створити звіт");
        setLayout(new GridBagLayout());




        // розміщуємо компоненти на формі
        add(listButton, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(50, 150, 50, 150), 0, 0));
        add(reportButton, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(40, 150, 50, 150), 0, 0));


    }

    public JButton getListButton() {
        return listButton;
    }

    public JButton getReportButton() {
        return reportButton;
    }
}
