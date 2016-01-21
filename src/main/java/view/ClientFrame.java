package view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by User on 13.10.2015.
 * * @Version 1.0
 */
public class ClientFrame extends JFrame {
    // Поля вводу інформації
    private JTextField clientFirstName;
    private JTextField clientLastName;
    private JTextField clientSurname;
    private JTextField clientAdress;
    private JTextField clientPhoneNumber;
    private JTextField recieverFirstName;
    private JTextField recieverLastName;
    private JTextField recieverSurname;
    private JTextField recieverAdress;
    private JTextField weight;

    // Надписи до полів вводу
    private JLabel label;
    private JLabel clientFirstNameLabel;
    private JLabel clientLastNameLabel;
    private JLabel clientSurnameLabel;
    private JLabel clientAdressLabel;
    private JLabel clientPhoneNumberLabel;
    private JLabel recieverFirstNameLabel;
    private JLabel recieverLastNameLabel;
    private JLabel recieverSurnameLabel;
    private JLabel recieverAdressLabel;
    private JLabel weightLabel;
    private JLabel insuranceLabel;
    private JLabel typeLabel;

    // Контейнери для компонентів
    private JPanel panel; // для текстової інформації
    private JPanel bottomPanel; // для кнопок переходу
    private JPanel labelPanel; // для головного надпису
    private JPanel typePanel;
    private JPanel insurancePanel;


    // кнопки переходу
    private JButton backButton;
    private JButton nextButton;

    // компоненти для вибору типу посилки та страхування
    private JRadioButton letterRadioButton;
    private JRadioButton packageRadioButton;
    private JRadioButton wrapperRadioButton;
    private JRadioButton yesInsuranceButton;
    private JRadioButton noInsuranceButton;

    //компоненти для розміщення груп кнопок-перемикачів
    private ButtonGroup typeButtonGroup;
    private ButtonGroup insuranceButtonGroup;


    public ClientFrame() {
        // ініціалізація надписів
        label = new JLabel("Заповніть форму для відправлення: ");
        clientFirstNameLabel = new JLabel("Ім'я: ");
        clientLastNameLabel = new JLabel("Прізвище: ");
        clientSurnameLabel = new JLabel("По батькові: ");
        clientAdressLabel = new JLabel("Адреса: ");
        clientPhoneNumberLabel = new JLabel("Номер мобільного телефону: ");
        recieverFirstNameLabel = new JLabel("Ім'я одержувача: ");
        recieverLastNameLabel = new JLabel("Прізвище одержувача: ");
        recieverSurnameLabel = new JLabel("По батькові одержувача: ");
        recieverAdressLabel = new JLabel("Адреса одержувача: ");
        weightLabel = new JLabel("Вага посилки, гр: ");
        typeLabel = new JLabel("Оберіть тип відпралення: ");
        insuranceLabel = new JLabel("Бажаєте застрахувати відправлення? ");

        // ініціалізація текстових полів
        clientFirstName = new JTextField(15);
        clientLastName = new JTextField(15);
        clientSurname = new JTextField(15);
        clientAdress = new JTextField(15);
        clientPhoneNumber = new JTextField(15);
        recieverAdress = new JTextField(15);
        recieverFirstName = new JTextField(15);
        recieverLastName = new JTextField(15);
        recieverSurname = new JTextField(15);
        weight = new JTextField(15);

        // ініціалізація кнопок переходу
        backButton = new JButton("Назад");
        nextButton = new JButton("Далі");

        // ініціалізація контейнерів для компонентів
        labelPanel = new JPanel();
        panel = new JPanel();
        bottomPanel = new JPanel();
        typePanel = new JPanel();
        insurancePanel = new JPanel();

        // ініціалізація груп кнопок
        typeButtonGroup = new ButtonGroup();
        insuranceButtonGroup = new ButtonGroup();



        // ініціалізація та розміщення компонентів вибору
        letterRadioButton = new JRadioButton("Лист");
        packageRadioButton = new JRadioButton("Посилка");
        wrapperRadioButton = new JRadioButton("Бандероль");

        // об'єднання кнопок-перемикачів вибору типу  в групу
        typeButtonGroup.add(letterRadioButton);
        typeButtonGroup.add(packageRadioButton);
        typeButtonGroup.add(wrapperRadioButton);

        // додаємо кнопки до контейнеру
        typePanel.add(letterRadioButton);
        typePanel.add(packageRadioButton);
        typePanel.add(wrapperRadioButton);

        //ініціалізація кнопок вибору страхування
        yesInsuranceButton = new JRadioButton("Так");
        noInsuranceButton = new JRadioButton("Ні");

        // об'єднання кнопок-перемикачів вибору страхування  в групу
        insuranceButtonGroup.add(yesInsuranceButton);
        insuranceButtonGroup.add(noInsuranceButton);

        // додаємо кнопки до контейнеру
        insurancePanel.add(yesInsuranceButton);
        insurancePanel.add(noInsuranceButton);


        // встановлення менеджера компонування для форми та розміщення головного надпису
        setLayout(new BorderLayout());
        add(labelPanel, BorderLayout.NORTH);
        labelPanel.add(label, CENTER_ALIGNMENT);

        // встановлення менеджера компонування для текстового контейнеру та розміщення надписів та основних компонентів
        panel.setLayout(new GridBagLayout());
        panel.add(clientFirstNameLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(15, 20, 5, 10), 0, 0));
        panel.add(clientFirstName, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(15, 10, 5, 40), 0, 0));
        panel.add(recieverFirstNameLabel, new GridBagConstraints(2, 0, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(15, 40, 5, 10), 0, 0));
        panel.add(recieverFirstName, new GridBagConstraints(3, 0, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(15, 10, 5, 20), 0, 0));

        panel.add(clientLastNameLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 20, 5, 10), 0, 0));
        panel.add(clientLastName, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 10, 5, 40), 0, 0));
        panel.add(recieverLastNameLabel, new GridBagConstraints(2, 1, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 40, 5, 10), 0, 0));
        panel.add(recieverLastName, new GridBagConstraints(3, 1, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 10, 5, 20), 0, 0));

        panel.add(clientSurnameLabel, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 20, 5, 10), 0, 0));
        panel.add(clientSurname, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 10, 5, 40), 0, 0));
        panel.add(recieverSurnameLabel, new GridBagConstraints(2, 2, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 40, 5, 10), 0, 0));
        panel.add(recieverSurname, new GridBagConstraints(3, 2, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 10, 5, 20), 0, 0));

        panel.add(clientAdressLabel, new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 20, 5, 10), 0, 0));
        panel.add(clientAdress, new GridBagConstraints(1, 3, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 10, 5, 40), 0, 0));
        panel.add(recieverAdressLabel, new GridBagConstraints(2, 3, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 40, 5, 10), 0, 0));
        panel.add(recieverAdress, new GridBagConstraints(3, 3, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 10, 5, 20), 0, 0));

        panel.add(clientPhoneNumberLabel, new GridBagConstraints(0, 4, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 20, 5, 10), 0, 0));
        panel.add(clientPhoneNumber, new GridBagConstraints(1, 4, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 10, 5, 40), 0, 0));
        panel.add(weightLabel, new GridBagConstraints(2, 4, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 40, 5, 10), 0, 0));
        panel.add(weight, new GridBagConstraints(3, 4, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 10, 5, 20), 0, 0));

        panel.add(typeLabel, new GridBagConstraints(0, 5, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(12, 20, 5, 10), 0, 0));
        panel.add(typePanel, new GridBagConstraints(1, 5, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 40), 0, 0));
        panel.add(insuranceLabel, new GridBagConstraints(2, 5, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(12, 40, 5, 10), 0, 0));
        panel.add(insurancePanel, new GridBagConstraints(3, 5, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 20), 0, 0));

        // розміщення основного контейнера на формі
        add(panel);

        // розміщення кнопок переходу менеджером GridBagLayout
        add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.setLayout(new GridBagLayout());
        bottomPanel.add(backButton, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(10, 100, 10, 100), 0, 0));
        bottomPanel.add(nextButton, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(10, 100, 10, 100), 0, 0));


    }

    /* гетери для контролера */
    public JButton getBackButton() {
        return backButton;
    }

    public JButton getNextButton() {
        return nextButton;
    }

    public JRadioButton getLetterRadioButton() {
        return letterRadioButton;
    }

    public JRadioButton getPackageRadioButton() {
        return packageRadioButton;
    }

    public JRadioButton getWrapperRadioButton() {
        return wrapperRadioButton;
    }

    public JRadioButton getYesInsuranceButton() {
        return yesInsuranceButton;
    }

    public JRadioButton getNoInsuranceButton() {
        return noInsuranceButton;
    }

    public String getClientFirstName() {
        return clientFirstName.getText();
    }

    public String getRecieverFirstName() {
        return recieverFirstName.getText();
    }

    public String getClientLastName() {
        return clientLastName.getText();
    }

    public String getRecieverLastName() {
        return recieverLastName.getText();
    }

    public String getClientSurname() {
        return clientSurname.getText();
    }

    public String getRecieverSurname() {
        return recieverSurname.getText();
    }

    public String getClientAdress() {
        return clientAdress.getText();
    }

    public String getRecieverAdress() {
        return recieverAdress.getText();
    }

    public String getClientPhoneNumber() {
        return clientPhoneNumber.getText();
    }

    public double getWeight() {
        return Double.parseDouble(weight.getText());
    }


}
