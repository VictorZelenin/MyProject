package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Created by User on 16.10.2015.
 *  @Version 1.0
 */
public class ReportFrame extends JFrame {
    private JLabel label;
    private JTextField dateFiled;
    private JButton reportButton;
    private JButton backButton;
    private JButton exitButton;
    private JPanel buttonPanel;
    private JPanel textFiledPanel;
    private JTable reportTable;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;


    public ReportFrame() {
        // задаємо менеджера компонування для форми
        setLayout(new BorderLayout());


        label = new JLabel("Вкажіть дату за якою треба створити звіт(yyyy-MM-dd) : ");

        // створюємо таблицю та модель таблиці
        tableModel = new DefaultTableModel();
        reportTable = new JTable(tableModel);
        scrollPane = new JScrollPane(reportTable);
        // спочатку таблиця невидима
        scrollPane.setVisible(false);

        // створюємо  та розміщуємо компоненти
        buttonPanel = new JPanel();
        textFiledPanel = new JPanel();

        dateFiled = new JTextField(20);
        reportButton = new JButton("Створити звіт");
        backButton = new JButton("Назад");
        exitButton = new JButton("Вихід");

        backButton.setVisible(false);
        exitButton.setVisible(false);

        textFiledPanel.add(label, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(200, 200, 200, 200), 0, 0));
        textFiledPanel.add(dateFiled, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(200, 200, 200, 200), 0, 0));

        buttonPanel.setLayout(new GridBagLayout());

        buttonPanel.add(backButton, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(1, 200, 8, 200), 0, 0));
        buttonPanel.add(reportButton, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(1, 200, 8, 200), 0, 0));
        buttonPanel.add(exitButton, new GridBagConstraints(2, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(1, 200, 8, 200), 0, 0));

        add(textFiledPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
        add(scrollPane);


    }

    public String getDateFiled() {
        return dateFiled.getText();
    }

    public JTextField getField() {
        return dateFiled;
    }

    public JButton getReportButton() {
        return reportButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JLabel getLabel() {
        return label;
    }
}
