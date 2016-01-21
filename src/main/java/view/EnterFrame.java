package view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by User on 13.10.2015.
 *  @Version 1.0
 */
public class EnterFrame extends JFrame {

    private JLabel label;
    private JButton adminButton;
    private JButton clientButton;



    public EnterFrame(){
        // задаємо менеджер компонування
        setLayout(new GridBagLayout());


        label = new JLabel("Оберіть режим роботи програми :");
        Font font = new Font("Helvetica", Font.PLAIN,15);
        label.setFont(font);

        //створюємо кнопки
        adminButton = new JButton("Адмін");
        clientButton = new JButton("Клієнт");


        // розміщуємо компоненти на формі
        add(label, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(5, 100, 5, 5), 0, 0));
        add(adminButton,new GridBagConstraints(0,1,1,1,1,1,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,
                new Insets(50,150,50,150),0,0));
        add(clientButton,new GridBagConstraints(0,2,1,1,1,1,GridBagConstraints.NORTH,GridBagConstraints.HORIZONTAL,
                new Insets(40,150,50,150),0,0));



    }
    public JButton getAdminButton() {
        return adminButton;
    }

    public JButton getClientButton() {
        return clientButton;
    }


}
