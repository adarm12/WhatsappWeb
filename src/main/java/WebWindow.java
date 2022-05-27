
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.tracing.opentelemetry.SeleniumSpanExporter;


import javax.swing.*;
import java.awt.*;

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
    public static final String PHONE_START = "05", ISRAELI_AREA_CODE = "9725";


    private ImageIcon background;
    private JButton enterButton;
    private JLabel successfullyEnterLabel;
    private JLabel phoneNumTitle;
    private JTextField phoneNumberTextField;
    private JLabel messageTitle;
    private JTextField messageTextField;
    private JLabel messageForUser;


    public WebWindow(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.setLayout(null);

        this.enterButton = new MyJButton("התחבר", ENTER_BUTTON_X, ENTER_BUTTON_Y, ENTER_BUTTON_WIDTH, ENTER_BUTTON_HEIGHT).getButton();
        this.add(this.enterButton);
        enter();

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

        this.messageForUser = newLabel("", 200, 200, 500, 80);
        this.add(this.messageForUser);

        this.background = new ImageIcon("background.png");
        this.setVisible(true);
    }


    private void enter() { //TODO action listener++++++++++++++++++++++++
        this.enterButton.addActionListener((event) -> {
            phoneNumber1(this.phoneNumberTextField.getText());
//            if (this.messageForUser.getText().equals("") || this.messageForUser.getText() == null)
//                this.messageForUser.setText("יש להכניס הודעה");
            if (this.phoneNumberTextField.getText() == null  && this.messageTextField == null)
                this.messageForUser.setText("phone + mess");
            else {
                this.messageForUser.setText("תקין");
                ChromeDriver web = new ChromeDriver();
                web.get(WEB);
                web.manage().window().maximize();
                List<WebElement> menu = web.findElements(By.linkText("עזרה בשביל להתחיל?"));
                do {
                this.successfullyEnterLabel = newLabel("התתחברות בוצעה בהצלחה!",
                        ENTER_LABEL_X, 500, ENTER_LABEL_WIDTH, ENTER_LABEL_HEIGHT);
                this.add(successfullyEnterLabel);
                } while (menu.get(0).isDisplayed());
                this.enterButton.setVisible(false);
            }
        });
    }

    public void phoneNumber1(String phone) {
        boolean validAreaCode = false;
        boolean onlyNumbers = true;
        String partOfnum = "";
        try {
            if (phone.length() != 0) {
                if (phone.length() < 10 || phone.length() > 12) {
                    this.messageForUser.setText("אורך המספר הטלפון אינו תקין");
                    System.out.println("אורך המספר הטלפון אינו תקין");
                } else if ((phone.substring(0, 2).equals("05") && phone.length() == 10) || (phone.substring(0, 4).equals("9725") && phone.length() == 12)) {
                    validAreaCode = true;
                    partOfnum = phone.substring(4);
                    if (validAreaCode)
                        this.messageForUser.setText("מספר תקין מתחיל ב-05 או ב-9725");
                } else
                    for (int i = 0; i < phone.length(); i++) {
                        if (!Character.isDigit(phone.charAt(i))) {
                            onlyNumbers = false;
                            this.messageForUser.setText("יש להכניס רק ספרות");
                            break;
                        }
                    }
            } else {
                this.messageForUser.setText("יש להכניס מספר טלפון");
                System.out.println("r");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (validAreaCode && onlyNumbers) ;

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