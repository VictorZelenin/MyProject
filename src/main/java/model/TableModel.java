package model;

import model.Model;

import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by User on 16.10.2015.
 *  @Version 1.0
 */
public class TableModel extends AbstractTableModel {
    // кількість стовпців
    private static final int columnCount = 14;
    // список, який зберігає всю інформацію
    private ArrayList<String[]> dataArrayList;

    public TableModel() {
        // створюємо відповідний список
        dataArrayList = new ArrayList<String[]>();
        for (int i = 0; i < dataArrayList.size(); i++) {
            // додаємо інформацію
            dataArrayList.add(new String[getColumnCount()]);
        }
    }

    public int getRowCount() {
        return dataArrayList.size();
    }

    public int getColumnCount() {
        return columnCount;
    }

    // метод, який повертає об'єкт за вказаними координатами
    public Object getValueAt(int rowIndex, int columnIndex) {
        String[] rows = dataArrayList.get(rowIndex);
        return rows[columnIndex];
    }

    // метод, який повертає ID клієнта за вказаним номером рядка
    public int getID(int rowIndex) {
        String[] rows = dataArrayList.get(rowIndex);
        return Integer.parseInt(rows[0]);
    }


    // додавання інформації до таблиці
    public void addData(String[] row) {
        String[] rowTable = new String[getColumnCount()];
        rowTable = row;
        dataArrayList.add(rowTable);
    }

    // зчитування інформації із БД
    public void addData(Model model) {
        ResultSet resultSet = model.getDatabaseWorker().executeQuery("SELECT * FROM information");
        try {

            while (resultSet.next()) {
                String[] row = {resultSet.getString(1),
                        resultSet.getString(3),
                        resultSet.getString(2),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9),
                        resultSet.getString(10),
                        resultSet.getString(11),
                        resultSet.getString(12),
                        resultSet.getString(13),
                        resultSet.getString(14)};
                addData(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // розміщення назв стовпців
    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "#id";
            case 1:
                return "Прізвище";
            case 2:
                return "Ім'я";
            case 3:
                return "По батькові";
            case 4:
                return "Адреса клієнта";
            case 5:
                return "Ім'я одержувача";
            case 6:
                return "Прізвище одержувача";
            case 7:
                return "По батькові одержувача";
            case 8:
                return "Адреса одержувача";
            case 9:
                return "Вага, г";
            case 10:
                return "Тип";
            case 11:
                return "Страхування";
            case 12:
                return "Дата";
            case 13:
                return "Телефонний номер";

            default:
                return null;
        }


    }
    // видалення рядка
    public void deleteRow(int rowIndex) {
        dataArrayList.remove(rowIndex);


    }
}
