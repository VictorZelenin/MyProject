package view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by User on 13.10.2015.
 *  @Version 1.0
 */
public class LoginFrame extends JFrame {
    private JLabel label;
    private JLabel loginLabel;
    private JLabel passwordLabel;
    private JTextField login;
    private JPasswordField password;
    private JButton enterButton;
    private JPanel labelPanel;
    private JPanel container;
    private JPanel southPanel;

    public LoginFrame() {
        // задаємо менеджера компонування для форми
        setLayout(new BorderLayout());
        // створюємо контейнери
        labelPanel = new JPanel();
        container = new JPanel();
        southPanel = new JPanel();

        // ініціалізація компонентів
        label = new JLabel("Авторизуйтесь");
        labelPanel.add(label);
        add(labelPanel, BorderLayout.NORTH);

        loginLabel = new JLabel("Логін");
        login = new JTextField(20);
        passwordLabel = new JLabel("Пароль");
        password = new JPasswordField(20);

        enterButton = new JButton("Ввійти");

        //розміщення компонентів в контейнері
        container.setLayout(new GridBagLayout());
        container.add(loginLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        container.add(login, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        container.add(passwordLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 10, 5), 0, 0));
        container.add(password, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 10, 5), 0, 0));

        add(container);

        southPanel.add(enterButton, CENTER_ALIGNMENT);
        add(southPanel,BorderLayout.SOUTH);
    }

    public String getLogin() {
        return login.getText();
    }

    public String getPassword() {
        return password.getText();
    }

    public JButton getEnterButton() {
        return enterButton;
    }
}
