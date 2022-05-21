
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.tracing.opentelemetry.SeleniumSpanExporter;


import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;
import java.util.List;

public class WebWindow extends JPanel {

    Font font = new Font("Gisha", Font.BOLD, 30);
    Font textFiledFont = new Font("Gisha", Font.BOLD, 24);

    public static final String WEB = "https://web.whatsapp.com/";

    public static final int ENTER_BUTTON_X = 100, ENTER_BUTTON_Y = 700, ENTER_BUTTON_WIDTH = 250, ENTER_BUTTON_HEIGHT = 100;
    public static final int GENERAL_WIDTH = 400, GENERAL_HEIGHT = 50;
    public static final int PHONE_NUM_TITLE_Y = 100, PHONE_NUM_TITLE_WIDTH = 275;
    public static final int MARGIN_BETWEEN = 100;
    public static final int MESSAGE_TITLE_MARGIN = 310;
    public static final int MESSAGE_TEXT_MARGIN_X = 210, MESSAGE_TEXT_WIDTH = 500, MESSAGE_TEXT_HEIGHT = 100;
    public static final int ENTER_LABEL_X = 100, ENTER_LABEL_Y = 700, ENTER_LABEL_WIDTH = 500, ENTER_LABEL_HEIGHT = 150;
    public static final int LENGTH_PHONE_NUMBER = 10;
    public static final String PHONE_START = "05", ISRAELI_AREA_CODE = "972";


    private ImageIcon background;
    private JButton enterButton;
    private JLabel successfullyEnterLabel;
    private JLabel phoneNumTitle;
    private JTextField phoneNumberTextField;
    private JLabel messageTitle;
    private JTextField messageTextField;
    private JLabel messageForUser;


    public WebWindow(int x, int y, int width, int height) {
//        System.setProperty("webdriver.chrome.driver", "C:\\1234\\driver.exe");
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("user-data-air=C:\\Users\\shani\\AppData\\Local\\Temp\\scoped_dir4008_2001821348\\Default");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\adarm\\Downloads\\chromedriver_win32 (2)\\chromedriver.exe");
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("user-data-dir=c:C:\\Users\\adarm\\AppData\\Local\\Google\\Chrome\\User Data\\Profile 1\n");

        this.setBounds(x, y, width, height);
        this.setLayout(null);

        this.enterButton = new MyJButton("התחבר", ENTER_BUTTON_X, ENTER_BUTTON_Y, ENTER_BUTTON_WIDTH, ENTER_BUTTON_HEIGHT).getButton();
        this.add(this.enterButton);
        enter();
//
        this.phoneNumTitle = newLabel("הכנס מספר פלאפון: ", MainWindow.WINDOW_WIDTH - GENERAL_WIDTH,
                PHONE_NUM_TITLE_Y, PHONE_NUM_TITLE_WIDTH, GENERAL_HEIGHT);
        this.add(phoneNumTitle);

        this.phoneNumberTextField = newTextField(phoneNumTitle.getX() + phoneNumTitle.getWidth() - GENERAL_WIDTH,
                phoneNumTitle.getY() + phoneNumTitle.getHeight(), GENERAL_WIDTH, GENERAL_HEIGHT);
        this.add(phoneNumberTextField);

        this.messageTitle = newLabel("הכנס הודעה: ", MainWindow.WINDOW_WIDTH - MESSAGE_TITLE_MARGIN,
                phoneNumberTextField.getY() + MARGIN_BETWEEN, GENERAL_WIDTH, GENERAL_HEIGHT);
        this.add(messageTitle);

        this.messageTextField = newTextField(messageTitle.getX() + messageTitle.getWidth() - MESSAGE_TEXT_WIDTH - MESSAGE_TEXT_MARGIN_X,
                messageTitle.getY() + messageTitle.getHeight(), MESSAGE_TEXT_WIDTH, MESSAGE_TEXT_HEIGHT);
        this.add(messageTextField);

        this.messageForUser = newLabel(" ", 200, 200, 500, 80);
        this.add(this.messageForUser);

        this.background = new ImageIcon("background.png");
        this.setVisible(true);
    }


    private void enter() { //TODO action listener++++++++++++++++++++++++
        this.enterButton.addActionListener((event) -> {
//            this.enterButton.setVisible(false);
            repaint(); //*****

            if ((!(this.messageTextField.getText().equals(""))) && phoneNumber1(this.phoneNumberTextField.getText())) {
                System.out.println("תקין");
//                   ChromeDriver web = new ChromeDriver();
//                   web.get(WEB);
//                   web.manage().window().maximize();
//                   List<WebElement> menu = web.findElements(By.linkText("בארכיון"));
//                   System.out.println(menu.size());
//                   if (menu.size() > 0) {
//                       System.out.println("find");
//                enterLabel();
//                repaint();
//                       this.successfullyEnterLabel = newLabel("התתחברות בוצעה בהצלחה!",
//                               ENTER_LABEL_X, ENTER_LABEL_Y, ENTER_LABEL_WIDTH, ENTER_LABEL_HEIGHT);
//                       this.add(successfullyEnterLabel);
            } else if (this.messageForUser.getText().equals(""))
                this.messageForUser.setText("יש להכניס הודעה");
        });
    }

    public boolean phoneNumber1(String phone) {
        boolean validAreaCode = false;
        boolean onlyNumbers = true;
        String partOfnum = "";
        try {
            if (phone.length() != 0) {
                if (phone.length() < 10 || phone.length() > 12) {
                    if (phone.length() < 10)
                        this.messageForUser.setText("אורך מספר הטלפון קצר");
                    else
                        this.messageForUser.setText("אורך מספר הטלפון ארוך");
                } else {
                    if (phone.substring(0, 2).equals("05") && phone.length() == 10) {
                        validAreaCode = true;
                        partOfnum = phone.substring(3);
                    } else if (phone.substring(0, 3).equals("972") && phone.length() == 12) {
                        validAreaCode = true;
                        partOfnum = phone.substring(3);
                    } else this.messageForUser.setText("לא תקין");
                    System.out.println(partOfnum);
                    for (int i = 0; i < partOfnum.length(); i++) {
                        if (!Character.isDigit(partOfnum.charAt(i)))
                            onlyNumbers = false;
                    }
                    if (!onlyNumbers)
                        this.messageForUser.setText("יש להכניס רק ספרות");
                }
            } else
                this.messageForUser.setText("יש להכניס מספר טלפון");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (validAreaCode && onlyNumbers)
            return true;
        else {
            return false;
        }
    }

    public JLabel newLabel(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        label.setFont(font);
        return label;
    }

    public JTextField newTextField(int x, int y, int width, int height) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, width, height);
        textField.setFont(textFiledFont);
        return textField;
    }

    public void paintComponent(Graphics graphics) {
        graphics.drawImage(this.background.getImage(), 0, 0,
                MainWindow.WINDOW_WIDTH, MainWindow.WINDOW_HEIGHT, null);
    }
}