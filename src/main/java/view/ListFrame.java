package view;

import model.Model;
import model.TableModel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by User on 16.10.2015.
 *  @Version 1.0
 */
public class ListFrame extends JFrame {
    private JButton backButton;
    private JButton exitButton;
    private JPanel tablePanel;
    private JPanel buttonPanel;
    private JTable table;
    private TableModel tableModel;

    public ListFrame() {
        // задаємо менеджера компонування для форми
        setLayout(new BorderLayout());

        tablePanel = new JPanel();
        buttonPanel = new JPanel();

        backButton = new JButton("Назад");
        exitButton = new JButton("Вихід");
        buttonPanel.add(backButton);
        // задаємо менеджерів компонування для контейнерів
        tablePanel.setLayout(new GridBagLayout());
        buttonPanel.setLayout(new GridBagLayout());

        // створюємо модель таблиці та саму таблицю
        tableModel = new TableModel();
        tableModel.addData(new Model());
        table = new JTable(tableModel);
        // задаємо виділення таблиці за строчками
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // додаємл таблицю в відповідний контейней
        JScrollPane scrollPane = new JScrollPane(table);


        // розміщуємо компоненти в контейнерах
        tablePanel.add(scrollPane, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));
        buttonPanel.add(backButton, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(1, 100, 1, 900), 0, 0));
        buttonPanel.add(exitButton, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 100), 0, 0));

        // розміщуємо контейнери
        add(tablePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);


    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public JTable getTable() {
        return table;
    }

    public TableModel getTableModel() {
        return tableModel;
    }
}
