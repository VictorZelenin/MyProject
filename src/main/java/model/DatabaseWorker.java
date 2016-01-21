package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by User on 13.10.2015.
 *  @Version 1.0
 *
 * */

// Переписать через паттерн Executor!

public class DatabaseWorker {
    // константи для встановлення зв'язку із MySQL сервером
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    // Singleton
    private static DatabaseWorker uniqueInstance;

    private Connection connection;

    private DatabaseWorker() {
        // створюємо зв'язок із БД
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e1) {
            System.err.println("Не вдалося встановити зв'язок із базою даних!");
        }
    }

    // Singleton
    public static DatabaseWorker getInstance(){
        if (uniqueInstance == null){
            uniqueInstance = new DatabaseWorker();
        }
        return uniqueInstance;
    }

    public Connection getConnection() {
        return connection;
    }

    // метод, який виконує запит до БД, повертає набір результатів
    public ResultSet executeQuery(String query) {
        try {

            java.sql.Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            return resultSet;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // метод, для видалення із БД , видаляє за заданим id
    public void deleteFromDB(int id) {
        try {
            java.sql.Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM information WHERE id = " + id);


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
