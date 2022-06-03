
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import javax.swing.*;
import java.awt.*;

import java.util.List;

public class WebWindow extends JPanel {

    Font font = new Font("Gisha", Font.BOLD, 30);
    Font textFiledFont = new Font("Gisha", Font.BOLD, 24);
    public static final String CONTACT = "https://web.whatsapp.com/send?phone=";

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

    private void enter() {
        this.enterButton.addActionListener((event) -> {
            String phone = this.phoneNumberTextField.getText();
            if (this.messageTextField.getText().equals("") && this.phoneNumberTextField.getText().equals(""))
                this.messageForUser.setText("יש להכניס מספר טלפון תקין והודעה לשליחה");
            else if (this.phoneNumberTextField.getText().equals(""))
                this.messageForUser.setText("יש להכניס מספר טלפון");
            else if (!PhoneNumber.isValidPhoneNumber(phone))
                this.messageForUser.setText("יש להכניס מספר תקין.");
            else if (this.messageTextField.getText().equals(""))
                this.messageForUser.setText("יש להכניס הודעה");

            if (PhoneNumber.isValidPhoneNumber(phone) && (!this.messageTextField.getText().equals(""))) {
                this.messageForUser.setText("תקין");

                ChromeDriver web = new ChromeDriver();
                web.get(CONTACT + PhoneNumber.formatPhoneNumber(phone));
                web.manage().window().maximize();

                List<WebElement> menu = web.findElements(By.linkText("עזרה בשביל להתחיל?"));
                System.out.println("menu" + menu.size());
                System.out.println(menu.get(0).isDisplayed());

                do {
                    SendMessage sendMessage = new SendMessage(MainWindow.WINDOW_X, MainWindow.WINDOW_Y,
                            MainWindow.WINDOW_WIDTH, MainWindow.WINDOW_WIDTH, this.messageTextField.getText(), web);
                    this.add(sendMessage);
                    this.setVisible(false);
                } while (!menu.get(0).isDisplayed());

                // List<WebElement> userMessageInput = web.findElements(By.className("web"));
//                System.out.println("user" + userMessageInput.size());
//
//                WebElement m  = userMessageInput.get(0).findElement(By.id("app"));
//                List<WebElement> text = web.findElements(By.className("_2cYbV"));
//                System.out.println("text " + text.size());

                //   WebElement sendClick = web.findElement(By.className("tvf2evcx oq44ahr5 lb5m6g5c svlsagor p2rjqpw5 epia9gcq"));
                // userMessageInput.get(0).sendKeys("this.messageTextField.getText()");

//                do {
//                    this.successfullyEnterLabel = newLabel("התתחברות בוצעה בהצלחה!",
//                            ENTER_LABEL_X, 500, ENTER_LABEL_WIDTH, ENTER_LABEL_HEIGHT);
//                    this.add(successfullyEnterLabel);
//                    this.enterButton.setVisible(false);
//
//
//                } while (menu.get(0).isDisplayed());
            }
        });
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