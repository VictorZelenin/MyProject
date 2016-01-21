package controller;

import model.Model;
import view.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


/**
 * @author Zelenin Victor
 * @version 1.0
 *          Created by Victor Zelenin on 13.10.2015.
 */

public class Controller {
    // запит до БД
    private static final String INSERT = "INSERT INTO information(first_name_client, last_name_client, " +
            "surname_client, adress_client, first_name_reciever, last_name_reciever, surname_reciever, " +
            "adress_reciever, weight, type, insurance, date,phone_number) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
    // константи , які задають ціну
    private static final int PRICE_OF_LETTER = 50;
    private static final int PRICE_OF_PACKAGE = 100;
    private static final int PRICE_OF_WRAPPER = 200;
    // константи , які задають логін та пароль
    private static final String LOGIN = "User";
    private static final String PASSWORD = "password";


    private View view;
    private Model model;

    // діалогові вікна
    private JOptionPane databaseError;
    private JOptionPane logError;
    private JOptionPane reportError;
    private JOptionPane checkError;
    private JOptionPane weightError;

    // списки для створення звіту(дані про тип та вагу)
    private static ArrayList<String> TYPE = new ArrayList<String>();
    private static ArrayList<Double> WEIGHT = new ArrayList<Double>();


    public Controller(Model model, final View view) {

        this.model = model;
        this.view = view;

        // налаштовуємо вхідну форму
        setupEnterFrame();
        // створюємо об'єкт, який реагує на зміну стану кнопок
        new ButtonListener();
        // створюємо об'єкт, який реагує на зміну стану таблиці
        new TableListener();


    }

    /* налаштування форм */
    private void setupEnterFrame() {
        //view.getEnterFrame().pack();
        view.getEnterFrame().setSize(400, 400);
        view.getEnterFrame().setLocation(350, 100);
        view.getEnterFrame().setTitle("Початок");
        view.getEnterFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.getEnterFrame().setVisible(true);
        view.getEnterFrame().setResizable(false);
    }

    private void setupClientFrame() {
        view.getClientFrame().pack();
        view.getClientFrame().setLocation(350, 100);
        view.getClientFrame().setTitle("Анкета");
        view.getClientFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.getClientFrame().setVisible(true);
    }

    private void setupExitFrame() {
        view.getExitFrame().setSize(650, 300);
        view.getExitFrame().setLocation(350, 100);
        view.getExitFrame().setTitle("Кінець");
        view.getExitFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.getExitFrame().setVisible(true);
    }

    private void setupLoginFrame() {
        view.getLoginFrame().pack();
        //view.getLoginFrame().setResizable(false);
        view.getLoginFrame().setResizable(false);
        view.getLoginFrame().setLocation(400, 200);
        view.getLoginFrame().setTitle("Авторизація");
        view.getLoginFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.getLoginFrame().setVisible(true);
    }

    private void setupAdminFrame() {
        view.getAdminFrame().setSize(500, 400);
        view.getAdminFrame().setLocation(350, 100);
        view.getAdminFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.getAdminFrame().setTitle("Адмін");
        view.getAdminFrame().setVisible(true);

    }

    private void setupListFrame() {
        view.getListFrame().setSize(1300, 400);
        view.getListFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.getListFrame().setTitle("Інформація про клієнтів");
        view.getListFrame().setVisible(true);

    }

    private void setupReportFrame() {

        //view.getReportFrame().setSize(600,600);
        view.getReportFrame().pack();
        view.getReportFrame().setLocation(350, 100);
        view.getReportFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.getReportFrame().setTitle("Звіт" + view.getReportFrame().getDateFiled());
        view.getReportFrame().setVisible(true);
    }

    //  вкладений клас, який реалізує зміну стану кнопок
    private class ButtonListener {
        private ButtonListener() {
            // кнопка "Клієнт" на вхідній формі
            view.getEnterFrame().getClientButton().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    view.getEnterFrame().dispose();
                    setupClientFrame();
                }
            });

            // кнопка "Адмін" на вхідній формі
            view.getEnterFrame().getAdminButton().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    view.getEnterFrame().dispose();
                    setupLoginFrame();
                }
            });

            // кнопка "Назад" на формі заповнення
            view.getClientFrame().getBackButton().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    view.getClientFrame().dispose();
                    view.getEnterFrame().setVisible(true);
                }
            });

            // кнопка "Сформувати чек" на вихідній формі
            view.getExitFrame().getCheckButton().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    view.getExitFrame().getCheckButton().setVisible(false);
                    // створення чеку та вивід в консолі
                    createCheck();
                    // створення чеку та добавлення його в файл check.txt
                    addToFile();

                }
            });

            // кнопка "Вихід" на вихідній формі
            view.getExitFrame().getExitButton().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });

            // запис до бази даних при натиску на кнопку "Далі"
            view.getClientFrame().getNextButton().addActionListener(new DatabaseProcessor());

            //  кнопка " Ввійти" на  формі входу
            view.getLoginFrame().getEnterButton().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // вхід до режиму "Адміністратор"
                    if (isSame(view)) {
                        view.getLoginFrame().dispose();
                        setupAdminFrame();
                    } else {
                        logError.showMessageDialog(view.getLoginFrame(), "Невірний логін чи(і) пароль!");
                    }
                }
            });

            // реакція на кнопку "Enter" при авторизації
            view.getLoginFrame().getEnterButton().addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        if (isSame(view)) {
                            view.getLoginFrame().dispose();
                            setupAdminFrame();
                        } else {
                            logError.showMessageDialog(view.getLoginFrame(), "Невірний логін чи(і) пароль!");
                        }
                    }
                }
            });


            // кнопка "Переглянути список" на формі адміністратора
            view.getAdminFrame().getListButton().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    view.getAdminFrame().dispose();
                    setupListFrame();
                }
            });

            // кнопка "Назад" на формі таблиці
            view.getListFrame().getBackButton().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    view.getListFrame().dispose();
                    setupAdminFrame();
                }
            });

            // кнопка "Вихід" на формі твблиці
            view.getListFrame().getExitButton().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });

            // кнопка "Створити звіт" на формі адміністратора
            view.getAdminFrame().getReportButton().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    view.getAdminFrame().dispose();
                    setupReportFrame();

                }
            });

            // кнопка "Назад" на формі звіту
            view.getReportFrame().getBackButton().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    view.getReportFrame().dispose();
                    setupAdminFrame();
                }
            });

            // кнопка "Вихід" на формі звіту
            view.getReportFrame().getExitButton().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });

            // кнопка "Створити звіт" на формі звіту
            view.getReportFrame().getReportButton().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        // створення звіту
                        createReport();
                        // налаштування форми звіту
                        view.getReportFrame().getField().setVisible(false);
                        view.getReportFrame().getReportButton().setVisible(false);
                        view.getReportFrame().getLabel().setVisible(false);
                        view.getReportFrame().getScrollPane().setVisible(true);
                        view.getReportFrame().getBackButton().setVisible(true);
                        view.getReportFrame().getExitButton().setVisible(true);
                        view.getReportFrame().pack();

                    } catch (ParseException ex) {
                        System.err.println("Не вдалося створити звіт!");
                        reportError.showMessageDialog(view.getReportFrame(), "Введіть дату у форматі : yyyy-MM-dd");
                    }
                }
            });

        }

    }

    // вкладений клас, який реалізує взаємодію користувача із таблицею
    private class TableListener {
        private TableListener() {

            view.getListFrame().getTable().addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {

                    // реагуємо на клавішу 'Del'
                    if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                        // спочатку видаляємо із БД
                        model.getDatabaseWorker().deleteFromDB(view.getListFrame().getTableModel().
                                getID(view.getListFrame().getTable().getSelectedRow()));
                        // потім видаляємо із таблиці
                        view.getListFrame().getTableModel().deleteRow(view.getListFrame().getTable().getSelectedRow());
                        // оновлюємо таблицю
                        view.getListFrame().getTable().updateUI();
                    }
                }
            });
        }
    }

    // клас , який реалізує запис даних до БД
    private class DatabaseProcessor implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                //  створюємо запит до БД
                java.sql.PreparedStatement preparedStatement = model.getDatabaseWorker().
                        getConnection().prepareStatement(INSERT);
                // записуємо до БД за заданими полями
                preparedStatement.setString(1, view.getClientFrame().getClientFirstName());
                preparedStatement.setString(2, view.getClientFrame().getClientLastName());
                preparedStatement.setString(3, view.getClientFrame().getClientSurname());
                preparedStatement.setString(4, view.getClientFrame().getClientAdress());
                preparedStatement.setString(5, view.getClientFrame().getRecieverFirstName());
                preparedStatement.setString(6, view.getClientFrame().getRecieverLastName());
                preparedStatement.setString(7, view.getClientFrame().getRecieverSurname());
                preparedStatement.setString(8, view.getClientFrame().getRecieverAdress());
                preparedStatement.setDouble(9, view.getClientFrame().getWeight());
                preparedStatement.setString(10, getType(view));
                preparedStatement.setString(11, getInsurance(view));
                preparedStatement.setDate(12, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
                preparedStatement.setString(13, view.getClientFrame().getClientPhoneNumber());


                preparedStatement.execute();
                // встановлення вихідної форми
                view.getClientFrame().dispose();
                setupExitFrame();


            } catch (Exception e1) {
                databaseError.showMessageDialog(view.getClientFrame(),
                        "Не вдалося записати дані до БД, можливі причини: не заповнені всі поля, " +
                                "невірно введена вага посилки");
            }
        }

    }

    // метод, який повертає тип відправлення
    private String getType(View view) {
        if (view.getClientFrame().getLetterRadioButton().isSelected()) {
            return "Лист";
        } else if (view.getClientFrame().getPackageRadioButton().isSelected()) {
            return "Посилка";
        } else if (view.getClientFrame().getWrapperRadioButton().isSelected()) {
            return "Бандероль";
        } else return "false";
    }

    // метод, який повертає статус страхування
    private String getInsurance(View view) {
        if (view.getClientFrame().getYesInsuranceButton().isSelected()) {
            return "Застраховано";
        } else if (view.getClientFrame().getNoInsuranceButton().isSelected()) {
            return "Не застраховано";
        } else return null;
    }

    // метод, який перевіряє логін та пароль
    private boolean isSame(View view) {
        if (view.getLoginFrame().getLogin().equals(LOGIN)) {
            if (view.getLoginFrame().getPassword().equals(PASSWORD))
                return true;
        }
        return false;
    }

    // метод, який створює звіт, використовючи інформацію із БД
    private void createReport() throws ParseException {
        // приводимо дату до формату java.sql.Date
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = format.parse(view.getReportFrame().getDateFiled());
        java.sql.Date sql = new java.sql.Date(parsed.getTime());

        // беремо дані із БД за вказаною користувачем датою
        ResultSet resultSet = model.getDatabaseWorker().executeQuery("SELECT * FROM information WHERE DATE ='" +
                sql + "' ");

        // масиви, які зберігпють стовпці звіту
        Integer[] counters = {0, 0, 0}; // 1-counterLetter; 2-counterPackage; 3-counterWrapper;
        Double[] weights = {0.0, 0.0, 0.0}; //1-weightOfLetters; 2-weightOfPackages; 3-weightOfWrappers;
        Double[] prices = {0.0, 0.0, 0.0}; // 1-priceOfLetters; 2-priceOfPackages; 3-priceOfWrappers

        // записуємо дані із БД
        try {
            while (resultSet.next()) {
                TYPE.add(resultSet.getString("type"));
                WEIGHT.add(resultSet.getDouble("weight"));

            }

            // заповнюємо масиви для стовпців звіту
            for (int i = 0; i < TYPE.size(); i++) {
                if (TYPE.get(i).equals("Лист")) {
                    counters[0]++;
                    weights[0] += WEIGHT.get(i);
                    if (WEIGHT.get(i) < 100) {
                        prices[0] += PRICE_OF_LETTER;
                    } else
                        prices[0] += PRICE_OF_LETTER + (WEIGHT.get(i) - 100) * 0.1;
                } else if (TYPE.get(i).equals("Посилка")) {
                    counters[1]++;
                    weights[1] += WEIGHT.get(i);
                    if (WEIGHT.get(i) < 500) {
                        prices[1] += PRICE_OF_PACKAGE;
                    } else
                        prices[1] += PRICE_OF_PACKAGE + (WEIGHT.get(i) - 500) * 0.1;
                } else if (TYPE.get(i).equals("Бандероль")) {
                    counters[2]++;
                    weights[2] += WEIGHT.get(i);
                    if (WEIGHT.get(i) < 1000) {
                        prices[2] += PRICE_OF_WRAPPER;
                    } else
                        prices[2] += PRICE_OF_WRAPPER + (WEIGHT.get(i) - 1000) * 0.1;
                }

            }

            // передаємо дані до таблиці
            view.getReportFrame().getTableModel().addColumn("Тип", new String[]{"Лист", "Посилка", "Бандероль"});
            view.getReportFrame().getTableModel().addColumn("Кількість", counters);
            view.getReportFrame().getTableModel().addColumn("Загальна вага, гр", weights);
            view.getReportFrame().getTableModel().addColumn("Загальна ціна, UAH", prices);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // метод, який формує чек і виводить його в консоль
    private void createCheck() {

        System.out.println("//*Контактна інформація пошти*//");
        System.out.println("---------------------------------------");
        System.out.println("Дата: " + new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        System.out.println("Замовлення: " + getType(view) + ", " + view.getClientFrame().getWeight() + " гр");
        if (getInsurance(view).equals("Застраховано")) {
            System.out.println("Застраховано");
        }
        System.out.println("---------------------------------------");
        System.out.println("Ціна: " + getPrice() + "UAH");

        System.out.println("---------------------------------------");
        System.out.println("Дякуємо, що скористалися нашим сервісом");

    }

    // метод, який повериає ціну в залежності від типу та ваги
    private double getPrice() {
        if (getType(view).equals("Лист")) {
            if (view.getClientFrame().getWeight() < 100)
                return PRICE_OF_LETTER;
            else return PRICE_OF_LETTER + (view.getClientFrame().getWeight() - 100) * 0.1;
        } else if (getType(view).equals("Посилка")) {
            if (view.getClientFrame().getWeight() < 500)
                return PRICE_OF_PACKAGE;
            else return PRICE_OF_PACKAGE + (view.getClientFrame().getWeight() - 500) * 0.1;
        } else if (getType(view).equals("Бандероль")) {
            if (view.getClientFrame().getWeight() < 1000)
                return PRICE_OF_WRAPPER;
            else return PRICE_OF_WRAPPER + (view.getClientFrame().getWeight() - 1000) * 0.1;
        }
        return 0;
    }

    // метод, який формує чек та виводить його в файл check.txt
    private void addToFile() {

        FileWriter writeFile = null;
        try {
            File logFile = new File("check.txt");
            writeFile = new FileWriter(logFile);
            writeFile.write("//*Контактна інформація пошти*//\r\n");
            writeFile.write("---------------------------------------\r\n");
            writeFile.write("Дата: " + new java.sql.Date(Calendar.getInstance().getTime().getTime()) + "\r\n");
            writeFile.write("Замовлення: " + getType(view) + ", " + view.getClientFrame().getWeight() + " гр\r\n");
            if (getInsurance(view).equals("Застраховано")) {
                writeFile.write("Застраховано\r\n");
            }
            writeFile.write("---------------------------------------\r\n");
            writeFile.write("Ціна: " + getPrice() + " UAH\r\n");
            writeFile.write("---------------------------------------\r\n");
            writeFile.write("Дякуємо, що скористалися нашим сервісом\r\n");


        } catch (IOException e) {
            System.err.println("Не вдалось створити чек!");
            checkError.showMessageDialog(view.getExitFrame(), "Не вдалось створити чек!");

        } finally {
            if (writeFile != null) {
                try {
                    writeFile.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }


}
