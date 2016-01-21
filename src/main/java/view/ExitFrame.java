package view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by User on 13.10.2015.
 * * @Version 1.0
 */
public class ExitFrame extends JFrame {
    private JLabel gratitudeLabel;
    private JLabel checkLabel;
    private JButton exitButton;
    private JButton checkButton;
    private JPanel gratitudeLabelPanel;
    private JPanel buttonPanel;
    private JPanel mainLabelPanel;


    public ExitFrame() {
        // задаємо менеджер компонування для нашої форми
        setLayout(new BorderLayout());
        // ініціалізація компонентів
        gratitudeLabelPanel = new JPanel();
        buttonPanel = new JPanel();
        mainLabelPanel = new JPanel();

        // розміщення контейнерів на формі
        add(gratitudeLabelPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
        add(mainLabelPanel,BorderLayout.CENTER);

        gratitudeLabel = new JLabel("Дякуємо, що ви скористалися нашою системою!");
        checkLabel = new JLabel("Чек успішно сформовано");
        checkLabel.setVisible(false);
        // задаємо шрифт для надпису
        Font font = new Font("Helvetica", Font.PLAIN, 16);
        gratitudeLabel.setFont(font);

        gratitudeLabelPanel.add(gratitudeLabel,CENTER_ALIGNMENT);
        gratitudeLabelPanel.add(checkLabel,CENTER_ALIGNMENT);

        // задаємо менеджер компонування для контейнеру для кнопок
        buttonPanel.setLayout(new GridBagLayout());

        // ініціалізація кнопок
        checkButton = new JButton("Сформувати чек");
        exitButton = new JButton("Вихід");

        // розміщення кнопок
        buttonPanel.add(checkButton, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(10, 100, 10, 100), 0, 0));
        buttonPanel.add(exitButton, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(10, 100, 10, 100), 0, 0));


    }

    public JButton getCheckButton() {
        return checkButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }
}
